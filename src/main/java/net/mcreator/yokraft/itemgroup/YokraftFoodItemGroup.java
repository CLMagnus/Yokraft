
package net.mcreator.yokraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.yokraft.item.YasharikusSugarItem;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class YokraftFoodItemGroup extends YokraftElements.ModElement {
	public YokraftFoodItemGroup(YokraftElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabyokraftfood") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(YasharikusSugarItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
