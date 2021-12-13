package com.kurisu.codemacro.script;

import java.util.List;

import com.kurisu.codemacro.actions.Action;

public interface CanParseScript {

    public List<Action> parseScript(String rawText);

}
