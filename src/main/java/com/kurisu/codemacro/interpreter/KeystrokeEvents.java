package com.kurisu.codemacro.interpreter;

import java.util.HashMap;
import java.util.Map;

import com.kurisu.codemacro.exceptions.BadSyntaxException;

import java.awt.event.KeyEvent;

/**
 * Cached map of reserved words for keyboard's keystroke events.
 */
public class KeystrokeEvents {
	private static Map<String, Integer> keyWords = new HashMap<>();

	static {
		keyWords.put("enter", KeyEvent.VK_ENTER);
		keyWords.put("shift", KeyEvent.VK_SHIFT);
		keyWords.put("control", KeyEvent.VK_CONTROL);
		keyWords.put("space", KeyEvent.VK_SPACE);
		keyWords.put("backspace", KeyEvent.VK_BACK_SPACE);
		keyWords.put("alt", KeyEvent.VK_ALT);
		keyWords.put("tab", KeyEvent.VK_TAB);
		keyWords.put("escape", KeyEvent.VK_ESCAPE);
		keyWords.put("windows", KeyEvent.VK_WINDOWS);
		keyWords.put("up", KeyEvent.VK_UP);
		keyWords.put("down", KeyEvent.VK_DOWN);
		keyWords.put("left", KeyEvent.VK_LEFT);
		keyWords.put("right", KeyEvent.VK_RIGHT);
		keyWords.put("insert", KeyEvent.VK_INSERT);
		keyWords.put("delete", KeyEvent.VK_DELETE);
		keyWords.put("home", KeyEvent.VK_HOME);
		keyWords.put("end", KeyEvent.VK_END);
		keyWords.put("pause", KeyEvent.VK_PAUSE);
		keyWords.put("pageup", KeyEvent.VK_PAGE_UP);
		keyWords.put("pagedown", KeyEvent.VK_PAGE_DOWN);
		keyWords.put("f1", KeyEvent.VK_F1);
		keyWords.put("f2", KeyEvent.VK_F2);
		keyWords.put("f3", KeyEvent.VK_F3);
		keyWords.put("f4", KeyEvent.VK_F4);
		keyWords.put("f5", KeyEvent.VK_F5);
		keyWords.put("f6", KeyEvent.VK_F6);
		keyWords.put("f7", KeyEvent.VK_F7);
		keyWords.put("f8", KeyEvent.VK_F8);
		keyWords.put("f9", KeyEvent.VK_F9);
		keyWords.put("f10", KeyEvent.VK_F10);
		keyWords.put("f11", KeyEvent.VK_F11);
		keyWords.put("f12", KeyEvent.VK_F12);
		keyWords.put("0", KeyEvent.VK_0);
		keyWords.put("1", KeyEvent.VK_1);
		keyWords.put("2", KeyEvent.VK_2);
		keyWords.put("3", KeyEvent.VK_3);
		keyWords.put("4", KeyEvent.VK_4);
		keyWords.put("5", KeyEvent.VK_5);
		keyWords.put("6", KeyEvent.VK_6);
		keyWords.put("7", KeyEvent.VK_7);
		keyWords.put("8", KeyEvent.VK_8);
		keyWords.put("9", KeyEvent.VK_9);
		populateAsciiAlphabet();
	}

	/**
	 * Populates the Map with the lowercase Alphabet KeyEvent values
	 */
	static private void populateAsciiAlphabet() {
		// 'A' = 65, 'Z' = 90
		for (int i = 65; i <= 90; i++) {
			char lowercase = (char) (i + 32);
			keyWords.put(String.valueOf(lowercase), i);
		}
	}

	static public int getKeyEvent(String reservedWord) throws BadSyntaxException {
		String lowercase = reservedWord.trim().toLowerCase();
		if (keyWords.containsKey(lowercase))
			return keyWords.get(lowercase);
		throw new BadSyntaxException(
				"Requested reserved keyword [" + reservedWord + "] does not exist. Please check manual.");
	}

}
