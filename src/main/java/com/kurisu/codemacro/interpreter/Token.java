package com.kurisu.codemacro.interpreter;

/**
 * Script will end up tokenized as this Object
 * 
 * This will allow to have a collection of tokens with all required information
 * to generate instructions
 */
public class Token {
    private TokenType tokenType;
    private String s_val;
    private Double d_val;
    private Integer keyCode;
    private int lineNo;

    public Token(TokenType tokenType, int lineNo) { // Commands
        this.tokenType = tokenType;
        this.lineNo = lineNo;
    }

    public Token(String s_val, int lineNo) { // String Literals
        this.tokenType = TokenType.STRING_LITERAL;
        this.s_val = s_val;
        this.lineNo = lineNo;
    }

    public Token(Integer keyCode, int lineNo) { // Keystrokes
        this.tokenType = TokenType.KEY_CODE;
        this.keyCode = keyCode;
        this.lineNo = lineNo;
    }

    public Token(Double d_val, int lineNo) { // Number Literals
        this.tokenType = TokenType.NUMBER_LITERAL;
        this.d_val = d_val;
        this.lineNo = lineNo;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getStringVal() {
        return s_val;
    }

    public Double getNumericVal() {
        return d_val;
    }

    public Integer getKeyCode() {
        return keyCode;
    }

    public int getLineNo() {
        return lineNo;
    }

}
