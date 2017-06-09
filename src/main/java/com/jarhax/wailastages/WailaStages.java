package com.jarhax.wailastages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jarhax.wailastages.compat.crt.WailaStagesCrT;

import mcp.mobius.waila.api.event.WailaRenderEvent;
import mcp.mobius.waila.api.event.WailaTooltipEvent;
import minetweaker.MineTweakerAPI;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.darkhax.gamestages.capabilities.PlayerDataHandler;
import net.darkhax.gamestages.capabilities.PlayerDataHandler.IStageData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "wailastages", name = "Waila Stages", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.0.0.387,);required-after:waila@[1.8.15,);required-after:gamestages@[1.0.11,);required-after:crafttweaker@[3.0.25.,)")
public class WailaStages {

	public static List<String> requiredStages = new ArrayList<>();
	public static Multimap<String, String> prefixes = HashMultimap.create();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent ev) {

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent ev) {

		MineTweakerAPI.registerClass(WailaStagesCrT.class);
	}

	@SubscribeEvent
	public void preTooltipRender(WailaRenderEvent.Pre event) {

		IStageData stageData = PlayerDataHandler.getStageData(PlayerUtils.getClientPlayer());

		boolean shouldLock = true;

		for (String requiredStage : requiredStages) {

			if (stageData.hasUnlockedStage(requiredStage)) {

				shouldLock = false;
			}
		}

		if (shouldLock) {

			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void getTooltipText(WailaTooltipEvent event) {

		IStageData stageData = PlayerDataHandler.getStageData(PlayerUtils.getClientPlayer());

		for (String stage : prefixes.keySet()) {

			if (!stageData.hasUnlockedStage(stage)) {

				for (Iterator<String> iterator = event.getCurrentTip().iterator(); iterator.hasNext();) {

					final String line = iterator.next();

					for (String regex : prefixes.get(stage)) {

						if (line.startsWith(regex)) {

							iterator.remove();
						}
					}
				}
			}
		}
	}
}
