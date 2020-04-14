
package net.mcreator.yokraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.yokraft.item.TangsVengeanceItem;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class YokraftToolsItemGroup extends YokraftElements.ModElement {
	public YokraftToolsItemGroup(YokraftElements instance) {
		super(instance, 45);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabyokrafttools") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(TangsVengeanceItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
