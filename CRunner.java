import java.io.*;

public class CRunner {
    private static int lczesci;
    private static int margines;
    private static String filePathWe;
    private static String filePathWy;
    private static String programName;

    public CRunner(int lczesci, int margines, String filePathWe, String filePathWy) {
        this.lczesci = lczesci;
        this.margines = margines;
        this.filePathWe = filePathWe;
        this.filePathWy = filePathWy;
    }

    public void CRun () {
        try {
            programName = "./pograf";
            String lCzesci = String.valueOf(lczesci);
            String Margines = String.valueOf(margines);
            ProcessBuilder builder = new ProcessBuilder(
                    programName, filePathWe, "--division", lCzesci, "--hem", Margines, "--output", filePathWy
            );

            builder.redirectErrorStream(true);
            Process process = builder.start();
            //System.out.println(filePathWe + " " + lCzesci + " " + Margines + " " + filePathWy);

            int exitCode = process.waitFor();
            System.out.println("Program zakończył się kodem: " + exitCode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
