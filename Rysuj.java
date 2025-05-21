import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Rysuj extends JPanel {

    private static int[] column;
    private static int[] row;
    private static int[] part;
    private static Color[] colors;

    private static int[][] matrix;

    private static int lwezlow;

    public Rysuj(int lwezlow, int[][] matrix, int[] column, int[] row, int[] part) {
        this.matrix = matrix;
        this.lwezlow = lwezlow;
        this.column = column;
        this.row = row;
        this.part = part;
        colors = new Color[part.length];
        makeRandomColors();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("paintComponent wywołane");
        paintMatrix(g);
    }

    public void paintMatrix(Graphics g) {
        g.setColor(Color.BLACK);
        int max = getMaxColumnOrRow();
        int width = 600/max-10;
        int distance = (600-width*max)/max;
        if(width<20) {
//            g.drawString("Za duży graf", 50, 50);
//            return;
            width = 20;
        }
        for (int i = 0; i < lwezlow; i++) {
            for (int j = 0; j < lwezlow; j++) {
                if(matrix[i][j]==1) {
                    g.setColor(Color.BLACK);
                    g.drawLine(column[i]*(width+distance)+width/2,row[i]*(width+distance)+width/2,column[j]*(width+distance)+width/2,row[j]*(width+distance)+width/2);
                }
            }
        }
        for (int i = 0; i < lwezlow; i++) {
            int x = column[i]*(width+distance), y = row[i]*(width+distance);

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(String.valueOf(i));
            int textHeight = fm.getAscent();
            int ascent = fm.getAscent();
            g.setColor(colors[part[i]]);
            g.fillOval(x, y, width, width);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, width, width);
            double luminance = 0.299 * colors[part[i]].getRed() + 0.587 * colors[part[i]].getGreen() + 0.114 * colors[part[i]].getBlue();
            if(luminance< 128) {
                g.setColor(Color.WHITE);
            }
            g.drawString(String.valueOf(i), x+width/2-textWidth/2, y+width/2+(ascent-textHeight/2));
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

    private void makeRandomColors() {
        for (int i = 0; i < part.length; i++) {
            Random rand = new Random();
            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            colors[i] = randomColor;
        }

    }
}
