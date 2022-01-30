package com.kurisu.codemacro.instructions.robot;

import static java.awt.event.KeyEvent.*;

import java.awt.Robot;

import com.kurisu.codemacro.exceptions.InstructionException;

/**
 * TODO: This is legacy code, needs revamp.
 */
public class LegacyTypingHelper {

    private Robot robot = null;

    public LegacyTypingHelper() throws InstructionException {
        this.robot = CodeBot.getBot();
    }

    public void type(CharSequence characters) throws InstructionException {
        // Save current auto delay and set to default
        int userAutoDelay = robot.getAutoDelay();
        robot.setAutoDelay(1); // 1 Millisecond delay

        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type(character);
        }

        // restore user's auto delay
        robot.setAutoDelay(userAutoDelay);
    }

    public void type(char character) throws InstructionException {
        switch (character) {
            case 'a':
                doType(VK_A);
                break;
            case 'b':
                doType(VK_B);
                break;
            case 'c':
                doType(VK_C);
                break;
            case 'd':
                doType(VK_D);
                break;
            case 'e':
                doType(VK_E);
                break;
            case 'f':
                doType(VK_F);
                break;
            case 'g':
                doType(VK_G);
                break;
            case 'h':
                doType(VK_H);
                break;
            case 'i':
                doType(VK_I);
                break;
            case 'j':
                doType(VK_J);
                break;
            case 'k':
                doType(VK_K);
                break;
            case 'l':
                doType(VK_L);
                break;
            case 'm':
                doType(VK_M);
                break;
            case 'n':
                doType(VK_N);
                break;
            case 'o':
                doType(VK_O);
                break;
            case 'p':
                doType(VK_P);
                break;
            case 'q':
                doType(VK_Q);
                break;
            case 'r':
                doType(VK_R);
                break;
            case 's':
                doType(VK_S);
                break;
            case 't':
                doType(VK_T);
                break;
            case 'u':
                doType(VK_U);
                break;
            case 'v':
                doType(VK_V);
                break;
            case 'w':
                doType(VK_W);
                break;
            case 'x':
                doType(VK_X);
                break;
            case 'y':
                doType(VK_Y);
                break;
            case 'z':
                doType(VK_Z);
                break;
            case 'A':
                doType(VK_SHIFT, VK_A);
                break;
            case 'B':
                doType(VK_SHIFT, VK_B);
                break;
            case 'C':
                doType(VK_SHIFT, VK_C);
                break;
            case 'D':
                doType(VK_SHIFT, VK_D);
                break;
            case 'E':
                doType(VK_SHIFT, VK_E);
                break;
            case 'F':
                doType(VK_SHIFT, VK_F);
                break;
            case 'G':
                doType(VK_SHIFT, VK_G);
                break;
            case 'H':
                doType(VK_SHIFT, VK_H);
                break;
            case 'I':
                doType(VK_SHIFT, VK_I);
                break;
            case 'J':
                doType(VK_SHIFT, VK_J);
                break;
            case 'K':
                doType(VK_SHIFT, VK_K);
                break;
            case 'L':
                doType(VK_SHIFT, VK_L);
                break;
            case 'M':
                doType(VK_SHIFT, VK_M);
                break;
            case 'N':
                doType(VK_SHIFT, VK_N);
                break;
            case 'O':
                doType(VK_SHIFT, VK_O);
                break;
            case 'P':
                doType(VK_SHIFT, VK_P);
                break;
            case 'Q':
                doType(VK_SHIFT, VK_Q);
                break;
            case 'R':
                doType(VK_SHIFT, VK_R);
                break;
            case 'S':
                doType(VK_SHIFT, VK_S);
                break;
            case 'T':
                doType(VK_SHIFT, VK_T);
                break;
            case 'U':
                doType(VK_SHIFT, VK_U);
                break;
            case 'V':
                doType(VK_SHIFT, VK_V);
                break;
            case 'W':
                doType(VK_SHIFT, VK_W);
                break;
            case 'X':
                doType(VK_SHIFT, VK_X);
                break;
            case 'Y':
                doType(VK_SHIFT, VK_Y);
                break;
            case 'Z':
                doType(VK_SHIFT, VK_Z);
                break;
            case '0':
                doType(VK_0);
                break;
            case '1':
                doType(VK_1);
                break;
            case '2':
                doType(VK_2);
                break;
            case '3':
                doType(VK_3);
                break;
            case '4':
                doType(VK_4);
                break;
            case '5':
                doType(VK_5);
                break;
            case '6':
                doType(VK_6);
                break;
            case '7':
                doType(VK_7);
                break;
            case '8':
                doType(VK_8);
                break;
            case '9':
                doType(VK_9);
                break;
            case '`':
                ascii(VK_NUMPAD9, VK_NUMPAD6);
                break;
            case '-':
                ascii(VK_NUMPAD4, VK_NUMPAD5);
                break;
            case '=':
                ascii(VK_NUMPAD6, VK_NUMPAD1);
                break;
            case '~':
                ascii(VK_NUMPAD1, VK_NUMPAD2, VK_NUMPAD6);
                break;
            case '!':
                ascii(VK_NUMPAD3, VK_NUMPAD3);
                break;
            case '@':
                ascii(VK_NUMPAD6, VK_NUMPAD4);
                break;
            case '#':
                ascii(VK_NUMPAD3, VK_NUMPAD5);
                break;
            case '$':
                ascii(VK_NUMPAD3, VK_NUMPAD6);
                break;
            case '%':
                ascii(VK_NUMPAD3, VK_NUMPAD7);
                break;
            case '^':
                ascii(VK_NUMPAD9, VK_NUMPAD4);
                break;
            case '&':
                ascii(VK_NUMPAD3, VK_NUMPAD8);
                break;
            case '*':
                ascii(VK_NUMPAD4, VK_NUMPAD2);
                break;
            case '(':
                ascii(VK_NUMPAD4, VK_NUMPAD0);
                break;
            case ')':
                ascii(VK_NUMPAD4, VK_NUMPAD1);
                break;
            case '_':
                ascii(VK_NUMPAD9, VK_NUMPAD5);
                break;
            case '+':
                ascii(VK_NUMPAD4, VK_NUMPAD3);
                break;
            case '\t':
                doType(VK_TAB);
                break;
            case '\n':
                doType(VK_ENTER);
                break;
            case '[':
                ascii(VK_NUMPAD9, VK_NUMPAD1);
                break;
            case ']':
                ascii(VK_NUMPAD9, VK_NUMPAD3);
                break;
            case '\\':
                ascii(VK_NUMPAD9, VK_NUMPAD2);
                break;
            case '{':
                ascii(VK_NUMPAD1, VK_NUMPAD2, VK_NUMPAD3);
                break;
            case '}':
                ascii(VK_NUMPAD1, VK_NUMPAD2, VK_NUMPAD5);
                break;
            case '|':
                ascii(VK_NUMPAD1, VK_NUMPAD2, VK_NUMPAD4);
                break;
            case ';':
                ascii(VK_NUMPAD5, VK_NUMPAD9);
                break;
            case ':':
                ascii(VK_NUMPAD5, VK_NUMPAD8);
                break;
            case '\'':
                ascii(VK_NUMPAD3, VK_NUMPAD9);
                break;
            case '"':
                ascii(VK_NUMPAD3, VK_NUMPAD4);
                break;
            case ',':
                ascii(VK_NUMPAD4, VK_NUMPAD4);
                break;
            case '<':
                ascii(VK_NUMPAD6, VK_NUMPAD0);
                break;
            case '.':
                ascii(VK_NUMPAD4, VK_NUMPAD6);
                break;
            case '>':
                ascii(VK_NUMPAD6, VK_NUMPAD2);
                break;
            case '/':
                ascii(VK_NUMPAD4, VK_NUMPAD7);
                break;
            case '?':
                ascii(VK_NUMPAD6, VK_NUMPAD3);
                break;
            case ' ':
                doType(VK_SPACE);
                break;
            case '¿':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD8);
                break;
            case '¡':
                ascii(VK_NUMPAD1, VK_NUMPAD7, VK_NUMPAD3);
                break;
            case 'á':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD0);
                break;
            case 'é':
                ascii(VK_NUMPAD1, VK_NUMPAD3, VK_NUMPAD0);
                break;
            case 'í':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD1);
                break;
            case 'ó':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD2);
                break;
            case 'ú':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD3);
                break;
            case 'ñ':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD4);
                break;
            case 'Ñ':
                ascii(VK_NUMPAD1, VK_NUMPAD6, VK_NUMPAD5);
                break;
            default:
                throw new InstructionException("Cannot type [" + character + "] character.");
        }
    }

    private void ascii(int d1, int d2) {
        robot.keyPress(VK_ALT);

        robot.keyPress(d1);
        robot.keyRelease(d1);

        robot.keyPress(d2);
        robot.keyRelease(d2);

        robot.keyRelease(VK_ALT);
    }

    private void ascii(int d1, int d2, int d3) {
        robot.keyPress(VK_ALT);

        robot.keyPress(d1);
        robot.keyRelease(d1);

        robot.keyPress(d2);
        robot.keyRelease(d2);

        robot.keyPress(d3);
        robot.keyRelease(d3);

        robot.keyRelease(VK_ALT);
    }

    private void doType(int... keyCodes) {
        doType(keyCodes, 0, keyCodes.length);
    }

    private void doType(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }
        robot.keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
        robot.keyRelease(keyCodes[offset]);
    }

}
