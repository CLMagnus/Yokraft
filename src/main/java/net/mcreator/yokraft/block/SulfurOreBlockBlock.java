
package net.mcreator.yokraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.yokraft.itemgroup.YokraftBlockItemGroup;
import net.mcreator.yokraft.YokraftElements;

import java.util.List;
import java.util.Collections;

@YokraftElements.ModElement.Tag
public class SulfurOreBlockBlock extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:sulfuroreblock")
	public static final Block block = null;
	public SulfurOreBlockBlock(YokraftElements instance) {
		super(instance, 39);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(YokraftBlockItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends FallingBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(5f, 10f).lightValue(0).harvestLevel(2)
					.harvestTool(ToolType.AXE));
			setRegistryName("sulfuroreblock");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
