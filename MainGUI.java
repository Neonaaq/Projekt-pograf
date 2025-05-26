import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainGUI {
    private JPanel panel;
    private JPanel centrump;
    private JFrame frame;
    private Gora gora;
    private Dol dol;
    private Save save;

    public MainGUI(JFrame frame) {
        this.frame = frame;
    }
    public JPanel StworzMainGUI(){
        panel = new JPanel();
        gora = new Gora(frame);
        dol = new Dol();
        save = new Save();

        JPanel gorap = gora.StworzGore();
        JPanel gornyPanel = new JPanel();
        gornyPanel.setLayout(new BorderLayout());
        gornyPanel.add(gorap, BorderLayout.NORTH);

        centrump = new JPanel();

        JPanel dolp = dol.StworzDol();
        JPanel savep = save.StworzSavePanel();
        JPanel dolnyPanel = new JPanel();
        dolnyPanel.setLayout(new BorderLayout());
        dolnyPanel.add(dolp, BorderLayout.NORTH);
        dolnyPanel.add(savep, BorderLayout.CENTER);


        save.setRunnable(() -> {
            String filePathWe = gora.getFilePathWe();
            String filePathWy = gora.getFilePathWy();
            String lCzesci = dol.getLczesci();
            String Margines = dol.getMargines();


            int lczesci = 2;
            int margines = 10;

            if (filePathWe == null || filePathWe.length() == 0) {
                String message = "Nazwa/ścieżka pliku wejściowego musi zostać podana!";
                JOptionPane.showMessageDialog(frame, message);
            }
            else {
                File file = new File(filePathWe);
                if (file.exists() == false) {
                    String message = "Plik jest pusty.";
                    JOptionPane.showMessageDialog(frame, message);
                }
                else {
                    if(lCzesci == null || lCzesci.length() == 0) {
                        String message = "Zostanie ustalona domyślna ilość części = 2.";
                        JOptionPane.showMessageDialog(frame, message);
                        lczesci = 2;
                    }
                    else {
                        try{
                            lczesci = Integer.valueOf(lCzesci);
                            if(lczesci<2) {
                                String message = "Liczba części musi być większa niż 1.\nZostanie ustawiona domyślna wartość = 2.";
                                JOptionPane.showMessageDialog(frame, message);
                                lczesci = 2;
                            }
                        }
                        catch(NumberFormatException nfe){
                            String message = "Liczba części musi być cyfrą.\nZostanie ustawiona domyślna wartość = 2.";
                            JOptionPane.showMessageDialog(frame, message);
                        }
                    }

                    if(Margines == null || Margines.length() == 0) {
                        String message = "Zostanie ustalona domyślna wartość marginesu procentowego = 10.";
                        JOptionPane.showMessageDialog(frame, message);
                        margines = 10;
                    }
                    else {
                        try {
                            margines = Integer.valueOf(Margines);
                            if(margines<=0 || margines>30) {
                                String message = "Margines procentowy musi zawierac sie w przedziale (0, 30>.\nZostanie ustawiona domyślna wartość = 10.";
                                JOptionPane.showMessageDialog(frame, message);
                                margines = 10;
                            }
                        }
                        catch(NumberFormatException nfe){
                            String message = "Margines procentowy musi być cyfrą.\nZostanie ustawiona domyślna wartość = 2.";
                            JOptionPane.showMessageDialog(frame, message);
                        }
                    }

                    CRunner cRunner = new CRunner(lczesci, margines, filePathWe, filePathWy);
                    cRunner.CRun();

                    this.aktualizujCentrum();
                }
            }

        });

        panel.setLayout(new BorderLayout());
        panel.add(gornyPanel, BorderLayout.NORTH);
        panel.add(centrump, BorderLayout.CENTER);
        panel.add(dolnyPanel, BorderLayout.SOUTH);

        return panel;
    }

    public void aktualizujCentrum() {
        panel.remove(centrump);
        Graf graf = new Graf(gora.getFilePathWy());
        graf.OdczytajPlik();
        JPanel newRysunek = graf.StworzRysunekGrafu();
        centrump=newRysunek;
        panel.add(newRysunek, BorderLayout.CENTER);

        SwingUtilities.invokeLater(() -> {
            panel.revalidate();
            panel.repaint();
        });
    }

}
