
package net.mcreator.yokraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.yokraft.itemgroup.YokraftFoodItemGroup;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class CookedTofuItem extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:cookedtofu")
	public static final Item block = null;
	public CookedTofuItem(YokraftElements instance) {
		super(instance, 7);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(YokraftFoodItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(7).saturation(3.9999999999999996f).setAlwaysEdible().build()));
			setRegistryName("cookedtofu");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 28;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
