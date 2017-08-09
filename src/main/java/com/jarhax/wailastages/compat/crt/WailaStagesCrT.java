package com.jarhax.wailastages.compat.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.WailaStages")
public class WailaStagesCrT {

	@ZenMethod
	public static void addWailaStage(String stage) {

		CraftTweakerAPI.apply(new ActionAddRequiredStage(stage));
	}

	@ZenMethod
	public static void addRequirement(String stage, String prefix) {

		CraftTweakerAPI.apply(new ActionAddRestriction(stage, prefix));
	}
}
