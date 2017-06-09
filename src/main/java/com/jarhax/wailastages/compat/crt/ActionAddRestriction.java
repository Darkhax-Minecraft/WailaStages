package com.jarhax.wailastages.compat.crt;

import com.jarhax.wailastages.WailaStages;

import minetweaker.IUndoableAction;

public class ActionAddRestriction implements IUndoableAction {

	private final String stage;
	private final String prefix;

	public ActionAddRestriction(String stage, String prefix) {

		this.stage = stage;
		this.prefix = prefix;
	}

	@Override
	public void apply() {

		WailaStages.prefixes.put(this.stage, this.prefix);
	}

	@Override
	public String describe() {

		return String.format("Added restriction %s to stage %s", this.prefix, this.stage);
	}

	@Override
	public void undo() {

		WailaStages.prefixes.remove(this.stage, this.prefix);
	}

	@Override
	public String describeUndo() {

		return String.format("Removed restriction %s from stage %s", this.prefix, this.stage);
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
