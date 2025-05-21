import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Graf graf = new Graf("dane_wyjsciowe.txt");
        graf.OdczytajPlik();
        // taki teścik
//        for(int i=0; i<105; i++) {
//            if(graf.matrix[37][i] == 1) {
//                System.out.println(i);
//            }
//        }

        JFrame frame = new JFrame("Pograf");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);    // to można zmieniać ale musi być > 600 bo rysunek jest 600x600
        JPanel rysunek = graf.StworzRysunekGrafu();
        frame.add(rysunek, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
