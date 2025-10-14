package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.RAND;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.QuickSortIterativeLomuto;

public class QuickSortPerformanceChartsRandDataTest<T extends Comparable<T>> extends PerformanceChartsTest<T> {

    public QuickSortPerformanceChartsRandDataTest() {
        super(new QuickSortIterativeLomuto<>(), RAND);
    }

}
