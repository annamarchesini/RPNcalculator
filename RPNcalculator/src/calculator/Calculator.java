package calculator;

import javax.swing.*;
import java.util.Objects;
import java.util.Stack;

public class Calculator {
    private JButton btn7;
    private JButton btn4;
    private JButton btn1;
    private JButton btn0;
    private JButton btn8;
    private JButton btn5;
    private JButton btn2;
    private JButton btnop;
    private JButton btncp;
    private JButton btn3;
    private JButton btn6;
    private JButton btn9;
    private JButton btnsum;
    private JButton btnsubtraction;
    private JButton btnmoltiplication;
    private JButton btndivision;
    private JButton btnspace;
    private JButton btncanc;
    private JButton btncovert;
    private JPanel panel;
    private JTextField TextNumber;
    private JButton btnequal;


    public Calculator() {
        btn0.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "0");
        });
        btn1.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "1");
        });
        btn2.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "2");
        });
        btn3.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "3");
        });
        btn4.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "4");
        });
        btn5.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "5");
        });
        btn6.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "6");
        });
        btn7.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "7");
        });
        btn8.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "8");
        });
        btn9.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "9");
        });
        btncanc.addActionListener(e -> {
            TextNumber.setText("");
        });
        btndivision.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "/");
        });
        btnmoltiplication.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "*");
        });
        btnsubtraction.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "-");
        });
        btnsum.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "+");
        });
        btnop.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + "(");
        });
        btncp.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + ")");
        });
        btnspace.addActionListener(e -> {
            TextNumber.setText(TextNumber.getText() + " ");
        });
        btncovert.addActionListener(e -> {
            TextNumber.setText(Convert(TextNumber.getText()));
        });


        btnequal.addActionListener(e -> {
            TextNumber.setText(Double.toString(RPNresult(TextNumber.getText())));
        });
    }

    public static double RPNresult(String RPNtext) {
        StringBuilder result = new StringBuilder();
        Stack<Double> stuff = new Stack<>();
        String[] RPNtextsplit = RPNtext.split(" ");

        for (String txt : RPNtextsplit) {
            if (isDouble(txt)) {
                stuff.push(Double.parseDouble(txt));
            } else {
                stuff.push(Calculate(stuff.pop(), txt, stuff.pop()));
            }
        }
        return stuff.pop();
    }


    public static boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static double Calculate(double num1, String op, double num2) {
        if (Objects.equals(op, "+")) {
            return num1 + num2;
        } else if (Objects.equals(op, "-")) {
            return num1 - num2;
        }
        if (Objects.equals(op, "*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    public static String Convert(String infix) {
        Stack<Character> operators = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (Character.isDigit(c) || c == ' ') {
                result.append(c);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    result.append(" ");
                    result.append(operators.pop());
                }
                result.append(" ");
                operators.pop();
            } else {

                while (!operators.isEmpty() && Prec(c) <= Prec(operators.peek())) {
                    result.append(" ");

                    result.append(operators.pop());
                }
                result.append(" ");
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            result.append(" ");
            result.append(operators.pop());
        }
        return result.toString();

    }

    private static int Prec(char c) {
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;

        }
        return -1;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

