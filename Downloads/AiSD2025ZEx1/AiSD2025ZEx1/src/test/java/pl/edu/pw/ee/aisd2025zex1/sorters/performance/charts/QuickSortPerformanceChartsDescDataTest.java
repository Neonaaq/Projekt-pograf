package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.DESC;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.QuickSortIterativeLomuto;

public class QuickSortPerformanceChartsDescDataTest<T extends Comparable<T>> extends PerformanceChartsTest<T> {

    public QuickSortPerformanceChartsDescDataTest() {
        super(new QuickSortIterativeLomuto<>(), DESC);
    }

}
