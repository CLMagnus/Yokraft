
package net.mcreator.yokraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.yokraft.itemgroup.YokraftFoodItemGroup;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class RawTofuItem extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:rawtofu")
	public static final Item block = null;
	public RawTofuItem(YokraftElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(YokraftFoodItemGroup.tab).maxStackSize(16)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).setAlwaysEdible().build()));
			setRegistryName("rawtofu");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 40;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
