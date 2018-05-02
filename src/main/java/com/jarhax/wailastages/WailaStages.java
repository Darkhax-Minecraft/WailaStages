package com.jarhax.wailastages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import mcp.mobius.waila.api.event.WailaRenderEvent;
import mcp.mobius.waila.api.event.WailaTooltipEvent;
import net.darkhax.bookshelf.lib.LoggingHelper;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "wailastages", name = "Waila Stages", version = "@VERSION@", dependencies = "required-after:bookshelf;required-after:waila;required-after:gamestages@[2.0.89,);required-after:crafttweaker", certificateFingerprint = "@FINGERPRINT@")
public class WailaStages {
    
    public static final LoggingHelper LOG = new LoggingHelper("Waila Stages");
    
    public static List<String> requiredStages = new ArrayList<>();
    public static Multimap<String, String> prefixes = HashMultimap.create();
    
    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent ev) {
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void preTooltipRender (WailaRenderEvent.Pre event) {
        
        if (!requiredStages.isEmpty() && !GameStageHelper.clientHasAnyOf(PlayerUtils.getClientPlayer(), requiredStages)) {
            
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void getTooltipText (WailaTooltipEvent event) {
        
        for (final String stage : prefixes.keySet()) {
            
            if (!GameStageHelper.clientHasStage(PlayerUtils.getClientPlayer(), stage)) {
                
                for (final Iterator<String> iterator = event.getCurrentTip().iterator(); iterator.hasNext();) {
                    
                    final String line = iterator.next();
                    
                    for (final String regex : prefixes.get(stage)) {
                        
                        if (line.startsWith(regex)) {
                            
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }
    }
}