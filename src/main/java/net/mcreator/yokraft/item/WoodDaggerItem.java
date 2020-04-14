
package net.mcreator.yokraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.yokraft.itemgroup.YokraftToolsItemGroup;
import net.mcreator.yokraft.YokraftElements;

@YokraftElements.ModElement.Tag
public class WoodDaggerItem extends YokraftElements.ModElement {
	@ObjectHolder("yokraft:wooddagger")
	public static final Item block = null;
	public WoodDaggerItem(YokraftElements instance) {
		super(instance, 46);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 30;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 0.5f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 3, -2f, new Item.Properties().group(YokraftToolsItemGroup.tab)) {
		}.setRegistryName("wooddagger"));
	}
}
