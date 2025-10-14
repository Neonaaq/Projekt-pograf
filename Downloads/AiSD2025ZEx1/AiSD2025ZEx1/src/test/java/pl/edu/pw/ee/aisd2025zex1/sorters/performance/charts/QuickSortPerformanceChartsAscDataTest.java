package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.ASC;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.QuickSortIterativeLomuto;

public class QuickSortPerformanceChartsAscDataTest<T extends Comparable<T>> extends PerformanceChartsTest<T> {

    public QuickSortPerformanceChartsAscDataTest() {
        super(new QuickSortIterativeLomuto<>(), ASC);
    }

}
