import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graf {
    private static int[] column;
    private static int[] row;
    private static int[] part;

    public static int[][] matrix;

    private static int lwezlow;
    String filePath;

    public Graf(String filePath) {
        this.filePath = filePath;
    }

    public void OdczytajPlik() {
        // pomocniczy plik ze strukturą z C (kolumny, rzędy i części)
        try(BufferedReader reader = new BufferedReader(new FileReader("struktura.txt"))) {
            String line = reader.readLine();
            int wezel = 0;
            lwezlow = Integer.parseInt(line);
            column = new int[lwezlow];
            row = new int[lwezlow];
            part = new int[lwezlow];
            matrix = new int[lwezlow][lwezlow];

            while ((line = reader.readLine()) != null) {
                // Dzielimy linię po średniku
                String[] parts = line.split(";");

                if (parts.length == 3) {
                    column[wezel] = Integer.parseInt(parts[0]);
                    row[wezel] = Integer.parseInt(parts[1]);
                    part[wezel] = Integer.parseInt(parts[2]);
                    wezel++;
                } else {
                    System.out.println("Nieprawidłowy format linii: " + line);
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        // plik z połączeniami
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] pom = new String[lwezlow*lwezlow];
            int l = 0;
            for (int i = 0; i < lwezlow; i++) {
                for (int j = 0; j < lwezlow; j++) {
                    matrix[i][j] = 0;
                }
            }
            while ((line = reader.readLine()) != null) {
                l++;
                if(l == 4) {
                    pom = line.split(";");
                }
                if(l>4) {
                    String[] parts = line.split(";");
                    for(int i = 0; i < parts.length; i++) {
                        int liczba1 = Integer.parseInt(parts[i]);
                        int liczba2;
                        if(i+1 < parts.length) {
                            liczba2 = Integer.parseInt(parts[i+1]);
                        } else {
                            liczba2 = pom.length;
                        }
                        for(int j = liczba1+1; j < liczba2; j++) {
                            int rzad = Integer.parseInt(pom[liczba1]);
                            int kolumna = Integer.parseInt(pom[j]);
                            matrix[rzad][kolumna] = 1;
                            matrix[kolumna][rzad] = 1;
                        }
                    }
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private int getMaxColumnOrRow() {
        int max = 0;
        for (int i = 0; i < column.length; i++) {
            if (column[i]+1 > max) {
                max = column[i]+1;
            }
        }
        for (int i = 0; i < row.length; i++) {
            if (row[i]+1 > max) {
                max = row[i]+1;
            }
        }
        return max;
    }

    public JPanel StworzRysunekGrafu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Rysuj r = new Rysuj(lwezlow, matrix, column, row, part);
        int max = getMaxColumnOrRow();
        if(max*20>600) {
            r.setPreferredSize(new Dimension(max*30, max*30));
        } else {
            r.setPreferredSize(new Dimension(600, 600));
        }

        JScrollPane scrollPane = new JScrollPane(r);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}
