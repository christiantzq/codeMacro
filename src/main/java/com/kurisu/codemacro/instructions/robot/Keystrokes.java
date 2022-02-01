package com.kurisu.codemacro.instructions.robot;

import java.awt.Robot;

import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.instructions.Instruction;

/**
 * Maps the script to a keystroke Instruction
 * 
 * Handles simple keyboard strokes like 'enter'
 * and combinations like 'control + c'
 */
public class Keystrokes implements Instruction {
    int[] keycodes;

    public Keystrokes(int[] keycodes) {
        this.keycodes = keycodes;
    }

    @Override
    public void execute() throws InstructionException {
        Robot bot = CodeBot.getBot();

        for (int keycode : keycodes) {
            bot.keyPress(keycode);
        }
        for (int i = keycodes.length - 1; i >= 0; i--) {
            bot.keyRelease(keycodes[i]);
        }
    }

}
