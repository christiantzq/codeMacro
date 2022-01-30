package com.kurisu.codemacro.instructions.robot;

import java.awt.AWTException;
import java.awt.Robot;

import com.kurisu.codemacro.exceptions.InstructionException;

public class CodeBot {
    private static Robot robot;

    private CodeBot() {
    }

    public static Robot getBot() throws InstructionException {
        if (robot != null) {
            return robot;
        }
        try {
            return robot = new Robot();
        } catch (AWTException e) {
            throw new InstructionException("System Error. Bot cannot be initialized.");
        }
    }

}
