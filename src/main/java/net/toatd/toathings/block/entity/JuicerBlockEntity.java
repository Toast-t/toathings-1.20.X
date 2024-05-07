package net.toatd.toathings.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.toatd.toathings.item.ModItems;
import net.toatd.toathings.screen.JuicerScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class JuicerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    private static final int FOOD_ITEM_SLOT = 0;
    private static final int MATERIAL_ITEM_SLOT = 1;
    private static final int GLASS_BOTTLE_SLOT = 2;
    private static final int OUPUT_SLOT = 3;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 200;

    public JuicerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JUICER_BLOCK_ENTITY_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> JuicerBlockEntity.this.progress;
                    case 1 -> JuicerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: JuicerBlockEntity.this.progress = value;
                    case 1: JuicerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Juicer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new JuicerScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("juicer.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        progress = nbt.getInt("juicer.progress");
        Inventories.readNbt(nbt, inventory);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            this.increaseCraftingProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                this.craftItem();
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void craftItem() {
        this.removeStack(FOOD_ITEM_SLOT,1);
        this.removeStack(MATERIAL_ITEM_SLOT,1);
        this.removeStack(GLASS_BOTTLE_SLOT,1);
        ItemStack result = new ItemStack(ModItems.APPLE_JUICE);

        this.setStack(OUPUT_SLOT, new ItemStack(result.getItem(),getStack(OUPUT_SLOT).getCount() + result.getCount()));
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.APPLE_JUICE);
        boolean hasFood = getStack(FOOD_ITEM_SLOT).getItem() == Items.APPLE;
        boolean hasMaterial = getStack(MATERIAL_ITEM_SLOT).getItem() == Items.HONEYCOMB;
        boolean hasBottle = getStack(GLASS_BOTTLE_SLOT).getItem() == Items.GLASS_BOTTLE;

        return hasFood && hasMaterial && hasBottle
                && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUPUT_SLOT).getCount() + result.getCount() <= getStack(OUPUT_SLOT).getMaxCount();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUPUT_SLOT).getItem() == item || this.getStack(OUPUT_SLOT).isEmpty();
    }
}
