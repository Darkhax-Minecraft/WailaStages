package com.jarhax.wailastages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import mcp.mobius.waila.api.event.WailaRenderEvent;
import mcp.mobius.waila.api.event.WailaTooltipEvent;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.darkhax.gamestages.capabilities.PlayerDataHandler;
import net.darkhax.gamestages.capabilities.PlayerDataHandler.IStageData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "wailastages", name = "Waila Stages", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.1.427,);required-after:waila@[1.8.19,);required-after:gamestages@[1.0.12,);required-after:crafttweaker@[4.0.1.,)", acceptedMinecraftVersions = "[1.12,1.12.2)")
public class WailaStages {

    public static List<String> requiredStages = new ArrayList<>();
    public static Multimap<String, String> prefixes = HashMultimap.create();

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent ev) {

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void preTooltipRender (WailaRenderEvent.Pre event) {

        if (!requiredStages.isEmpty()) {

            final IStageData stageData = PlayerDataHandler.getStageData(PlayerUtils.getClientPlayer());

            boolean shouldLock = true;

            for (final String requiredStage : requiredStages) {

                if (stageData.hasUnlockedStage(requiredStage)) {

                    shouldLock = false;
                }
            }

            if (shouldLock) {

                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void getTooltipText (WailaTooltipEvent event) {

        final IStageData stageData = PlayerDataHandler.getStageData(PlayerUtils.getClientPlayer());

        for (final String stage : prefixes.keySet()) {

            if (!stageData.hasUnlockedStage(stage)) {

                for (final Iterator<String> iterator = event.getCurrentTip().iterator(); iterator.hasNext();) {

                    final String line = iterator.next();
                    boolean hasRemoved = false;

                    for (final String regex : prefixes.get(stage)) {

                        if (hasRemoved) {

                            break;
                        }

                        if (line.startsWith(regex)) {

                            iterator.remove();
                            hasRemoved = true;
                        }
                    }
                }
            }
        }
    }
}
