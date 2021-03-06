/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: http://psi.vazkii.us/license.php
 *
 * File Created @ [19/02/2016, 18:08:41 (GMT)]
 */
package vazkii.psi.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.psi.common.entity.EntitySpellCharge;
import vazkii.psi.common.item.base.ItemMod;
import vazkii.psi.common.lib.LibItemNames;

public class ItemDetonator extends ItemMod {

	public ItemDetonator() {
		super(LibItemNames.DETONATOR);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		List<EntitySpellCharge> charges = worldIn.getEntitiesWithinAABB(EntitySpellCharge.class, playerIn.getEntityBoundingBox().expand(32, 32, 32));
		if(!charges.isEmpty())
			for(EntitySpellCharge c : charges)
				c.doExplosion();

		if(!worldIn.isRemote)
			worldIn.playSoundAtEntity(playerIn, "gui.button.press", 1F, 1F);
		else playerIn.swingItem();

		return itemStackIn;
	}

}
