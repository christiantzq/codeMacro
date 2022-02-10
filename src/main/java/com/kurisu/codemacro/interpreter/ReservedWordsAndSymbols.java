package com.kurisu.codemacro.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyEvent;

public class ReservedWordsAndSymbols {
    private static ReservedWordsAndSymbols instance;

    private Map<String, Integer> keyWords;
    private Map<String, TokenType> commands;
    private Map<Character, TokenType> symbols;

    private ReservedWordsAndSymbols() {
        keyWords = new HashMap<>();
        defineKeyboardWords();
        commands = new HashMap<>();
        defineCommandWords();
        symbols = new HashMap<>();
        defineSymbols();
    }

    public static ReservedWordsAndSymbols getInstance() {
        if (instance == null) {
            instance = new ReservedWordsAndSymbols();
        }
        return instance;
    }

    public ReservedWordsAndSymbolsWrapper getWrapper(String word){
        if(commands.containsKey(word)){
            return new ReservedWordsAndSymbolsWrapper(commands.get(word));
        }
        if(keyWords.containsKey(word)){
            return new ReservedWordsAndSymbolsWrapper(TokenType.KEY_CODE, keyWords.get(word));
        }
        return new ReservedWordsAndSymbolsWrapper(TokenType.UNDEFINED);
    }

    public ReservedWordsAndSymbolsWrapper getWrapper(Character c){
        if(symbols.containsKey(c)){
            return new ReservedWordsAndSymbolsWrapper(symbols.get(c));
        }
        return new ReservedWordsAndSymbolsWrapper(TokenType.UNDEFINED);
    }

    private void defineCommandWords() {
        commands.put("wait", TokenType.WAIT_COMMAND);
        commands.put("type", TokenType.TYPE_COMMAND);
        commands.put("if", TokenType.IF_BLOCK);
        commands.put("while", TokenType.WHILE_BLOCK);
        commands.put("for", TokenType.FOR_BLOCK);
        commands.put("fn", TokenType.FUNCTION_DEFINITION);
        commands.put("global", TokenType.GLOBAL_CONSTANT);
    }

    private void defineSymbols(){
        symbols.put(',', TokenType.COMMA_CHAR);
        symbols.put('+', TokenType.PLUS_CHAR);
        symbols.put('-', TokenType.MINUS_CHAR);
        symbols.put('*', TokenType.MUITIPLY_CHAR);
        symbols.put('/', TokenType.DIVIDE_CHAR);
        symbols.put('%', TokenType.MODULE_CHAR);
        symbols.put('(', TokenType.OPEN_PARETHESIS);
        symbols.put(')', TokenType.CLOSE_PARETHESIS);
        symbols.put('{', TokenType.OPEN_CURLY_BRACKET);
        symbols.put('}', TokenType.CLOSE_CURLY_BRACKET);
        symbols.put('>', TokenType.GREATER_THAN_CHAR);
        symbols.put('<', TokenType.LESS_THAN_CHAR);
        symbols.put('=', TokenType.EQUALS_CHAR);
        symbols.put('|', TokenType.PIPE_CHAR);
        symbols.put('&', TokenType.AMPERSAND_CHAR);
    }

    private void defineKeyboardWords() {
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
        populateFunctionKeys();
        populateNumberKeys();
        populateAsciiAlphabet();
    }

    private void populateNumberKeys(){
        // 0 = 48, 9 = 57
        for (int i = 48; i <= 57; i++) {            
            keyWords.put("" + (i-48), i);
        }
    }

    private void populateFunctionKeys(){
        // F1 = 112, F12 = 123
        for (int i = 112; i <= 123; i++) {            
            keyWords.put("F" + (i-111), i);
        }
    }

    private void populateAsciiAlphabet() {
        // 'A' = 65, 'Z' = 90
        for (int i = 65; i <= 90; i++) {
            char lowercase = (char) (i + 32);
            keyWords.put(String.valueOf(lowercase), i);
        }
    }

}
