package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table;

import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createRandomData;

public class PerformanceRandDataTest<T extends Comparable<T>> extends PerformanceTest<T> {

    @Override
    protected T[] generateData(int size) {
        return (T[]) createRandomData(size);
    }

}
