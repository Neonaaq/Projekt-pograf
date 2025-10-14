package pl.edu.pw.ee.aisd2025zex1.sorters.referencesort;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortTest;

public class ReferenceAlgSortTest<T extends Comparable<T>> extends GeneralSortTest<T> {

    public ReferenceAlgSortTest() {
        super(new ReferenceAlgSort<>());
    }

}
