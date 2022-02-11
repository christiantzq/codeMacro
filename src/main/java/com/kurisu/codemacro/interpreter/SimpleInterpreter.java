package com.kurisu.codemacro.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.List;

import com.kurisu.codemacro.exceptions.BadSyntaxException;
import com.kurisu.codemacro.exceptions.InstructionException;
import com.kurisu.codemacro.instructions.Instruction;
import com.kurisu.codemacro.instructions.codeblocks.Function;

/**
 * Opens the script as a buffer to be read line by line,
 * then converts each line into StreamTokenizer tokens,
 * which are collected and converted by our CustomTokenizer
 * into our Token type, which will contain all data required
 * to convert each token into script instructions.
 */
public class SimpleInterpreter implements CanInterprete {

    private static final int QUOTE_CHARACTER = '\'';
    private static final int DOUBLE_QUOTE_CHARACTER = '"';
    private static final int COMMENT_CHARACTER = '#';
    private static final int MINUS_SIGN = '-';
    private static final int DIVIDE_SIGN = '/';

    @Override
    public Function interprete(String scriptPath) throws BadSyntaxException, IOException, InstructionException {
        List<Token> tokens = tokenizeScript(scriptPath);
        return getMainFunction(tokens);
    }

    private Function getMainFunction(List<Token> tokens) throws BadSyntaxException {
        Function fn = new Function("main", new String[0]);
        int startToken = 0;
        while (startToken < tokens.size()) {
            int endToken = findEndingToken(tokens, startToken);
            fn.addInstruction(generateInstruction(tokens, startToken, endToken));
            startToken = endToken + 1;
        }
        return fn;
    }

    /**
     * Find the last index of the token included in an Instruction
     * 
     * @throws BadSyntaxException
     */
    private int findEndingToken(List<Token> tokens, int startToken) throws BadSyntaxException {
        Token token = tokens.get(startToken);
        if (token.getTokenType() == TokenType.FUNCTION_DEFINITION
                || token.getTokenType() == TokenType.IF_BLOCK
                || token.getTokenType() == TokenType.WHILE_BLOCK
                || token.getTokenType() == TokenType.FOR_BLOCK) { // This does not allow for bracketless Blocks
            return findNextTokenType(tokens, startToken, TokenType.CLOSE_CURLY_BRACKET);
        }
        return findNextLineToken(tokens, startToken);
    }

    /**
     * Find the first token of the next line and return the inmediate previous.
     * It returns the last if reached EoF
     */
    private int findNextLineToken(List<Token> tokens, int startToken) {
        int endToken = startToken;
        while (tokens.get(endToken).getLineNo() == tokens.get(startToken).getLineNo()) {
            if(endToken == tokens.size() - 1)
                return endToken;
            endToken++;
        }
        return endToken - 1;
    }

    /**
     * Finds the next token of given type in the list or dies trying
     */
    private int findNextTokenType(List<Token> tokens, int startToken, TokenType tokenType) throws BadSyntaxException {
        int endToken = startToken + 1;
        try {
            while (tokens.get(endToken).getTokenType() != tokenType)
                endToken++;
        } catch (Exception e) {
            throw new BadSyntaxException("Malformed syntax, missing [" + tokenType.name() + "] after line ["
                    + tokens.get(startToken).getLineNo() + "].");
        }
        return endToken;
    }

    private Instruction generateInstruction(List<Token> tokens, int startToken, int endToken) {
        System.out.println("# # # # # Instruction # # # # #");
        for (int i = startToken; i <= endToken; i++) {
            Token token = tokens.get(i);
            String str = token.getStringVal() == null ? "" : " => " + token.getStringVal();
            System.out.println(token.getTokenType().name() + str);
        }
        return null;
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
     * StreamTokenizer configuration
     */
    private StreamTokenizer getTokenizer(String line) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(line));
        tokenizer.lowerCaseMode(true);
        tokenizer.quoteChar(DOUBLE_QUOTE_CHARACTER);
        tokenizer.quoteChar(QUOTE_CHARACTER);
        tokenizer.ordinaryChar(MINUS_SIGN); // Don't parse minus as part of numbers.
        tokenizer.ordinaryChar(DIVIDE_SIGN); // Don't treat slash as a comment start.
        tokenizer.commentChar(COMMENT_CHARACTER);
        return tokenizer;
    }

}
