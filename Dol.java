import javax.swing.*;
import java.awt.*;

public class Dol {
    private JTextField textField1;
    private JTextField textField2;

    public Dol(){}

    public JPanel StworzDol(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        //napisy
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(255, 240, 245));

        JLabel label1 = new JLabel("Liczba podziałów:");
        label1.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label2 = new JLabel("Margines podziału:");
        label2.setFont(new Font("Arial", Font.BOLD, 16));

        //wpisywanie
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(255, 240, 245));

        textField1 = new JTextField();
        textField1.setFont(new Font("Arial", Font.BOLD, 16));
        textField1.setPreferredSize(new Dimension(100, 50));

        textField2 = new JTextField();
        textField2.setFont(new Font("Arial", Font.BOLD, 16));
        textField2.setPreferredSize(new Dimension(100, 50));


        //dodanie do paneli
        panel1.add(label1, BorderLayout.CENTER);
        panel1.add(Box.createHorizontalStrut(30));
        panel1.add(label2, BorderLayout.CENTER);

        panel2.add(textField1, BorderLayout.CENTER);
        panel2.add(Box.createHorizontalStrut(60));
        panel2.add(textField2, BorderLayout.CENTER);


        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.SOUTH);

        return panel;
    }

    public String getLczesci() {
        return textField1.getText();
    }
    public String getMargines() {
        return textField2.getText();
    }
}
