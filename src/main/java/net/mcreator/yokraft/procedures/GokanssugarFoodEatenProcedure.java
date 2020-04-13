package net.mcreator.yokraft.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class GokanssugarFoodEatenProcedure extends YokraftElements.ModElement {
	public GokanssugarFoodEatenProcedure(YokraftElements instance) {
		super(instance, 47);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GokanssugarFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 120, (int) 1));
	}
}
