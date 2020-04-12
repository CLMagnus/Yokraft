
package net.mcreator.yokraft.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Blocks;

import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class YokraftBlockItemGroup extends YokraftElements.ModElement {
	public YokraftBlockItemGroup(YokraftElements instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabyokraftblock") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
