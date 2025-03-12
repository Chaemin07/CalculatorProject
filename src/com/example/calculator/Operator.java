package com.example.calculator;

public enum Operator {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    // 몫
    QUOTIENT("/"),
    // 나머지
    REMAINDER("%"),
    POWER("^");
    private final String symbol;
    // 생성자
    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    // 기호를 기반으로 Operator 찾기
    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        return null;
    }
}
