package pl.edu.pw.ee.aisd2025zex1.sorters.heapsort;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;

public class HeapSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public HeapSortTest() {
        super(new HeapSort<>());
    }

}
