
package net.mcreator.yokraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.yokraft.item.OpenPalmItem;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class YokraftMiscItemGroup extends YokraftElements.ModElement {
	public YokraftMiscItemGroup(YokraftElements instance) {
		super(instance, 38);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabyokraftmisc") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(OpenPalmItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
