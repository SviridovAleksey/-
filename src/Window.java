import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    float a;
    float b;
    byte action = 0;
    String aa = "0";
    String bb = "0";
    float c;
    String symbol;
    boolean hasWrite = false;
    public Window() {
        setTitle("Типо калькулятор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 700, 350);
        JButton[] baton = new JButton[18];
        setLayout(new GridLayout(6, 5));
        for (int i = 0; i < 10; i++) {
            baton[i] = new JButton("" + (i));
            add(baton[i]);
        }
        baton[10] = new JButton(" + ");  add(baton[10]);
        baton[11] = new JButton(" - ");  add(baton[11]);
        baton[12] = new JButton(" / ");  add(baton[12]);
        baton[13] = new JButton(" * ");  add(baton[13]);
        baton[14] = new JButton(" Число : 'A' ");  add(baton[14]);
        baton[15] = new JButton("Число : 'B' ");  add(baton[15]);
        baton[17] = new JButton(" = ");  add(baton[17]);
        JLabel panelka = new JLabel("Результат :");
        add(panelka);
        JLabel outputA = new JLabel("   A = " + a);
        add(outputA);
        JLabel outputB = new JLabel("   B = " + b);
        add(outputB);
        baton[16] = new JButton("Сброс");  add(baton[16]);

        baton[0].addActionListener(e -> {
            fill("0"); output(outputA, outputB);
        });
        baton[1].addActionListener(e -> {
            fill("1"); output(outputA, outputB);
        });
        baton[2].addActionListener(e -> {
            fill("2"); output(outputA, outputB);
        });
        baton[3].addActionListener(e -> {
            fill("3"); output(outputA, outputB);
        });
        baton[4].addActionListener(e -> {
            fill("4"); output(outputA, outputB);
        });
        baton[5].addActionListener(e -> {
            fill("5"); output(outputA, outputB);
        });
        baton[6].addActionListener(e -> {
            fill("6"); output(outputA, outputB);
        });
        baton[7].addActionListener(e -> {
            fill("7"); output(outputA, outputB);
        });
        baton[8].addActionListener(e -> {
            fill("8"); output(outputA, outputB);
        });
        baton[9].addActionListener(e -> {
            fill("9"); output(outputA, outputB);
        });
        baton[10].addActionListener(e -> {
            action = 1; symbol = "+";
            hasWrite = true;
        });
        baton[11].addActionListener(e -> {
            action = 2; symbol = "-";
            hasWrite = true;
        });
        baton[12].addActionListener(e -> {
            action = 3; symbol = "/";
            hasWrite = true;
        });
        baton[13].addActionListener(e -> {
            action = 4; symbol = "*";
            hasWrite = true;

        });
        baton[14].addActionListener(e ->{ hasWrite = false;
            aa = "0"; a = 0; output(outputA, outputB);
        });
        baton[15].addActionListener(e ->{ hasWrite = true;
            bb = "0"; b = 0; output(outputA, outputB);
        });
        baton[16].addActionListener(e -> {
            a = 0; b = 0; aa = "0"; bb = "0";action = 0;
            output(outputA, outputB);
            panelka.setText("Результат :");
        });
        baton[17].addActionListener(e -> {
            action(action); outputPanel(panelka, symbol);
            output(outputA, outputB);
        });
        setVisible(true);
    }
    void output(JLabel outputA,JLabel outputB) {
        outputA.setText("   A = " + a);
        outputB.setText("   B = " + b);
    }
    void outputPanel(JLabel panelka, String symbol){
        panelka.setText("Результат : A " + symbol + " B = " + c);
        //  a = 0; b = 0;
        hasWrite = false;
    }
    void fill(String i){
        if (!hasWrite) {aa = aa + i;}
        else {bb =bb + i;}
        a = Float.parseFloat(aa);
        b = Float.parseFloat(bb);
    }
    void action(byte action) {
        switch (action) {
            case 1: c = a + b; break;
            case 2: c = a - b; break;
            case 3: c = a / b; break;
            case 4: c = a * b; break;

        }
    }
}

