package com.jarhax.wailastages.compat.crt;

import minetweaker.MineTweakerAPI;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.WailaStages")
public class WailaStagesCrT {

	@ZenMethod
	public static void addWailaStage(String stage) {

		MineTweakerAPI.apply(new ActionAddRequiredStage(stage));
	}

	@ZenMethod
	public static void addRequirement(String stage, String prefix) {

		MineTweakerAPI.apply(new ActionAddRestriction(stage, prefix));
	}
}
