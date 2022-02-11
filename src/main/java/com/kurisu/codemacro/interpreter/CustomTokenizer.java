package com.kurisu.codemacro.interpreter;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.exceptions.InstructionException;

/**
 * Uses tokens from a StreamTokenizer to generate our custom Tokens
 */
public class CustomTokenizer {
    private static final int SINGLE_QUOTE_CHARACTER = '\'';
    private static final int DOUBLE_QUOTE_CHARACTER = '"';

    private List<Token> tokens;
    private ReservedWordsAndSymbols wordsAndSymbols;

    public CustomTokenizer() {
        tokens = new ArrayList<>();
        wordsAndSymbols = ReservedWordsAndSymbols.getInstance();
    }

    public void addTokens(StreamTokenizer tokenizer, int lineNo)
            throws IOException, InstructionException, BadSyntaxException {
        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_WORD:
                    tokens.add(getTokenFromWord(tokenizer.sval, lineNo));
                    break;
                case StreamTokenizer.TT_NUMBER:
                    tokens.add(new Token(tokenizer.nval, lineNo)); // Overload for Number Literals
                    break;
                case SINGLE_QUOTE_CHARACTER:
                    tokens.add(new Token(tokenizer.sval, lineNo)); // Overload for String literals
                    break;
                case DOUBLE_QUOTE_CHARACTER:
                    tokens.add(new Token(tokenizer.sval, lineNo)); // Overload for String literals
                    break;
                default:
                    tokens.add(getTokenFromChar((char) tokenizer.ttype, lineNo));
            }
        }
    }

    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    private Token getTokenFromWord(String word, int lineNo) throws InstructionException {
        ReservedWordsAndSymbolsWrapper wrapper = wordsAndSymbols.getWrapper(word);
        if (wrapper.getTokenType() == TokenType.KEY_CODE)
            return new Token(wrapper.getKeyCode(), lineNo); // Overload for keystrokes
        return new Token(wrapper.getTokenType(), lineNo); // Overload for commands and user defined words
    }

    private Token getTokenFromChar(Character c, int lineNo) throws BadSyntaxException {
        ReservedWordsAndSymbolsWrapper wrapper = wordsAndSymbols.getWrapper(c);
        if (wrapper.getTokenType() == TokenType.UNDEFINED)
            throw new BadSyntaxException("Character [" + c + "] in line [" + lineNo + "] is not recognized.");
        return new Token(wrapper.getTokenType(), lineNo); // Overload for symbols
    }

}