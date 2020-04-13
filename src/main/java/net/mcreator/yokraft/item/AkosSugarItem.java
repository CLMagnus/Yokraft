
package net.mcreator.yokraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.yokraft.procedures.AkosSugarFoodEatenProcedure;
import net.mcreator.yokraft.itemgroup.YokraftFoodItemGroup;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class AkosSugarItem extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:akossugar")
	public static final Item block = null;
	public AkosSugarItem(YokraftElements instance) {
		super(instance, 49);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(YokraftFoodItemGroup.tab).maxStackSize(16)
					.food((new Food.Builder()).hunger(1).saturation(0.5f).setAlwaysEdible().build()));
			setRegistryName("akossugar");
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
				AkosSugarFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
