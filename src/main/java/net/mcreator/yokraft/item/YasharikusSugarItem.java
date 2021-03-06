
package net.mcreator.yokraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.yokraft.procedures.YasharikusSugarFoodEatenProcedure;
import net.mcreator.yokraft.itemgroup.YokraftFoodItemGroup;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class YasharikusSugarItem extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:yasharikussugar")
	public static final Item block = null;
	public YasharikusSugarItem(YokraftElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(YokraftFoodItemGroup.tab).maxStackSize(16)
					.food((new Food.Builder()).hunger(0).saturation(0.5f).setAlwaysEdible().build()));
			setRegistryName("yasharikussugar");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 20;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				YasharikusSugarFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
