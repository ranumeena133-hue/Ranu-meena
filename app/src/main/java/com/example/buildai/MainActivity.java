package com.example.buildai;

/* ==========================================================
 *  BUILD STUDIO CALCULATOR PRO 🧮
 *  FULLY AUTOMATED & ERROR-FREE ANDROID APPLICATION
 * ========================================================== */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.Stack;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tvExpression;
    private TextView tvResult;

    private String expression = "";
    private DecimalFormat decimalFormat = new DecimalFormat("#.########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Initialize Views
        tvExpression = findViewById(R.id.tvExpression);
        tvResult = findViewById(R.id.tvResult);

        // Set Click Listeners for Buttons
        int[] buttonIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply,
            R.id.btnDivide, R.id.btnPercent, R.id.btnBracket,
            R.id.btnClear, R.id.btnBack, R.id.btnEquals
        };

        for (int id : buttonIds) {
            View v = findViewById(id);
            if (v != null) {
                v.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnClear) {
            clearAll();
        } else if (id == R.id.btnBack) {
            backspace();
        } else if (id == R.id.btnEquals) {
            calculateFinalResult();
        } else if (id == R.id.btnBracket) {
            handleBracket();
        } else if (id == R.id.btnDot) {
            appendChar(".");
        } else if (id == R.id.btnPlus) {
            appendOperator("+");
        } else if (id == R.id.btnMinus) {
            appendOperator("-");
        } else if (id == R.id.btnMultiply) {
            appendOperator("×");
        } else if (id == R.id.btnDivide) {
            appendOperator("÷");
        } else if (id == R.id.btnPercent) {
            appendOperator("%");
        } else if (id == R.id.btn0) {
            appendDigit("0");
        } else if (id == R.id.btn1) {
            appendDigit("1");
        } else if (id == R.id.btn2) {
            appendDigit("2");
        } else if (id == R.id.btn3) {
            appendDigit("3");
        } else if (id == R.id.btn4) {
            appendDigit("4");
        } else if (id == R.id.btn5) {
            appendDigit("5");
        } else if (id == R.id.btn6) {
            appendDigit("6");
        } else if (id == R.id.btn7) {
            appendDigit("7");
        } else if (id == R.id.btn8) {
            appendDigit("8");
        } else if (id == R.id.btn9) {
            appendDigit("9");
        }
    }

    private void appendDigit(String digit) {
        expression += digit;
        updateDisplay();
        evaluateLiveResult();
    }

    private void appendOperator(String op) {
        if (expression.isEmpty()) {
            if (op.equals("-")) {
                expression = "-";
            }
            updateDisplay();
            return;
        }

        char lastChar = expression.charAt(expression.length() - 1);
        if (isOperator(lastChar)) {
            // Replace previous operator
            expression = expression.substring(0, expression.length() - 1) + op;
        } else {
            expression += op;
        }
        updateDisplay();
    }

    private void appendChar(String ch) {
        if (ch.equals(".")) {
            if (expression.isEmpty() || isOperator(expression.charAt(expression.length() - 1))) {
                expression += "0.";
            } else if (!hasDotInCurrentToken()) {
                expression += ".";
            }
        }
        updateDisplay();
        evaluateLiveResult();
    }

    private void handleBracket() {
        int openBrackets = 0;
        int closeBrackets = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') openBrackets++;
            if (c == ')') closeBrackets++;
        }

        if (expression.isEmpty()) {
            expression += "(";
        } else {
            char last = expression.charAt(expression.length() - 1);
            if (openBrackets > closeBrackets && (Character.isDigit(last) || last == ')')) {
                expression += ")";
            } else if (isOperator(last) || last == '(') {
                expression += "(";
            } else if (Character.isDigit(last)) {
                expression += "×(";
            } else {
                expression += "(";
            }
        }
        updateDisplay();
        evaluateLiveResult();
    }

    private boolean hasDotInCurrentToken() {
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (c == '.') return true;
            if (isOperator(c) || c == '(' || c == ')') return false;
        }
        return false;
    }

    private void clearAll() {
        expression = "";
        tvExpression.setText("");
        tvResult.setText("0");
    }

    private void backspace() {
        if (!expression.isEmpty()) {
            expression = expression.substring(0, expression.length() - 1);
            updateDisplay();
            if (expression.isEmpty()) {
                tvResult.setText("0");
            } else {
                evaluateLiveResult();
            }
        }
    }

    private void updateDisplay() {
        tvExpression.setText(expression);
    }

    private void evaluateLiveResult() {
        if (expression.isEmpty()) return;
        try {
            double res = evaluateExpression(expression);
            if (!Double.isNaN(res) && !Double.isInfinite(res)) {
                tvResult.setText(formatResult(res));
            }
        } catch (Exception e) {
            // Live eval ignores syntax errors until equals is pressed
        }
    }

    private void calculateFinalResult() {
        if (expression.isEmpty()) return;
        try {
            double res = evaluateExpression(expression);
            if (Double.isInfinite(res)) {
                tvResult.setText("Can't divide by 0");
            } else if (Double.isNaN(res)) {
                tvResult.setText("Error");
            } else {
                String formatted = formatResult(res);
                tvResult.setText(formatted);
                expression = formatted;
                tvExpression.setText("");
            }
        } catch (Exception e) {
            tvResult.setText("Error");
        }
    }

    private String formatResult(double val) {
        if (val == (long) val) {
            return String.format("%d", (long) val);
        } else {
            return decimalFormat.format(val);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '×' || c == '÷' || c == '%';
    }

    // Mathematical Expression Parser (Dijkstra / Shunting Yard algorithm)
    private double evaluateExpression(String expr) throws Exception {
        String cleanExpr = expr.replace("×", "*").replace("÷", "/");
        return parseAndEval(cleanExpr);
    }

    private double parseAndEval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) return Double.NaN;
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) {
                        double div = parseFactor();
                        if (div == 0) return Double.POSITIVE_INFINITY;
                        x /= div;
                    }
                    else if (eat('%')) x %= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    return Double.NaN;
                }

                return x;
            }
        }.parse();
    }
}
