package com.kurisu.codemacro.interpreter;

public class ReservedWordsAndSymbolsWrapper {
    private TokenType tokenType;
    private Integer keyCode;

    public ReservedWordsAndSymbolsWrapper(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public ReservedWordsAndSymbolsWrapper(TokenType tokenType, Integer keyCode) {
        this.tokenType = tokenType;
        this.keyCode = keyCode;
    }

    public TokenType getTokenType() {
        return this.tokenType;
    }

    public Integer getKeyCode() {
        return this.keyCode;
    }

}
