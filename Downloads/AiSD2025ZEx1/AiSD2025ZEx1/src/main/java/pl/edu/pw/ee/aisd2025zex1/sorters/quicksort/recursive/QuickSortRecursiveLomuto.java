package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.recursive;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class QuickSortRecursiveLomuto<T extends Comparable<T>> implements SortingCmp<T> {

    @Override
    public void sort(T[] data) {
        validateParams(data);

        int left = 0;
        int right = data.length - 1;

        quickSort(data, left, right);
    }

    private void validateParams(T[] data) {
        if (isNull(data)) {
            throw new RuntimeException("Input args (data) cannot be null!");
        }
    }

    private void quickSort(T[] data, int left, int right) {
        if (left < right) {

            int pivotId = partition(data, left, right);

            quickSort(data, left, pivotId - 1);
            quickSort(data, pivotId + 1, right);
        }
    }

    private int partition(T[] data, int start, int end) {
        T pivot = data[end];

        int i = start - 1;
        int j = start;

        while (j < end) {
            if (data[j].compareTo(pivot) <= 0) {
                i++;
                swap(data, i, j);
            }
            j++;
        }

        int pivotId = ++i;
        swap(data, pivotId, end);

        return pivotId;
    }

    private void swap(T[] data, int firstId, int secondId) {
        if (firstId != secondId) {

            T firstVal = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstVal;
        }
    }

}
