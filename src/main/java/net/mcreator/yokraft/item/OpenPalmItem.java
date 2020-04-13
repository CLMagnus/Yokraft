
package net.mcreator.yokraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class OpenPalmItem extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:openpalm")
	public static final Item block = null;
	public OpenPalmItem(YokraftElements instance) {
		super(instance, 37);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, YokraftElements.sounds.get(new ResourceLocation("yokraft:openpalm")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("openpalm");
		}
	}
}
