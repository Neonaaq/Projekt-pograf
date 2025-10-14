package pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;

public class SelectionSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public SelectionSortTest() {
        super(new SelectionSort<>());
    }

}
