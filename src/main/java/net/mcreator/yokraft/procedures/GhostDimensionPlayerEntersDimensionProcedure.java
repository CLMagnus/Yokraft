package net.mcreator.yokraft.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;

import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class GhostDimensionPlayerEntersDimensionProcedure extends YokraftElements.ModElement {
	public GhostDimensionPlayerEntersDimensionProcedure(YokraftElements instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GhostDimensionPlayerEntersDimension!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GhostDimensionPlayerEntersDimension!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GhostDimensionPlayerEntersDimension!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GhostDimensionPlayerEntersDimension!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		world.playSound((PlayerEntity) null, x, y, z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("yokraft:spiritsofthedarkland")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
	}
}
