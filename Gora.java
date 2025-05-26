import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Gora extends JPanel {
    private static JFrame frame;
    private static String filePathWe;
    private static String filePathWy;

    public Gora(JFrame frame){
        this.frame = frame;
    }

    public JPanel StworzGore(){
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 240, 245));

        ImageIcon uploadicon = new ImageIcon("upload.png");
        JButton uploadbutton = new JButton(uploadicon);
        uploadbutton.setPreferredSize(new Dimension(uploadicon.getIconWidth(), uploadicon.getIconHeight()));
        uploadbutton.setBackground(new Color(255, 240, 245));

        uploadbutton.addActionListener(e -> {
            String name = "Plik wejściowy";
            Okienko okno = new Okienko(frame, name);
            okno.setVisible(true);
            filePathWe = okno.getFilePath();
        });

        ImageIcon downloadicon = new ImageIcon("download.png");
        JButton downloadbutton = new JButton("", downloadicon);
        downloadbutton.setPreferredSize(new Dimension(downloadicon.getIconWidth(), downloadicon.getIconHeight()));
        downloadbutton.setBackground(new Color(255, 240, 245));

        downloadbutton.addActionListener(e -> {
            String name = "Plik wyjściowy";
            Okienko okno = new Okienko(frame, name);
            okno.setVisible(true);
            filePathWy = okno.getFilePath();
            if(filePathWy == null || filePathWy.equals("")){
                filePathWy = "dane_wyjsciowe.txt";
            }
            else{
                File file = new File(filePathWy);
                if(!file.exists()){
                    filePathWy = okno.getFilePath();
                }
            }
        });

        panel.add(uploadbutton);
        panel.add(downloadbutton);

        return panel;
    }

    public String getFilePathWe(){
        return filePathWe;
    }
    public String getFilePathWy(){
        if(filePathWy == null){
            filePathWy = "dane_wyjsciowe.txt";
        }
        return filePathWy;
    }
}
