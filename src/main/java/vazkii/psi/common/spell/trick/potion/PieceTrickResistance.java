/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: http://psi.vazkii.us/license.php
 *
 * File Created @ [08/02/2016, 20:19:58 (GMT)]
 */
package vazkii.psi.common.spell.trick.potion;

import net.minecraft.potion.Potion;
import vazkii.psi.api.spell.Spell;

public class PieceTrickResistance extends PieceTrickPotionBase {

	public PieceTrickResistance(Spell spell) {
		super(spell);
	}

	@Override
	public Potion getPotion() {
		return Potion.resistance;
	}

	@Override
	public int getPotency(int power, int time) {
		return power * power * power * time * 5;
	}

}
