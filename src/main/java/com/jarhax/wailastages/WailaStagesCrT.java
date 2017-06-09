package com.jarhax.wailastages;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.WailaStages")
public class WailaStagesCrT {

    @ZenMethod
    public static void setRequiredStage(String stage) {
        
        WailaStages.requiredStage = stage;
    }
    
    @ZenMethod
    public static void addRequirement(String stage, String regex) {
        
        WailaStages.stageRegix.put(stage, regex);
    }
}
