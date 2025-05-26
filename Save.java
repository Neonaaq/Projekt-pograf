import javax.swing.*;
import java.awt.*;


public class Save {
    private Runnable runnable;


    public Save() {}

    public JPanel StworzSavePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 240, 245));

        JButton save = new JButton("Zatwierdź");
        save.setFont(new Font("Arial", Font.BOLD, 16));

        save.addActionListener(e -> {
            if(runnable != null) {
                runnable.run();
            }
        });
        panel.add(save);

        return panel;
    }

    public void setRunnable(Runnable action) {
        this.runnable = action;
    }
}
