package com.kurisu.codemacro.interpreter;

import java.io.IOException;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.instructions.codeblocks.Function;

public interface CanInterprete {
    Function interprete(String scriptPath) throws BadSyntaxException, IOException, InstructionException;
}
