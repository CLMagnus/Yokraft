package net.mcreator.yokraft.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class UngosugarFoodEatenProcedure extends YokraftElements.ModElement {
	public UngosugarFoodEatenProcedure(YokraftElements instance) {
		super(instance, 46);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure UngosugarFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 180, (int) 1));
	}
}
