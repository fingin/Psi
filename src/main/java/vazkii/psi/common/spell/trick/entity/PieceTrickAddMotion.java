/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: http://psi.vazkii.us/license.php
 *
 * File Created @ [18/01/2016, 22:32:11 (GMT)]
 */
package vazkii.psi.common.spell.trick.entity;

import net.minecraft.entity.Entity;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickAddMotion extends PieceTrick {

	public static final double MULTIPLIER = 0.3;

	SpellParam target;
	SpellParam direction;
	SpellParam speed;

	public PieceTrickAddMotion(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
		addParam(direction = new ParamVector("psi.spellparam.direction", SpellParam.GREEN, false, false));
		addParam(speed = new ParamNumber("psi.spellparam.speed", SpellParam.RED, false, true));
	}

	@Override
	public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
		super.addToMetadata(meta);
		Double speedVal = this.<Double>getParamEvaluation(speed);
		if(speedVal == null)
			speedVal = 1D;

		double absSpeed = Math.abs(speedVal);
		meta.addStat(EnumSpellStat.POTENCY, (int) (absSpeed * absSpeed * 3.5));
		meta.addStat(EnumSpellStat.COST, (int) (absSpeed * Math.max(1, absSpeed * 0.5) * 60));
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Entity targetVal = this.<Entity>getParamValue(context, target);
		Vector3 directionVal = this.<Vector3>getParamValue(context, direction);
		Double speedVal = this.<Double>getParamValue(context, speed);

		addMotion(context, targetVal, directionVal, speedVal);

		return null;
	}

	public static void addMotion(SpellContext context, Entity e, Vector3 dir, double speed) throws SpellRuntimeException {
		if(!context.isInRadius(e))
			throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);

		dir = dir.copy().normalize().multiply(MULTIPLIER * speed);

		String key = "psi:Entity" + e.getEntityId() + "Motion";
		
		if(Math.abs(dir.x) > 0.0001) {
			String keyv = key + "X";
			if(!context.customData.containsKey(keyv)) {
				e.motionX += dir.x;
				context.customData.put(keyv, 0);
			}
		}

		if(Math.abs(dir.y) > 0.0001) {
			String keyv = key + "Y";
			if(!context.customData.containsKey(keyv)) {
				e.motionY += dir.y;
				context.customData.put(keyv, 0);
			}
			
			if(dir.y > 0)
				e.fallDistance = 0;
		}
		
		if(Math.abs(dir.z) > 0.0001) {
			String keyv = key + "Z";
			if(!context.customData.containsKey(keyv)) {
				e.motionZ += dir.z;
				context.customData.put(keyv, 0);
			}
		}
	}

}
