import javax.swing.*;
import java.awt.*;

public class Okienko extends JDialog {
    private JTextField field;
    private JButton okButton;
    private String filePath;

    public Okienko(Frame frame, String name) {
        super(frame, name, true);
        setLayout(new BorderLayout());
        setSize(300, getHeight());
        setBackground(new Color(255, 240, 245));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 240, 245));

        JLabel label = new JLabel("Wpisz nazwę/ścieżke pliku:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        field = new JTextField();
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setPreferredSize(new Dimension(300, 50));
        field.setMaximumSize(new Dimension(300, 50));
        field.setFont(new Font("Arial", Font.PLAIN, 16));

        okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.addActionListener(e -> {
            filePath = field.getText();
            dispose();
        });
        panel.add(Box.createVerticalStrut(10));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(field);

        add(panel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(frame);
    }

    public String getFilePath() {
        return filePath;
    }
}
