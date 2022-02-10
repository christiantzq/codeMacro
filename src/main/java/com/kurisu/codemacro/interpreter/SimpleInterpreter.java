package com.kurisu.codemacro.interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.List;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.instructions.codeblocks.Function;

public class SimpleInterpreter implements CanInterprete {

    private static final int QUOTE_CHARACTER = '\'';
    private static final int DOUBLE_QUOTE_CHARACTER = '"';
    private static final int COMMENT_CHARACTER = '#';
    private static final int OPEN_PARETHESIS = '(';
    private static final int CLOSE_PARETHESIS = ')';
    private static final int PLUS_SIGN = '+';
    private static final int MINUS_SIGN = '-';
    private static final int MULTIPLY_SIGN = '*';
    private static final int DIVIDE_SIGN = '/';
    private static final int COMMA = ',';
    private static final int EQUALS_SIGN = '=';
    private static final int OPEN_CURLY_BRACKET = '{';
    private static final int CLOSE_CURLY_BRACKET = '}';

    @Override
    public Function interprete(String scriptPath) throws BadSyntaxException, IOException, InstructionException {
        Function fn = new Function("main", new String[0]);

        List<Token> tokens = tokenizeScript(scriptPath);
        for (Token token : tokens) {
            String str = token.getStringVal() == null ? "" : " => " + token.getStringVal();
            System.out.println(token.getTokenType().name() + str);
        }

        return fn;
    }

    private List<Token> tokenizeScript(String scriptPath) throws BadSyntaxException, IOException, InstructionException {
        CustomTokenizer customTokenizer = new CustomTokenizer();
        BufferedReader readInput = new BufferedReader(new FileReader(scriptPath));
        int lineCounter = 0;
        String line;
        while ((line = readInput.readLine()) != null) {
            lineCounter++;
            StreamTokenizer tokenizer = getTokenizer(line);
            customTokenizer.addTokens(tokenizer, lineCounter);
        }
        readInput.close();
        return customTokenizer.getTokens();
    }

    /**
     * Tokenizer configuration
     */
    private StreamTokenizer getTokenizer(String line) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(line));
        tokenizer.quoteChar(DOUBLE_QUOTE_CHARACTER);
        tokenizer.quoteChar(QUOTE_CHARACTER);
        tokenizer.ordinaryChar(MINUS_SIGN); // Don't parse minus as part of numbers.
        tokenizer.ordinaryChar(DIVIDE_SIGN); // Don't treat slash as a comment start.
        tokenizer.commentChar(COMMENT_CHARACTER);
        return tokenizer;
    }

}
