
package net.mcreator.yokraft.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.PlantType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.yokraft.item.SoybeansItem;
import net.mcreator.yokraft.YokraftElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@YokraftElements.ModElement.Tag
public class SoybeanPlantBlock extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:soybeanplant")
	public static final Block block = null;
	public SoybeanPlantBlock(YokraftElements instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) {
			@Override
			public boolean place(IWorld world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
				DimensionType dimensionType = world.getDimension().getType();
				boolean dimensionCriteria = false;
				if (dimensionType == DimensionType.OVERWORLD)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				int generated = 0;
				for (int j = 0; j < 20; ++j) {
					BlockPos blockpos = pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
					if (world.isAirBlock(blockpos)) {
						BlockPos blockpos1 = blockpos.down();
						int k = 1 + random.nextInt(random.nextInt(3) + 1);
						k = Math.min(3, k);
						for (int l = 0; l < k; ++l) {
							if (block.getDefaultState().isValidPosition(world, blockpos)) {
								world.setBlockState(blockpos.up(l), block.getDefaultState(), 2);
								generated++;
							}
						}
					}
				}
				return generated > 0;
			}
		};
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG,
					Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(20)));
		}
	}
	public static class BlockCustomFlower extends SugarCaneBlock {
		public BlockCustomFlower() {
			super(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.CROP).hardnessAndResistance(0f, 0f)
					.lightValue(0));
			setRegistryName("soybeanplant");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(SoybeansItem.block, (int) (3)));
		}

		@Override
		public PlantType getPlantType(IBlockReader world, BlockPos pos) {
			return PlantType.Crop;
		}

		@Override
		public void tick(BlockState state, World world, BlockPos pos, Random random) {
			if (!state.isValidPosition(world, pos)) {
				world.destroyBlock(pos, true);
			} else if (world.isAirBlock(pos.up())) {
				int i = 1;
				for (; world.getBlockState(pos.down(i)).getBlock() == this; ++i);
				if (i < 3) {
					int j = state.get(AGE);
					if (j == 15) {
						world.setBlockState(pos.up(), getDefaultState());
						world.setBlockState(pos, state.with(AGE, 0), 4);
					} else {
						world.setBlockState(pos, state.with(AGE, j + 1), 4);
					}
				}
			}
		}
	}
}
