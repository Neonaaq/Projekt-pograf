import javax.swing.*;
import java.awt.*;

public class Main {
    private static MainGUI mainGUI;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pograf");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800); // to można zmieniać ale musi być > 600 bo rysunek jest 600x600

        mainGUI = new MainGUI(frame);
        JPanel panel = mainGUI.StworzMainGUI();

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
