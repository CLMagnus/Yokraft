
package net.mcreator.yokraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.yokraft.itemgroup.YokraftBlockItemGroup;
import net.mcreator.yokraft.item.SulfurDustItem;
import net.mcreator.yokraft.YokraftElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@YokraftElements.ModElement.Tag
public class SulfurOreBlock extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:sulfurore")
	public static final Block block = null;
	public SulfurOreBlock(YokraftElements instance) {
		super(instance, 38);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(YokraftBlockItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(3f, 5f).lightValue(0).harvestLevel(2)
					.harvestTool(ToolType.SHOVEL));
			setRegistryName("sulfurore");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(SulfurDustItem.block, (int) (3)));
		}
	}
	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == DimensionType.OVERWORLD)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("sulfurore", "sulfurore", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == Blocks.GRAVEL.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), block.getDefaultState(), 7), Placement.COUNT_RANGE, new CountRangeConfig(11, 1, 1, 63)));
		}
	}
}
