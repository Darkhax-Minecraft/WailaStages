package com.jarhax.wailastages.compat.crt;

import com.jarhax.wailastages.WailaStages;

import crafttweaker.IAction;

public class ActionAddRestriction implements IAction {

    private final String stage;
    private final String prefix;

    public ActionAddRestriction (String stage, String prefix) {

        this.stage = stage;
        this.prefix = prefix;
    }

    @Override
    public void apply () {

        WailaStages.prefixes.put(this.stage, this.prefix);
    }

    @Override
    public String describe () {

        return String.format("Added restriction %s to stage %s", this.prefix, this.stage);
    }
}