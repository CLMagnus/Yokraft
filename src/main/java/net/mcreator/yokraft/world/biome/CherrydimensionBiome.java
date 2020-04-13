
package net.mcreator.yokraft.world.biome;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.yokraft.block.SulfurOreBlockBlock;
import net.mcreator.yokraft.YokraftElements;

import java.util.Set;
import java.util.Random;

@YokraftElements.ModElement.Tag
public class CherrydimensionBiome extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:cherrydimension")
	public static final CustomBiome biome = null;
	public CherrydimensionBiome(YokraftElements instance) {
		super(instance, 44);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new CustomBiome());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, 5));
	}
	static class CustomBiome extends Biome {
		public CustomBiome() {
			super(new Biome.Builder().downfall(0.5f).depth(0.1f).scale(0.2f).temperature(0.5f).precipitation(Biome.RainType.RAIN)
					.category(Biome.Category.PLAINS).waterColor(4159204).waterFogColor(329011).parent("forest")
					.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(),
							Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())));
			setRegistryName("cherrydimension");
			DefaultBiomeFeatures.addCarvers(this);
			DefaultBiomeFeatures.addStructures(this);
			DefaultBiomeFeatures.addMonsterRooms(this);
			DefaultBiomeFeatures.addOres(this);
			DefaultBiomeFeatures.addLakes(this);
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DEFAULT_FLOWER,
					IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(4)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS,
					new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(4)));
			addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(new CustomTreeFeature(),
					IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(3, 0.1F, 1)));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.FOX, 15, 1, 5));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.WOLF, 15, 1, 5));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.TROPICAL_FISH, 15, 1, 5));
			this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.HORSE, 15, 1, 5));
		}
	}

	static class CustomTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
		CustomTreeFeature() {
			super(NoFeatureConfig::deserialize, false);
		}

		@Override
		public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldgen, Random rand, BlockPos position, MutableBoundingBox bbox) {
			if (!(worldgen instanceof IWorld))
				return false;
			IWorld world = (IWorld) worldgen;
			int height = rand.nextInt(5) + 7;
			boolean spawnTree = true;
			if (position.getY() >= 1 && position.getY() + height + 1 <= world.getHeight()) {
				for (int j = position.getY(); j <= position.getY() + 1 + height; j++) {
					int k = 1;
					if (j == position.getY())
						k = 0;
					if (j >= position.getY() + height - 1)
						k = 2;
					for (int px = position.getX() - k; px <= position.getX() + k && spawnTree; px++) {
						for (int pz = position.getZ() - k; pz <= position.getZ() + k && spawnTree; pz++) {
							if (j >= 0 && j < world.getHeight()) {
								if (!this.isReplaceable(world, new BlockPos(px, j, pz))) {
									spawnTree = false;
								}
							} else {
								spawnTree = false;
							}
						}
					}
				}
				if (!spawnTree) {
					return false;
				} else {
					Block ground = world.getBlockState(position.add(0, -1, 0)).getBlock();
					Block ground2 = world.getBlockState(position.add(0, -2, 0)).getBlock();
					if (!((ground == Blocks.GRASS_BLOCK.getDefaultState().getBlock() || ground == Blocks.DIRT.getDefaultState().getBlock())
							&& (ground2 == Blocks.GRASS_BLOCK.getDefaultState().getBlock() || ground2 == Blocks.DIRT.getDefaultState().getBlock())))
						return false;
					BlockState state = world.getBlockState(position.down());
					if (position.getY() < world.getHeight() - height - 1) {
						setTreeBlockState(changedBlocks, world, position.down(), Blocks.DIRT.getDefaultState(), bbox);
						for (int genh = position.getY() - 3 + height; genh <= position.getY() + height; genh++) {
							int i4 = genh - (position.getY() + height);
							int j1 = (int) (1 - i4 * 0.5);
							for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
								for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
									int j2 = i2 - position.getZ();
									if (Math.abs(position.getX()) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
										BlockPos blockpos = new BlockPos(k1, genh, i2);
										state = world.getBlockState(blockpos);
										if (state.getBlock().isAir(state, world, blockpos) || state.getMaterial().blocksMovement()
												|| state.isIn(BlockTags.LEAVES) || state.getBlock() == Blocks.VINE.getDefaultState().getBlock()
												|| state.getBlock() == SulfurOreBlockBlock.block.getDefaultState().getBlock()) {
											setTreeBlockState(changedBlocks, world, blockpos, SulfurOreBlockBlock.block.getDefaultState(), bbox);
										}
									}
								}
							}
						}
						for (int genh = 0; genh < height; genh++) {
							BlockPos genhPos = position.up(genh);
							state = world.getBlockState(genhPos);
							setTreeBlockState(changedBlocks, world, genhPos, Blocks.BIRCH_LOG.getDefaultState(), bbox);
							if (state.getBlock().isAir(state, world, genhPos) || state.getMaterial().blocksMovement() || state.isIn(BlockTags.LEAVES)
									|| state.getBlock() == Blocks.VINE.getDefaultState().getBlock()
									|| state.getBlock() == SulfurOreBlockBlock.block.getDefaultState().getBlock()) {
							}
						}
						if (rand.nextInt(4) == 0 && height > 5) {
							for (int hlevel = 0; hlevel < 2; hlevel++) {
								for (Direction Direction : Direction.Plane.HORIZONTAL) {
									if (rand.nextInt(4 - hlevel) == 0) {
										Direction dir = Direction.getOpposite();
										setTreeBlockState(changedBlocks, world, position.add(dir.getXOffset(), height - 5 + hlevel, dir.getZOffset()),
												Blocks.OAK_LEAVES.getDefaultState(), bbox);
									}
								}
							}
						}
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}

		private void addVines(IWorld world, BlockPos pos, Set<BlockPos> changedBlocks, MutableBoundingBox bbox) {
			setTreeBlockState(changedBlocks, world, pos, Blocks.VINE.getDefaultState(), bbox);
			int i = 5;
			for (BlockPos blockpos = pos.down(); world.isAirBlock(blockpos) && i > 0; --i) {
				setTreeBlockState(changedBlocks, world, blockpos, Blocks.VINE.getDefaultState(), bbox);
				blockpos = blockpos.down();
			}
		}

		private boolean canGrowInto(Block blockType) {
			return blockType.getDefaultState().getMaterial() == Material.AIR || blockType == Blocks.BIRCH_LOG.getDefaultState().getBlock()
					|| blockType == SulfurOreBlockBlock.block.getDefaultState().getBlock()
					|| blockType == Blocks.GRASS_BLOCK.getDefaultState().getBlock() || blockType == Blocks.DIRT.getDefaultState().getBlock();
		}

		private boolean isReplaceable(IWorld world, BlockPos pos) {
			BlockState state = world.getBlockState(pos);
			return state.getBlock().isAir(state, world, pos) || canGrowInto(state.getBlock()) || !state.getMaterial().blocksMovement();
		}

		private void setTreeBlockState(Set<BlockPos> changedBlocks, IWorldWriter world, BlockPos pos, BlockState state, MutableBoundingBox mbb) {
			super.setLogState(changedBlocks, world, pos, state, mbb);
			changedBlocks.add(pos.toImmutable());
		}
	}
}
