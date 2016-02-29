/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [May 31, 2014, 11:43:13 PM (GMT)]
 */
package vazkii.psi.client.core.version;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import vazkii.psi.api.spell.SpellContext;

public class CommandSpellDistance extends CommandBase {

	private static final boolean ENABLED = true;

	@Override
	public String getCommandName() {
		return "psi-area-of-effect";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/psi-area-of-effect <area-of-effect>";
	}

	@SuppressWarnings("finally")
	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(ENABLED){
			try{
				SpellContext.MAX_DISTANCE = Double.parseDouble(var2[0].toString());
				var1.addChatMessage(new ChatComponentTranslation("psi.area-of-effect.worked").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
				return;
			}finally{
				var1.addChatMessage(new ChatComponentTranslation("psi.area-of-effect.failed").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
				return;
			}
		}else{
			var1.addChatMessage(new ChatComponentTranslation("psi.area-of-effect.disabled").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
		}	
	}

}