package com.jarhax.wailastages.compat.crt;

import com.jarhax.wailastages.WailaStages;

import minetweaker.IUndoableAction;

public class ActionAddRequiredStage implements IUndoableAction {

	private final String stage;

	public ActionAddRequiredStage(String stage) {

		this.stage = stage;
	}

	@Override
	public void apply() {

		WailaStages.requiredStages.add(this.stage);
	}

	@Override
	public String describe() {

		return String.format("Added possible waila stage requirement %s", this.stage);
	}

	@Override
	public void undo() {

		WailaStages.requiredStages.remove(this.stage);
	}

	@Override
	public String describeUndo() {

		return String.format("Removed waila stage requirement %s", this.stage);
	}

	@Override
	public boolean canUndo() {

		return true;
	}

	@Override
	public Object getOverrideKey() {

		return null;
	}
}
