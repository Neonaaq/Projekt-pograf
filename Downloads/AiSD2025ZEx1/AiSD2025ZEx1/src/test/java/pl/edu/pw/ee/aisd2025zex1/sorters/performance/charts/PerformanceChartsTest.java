package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.format;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType;
import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.ASC;
import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.DESC;
import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.RAND;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createAscendingData;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createDescendingData;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createRandomData;

public abstract class PerformanceChartsTest<T extends Comparable<T>> {

    private static final Logger LOG = Logger.getLogger(PerformanceChartsTest.class.getName());

    private final SortingCmp<T> sorter;
    private final DataArrangeType dataArrangeType;
    private final String resultFilename;

    public PerformanceChartsTest(SortingCmp<T> sorter, DataArrangeType dataArrangeType) {
        this.sorter = sorter;
        this.dataArrangeType = dataArrangeType;

        String sorterName = sorter.getClass().getSimpleName();
        resultFilename = sorterName + "_charts_performance.txt";

        createOrClearResultFile();
    }

    @Test
    public void runPerformanceChartTest() {
        int step = 1000;
        int maxSize = 200_000;
        T[] data;

        for (int i = 0; i < maxSize; i += step) {

            data = (T[]) createDataByType(i);

            measureTimeAndSaveToFile(sorter, data);
        }
    }

    private void createOrClearResultFile() {
        File resultFile = new File(resultFilename);

        try {
            resultFile.createNewFile();

            new FileWriter(resultFile, false).close();

        } catch (IOException e) {
            LOG.log(SEVERE, "Caught exception during creating file", e);
        }
    }

    private T[] createDataByType(int size) {
        T[] data;

        switch (dataArrangeType) {
            case ASC ->
                data = (T[]) createAscendingData(size);

            case RAND ->
                data = (T[]) createRandomData(size);

            case DESC ->
                data = (T[]) createDescendingData(size);

            default ->
                throw new RuntimeException(format("Unexpected data arraynge type [type: %s].", dataArrangeType));
        }

        return data;
    }

    private void measureTimeAndSaveToFile(SortingCmp<T> sorter, T[] data) {
        int n = data.length;

        long measuredTime = measureTime(sorter, data);

        saveToFile(n, measuredTime);
    }

    private long measureTime(SortingCmp<T> sorter, T[] data) {
        long start = System.nanoTime();

        sorter.sort(data);

        long timeResult = System.nanoTime() - start;

        return timeResult;
    }

    private void saveToFile(int size, long measuredTime) {
        try (
                FileWriter fWriter = new FileWriter(resultFilename, true); BufferedWriter writer = new BufferedWriter(fWriter)) {

            writer.append(format("%8d | %d\n", size, measuredTime));

        } catch (IOException e) {
            LOG.log(SEVERE, "Caught exception during writing to file: " + resultFilename, e);
        }
    }
}
