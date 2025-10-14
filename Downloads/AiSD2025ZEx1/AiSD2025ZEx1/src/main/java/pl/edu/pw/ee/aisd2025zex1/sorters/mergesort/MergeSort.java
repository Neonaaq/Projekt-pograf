package pl.edu.pw.ee.aisd2025zex1.sorters.mergesort;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class MergeSort<T extends Comparable<T>> implements SortingCmp<T> {

    @Override
    public void sort(T[] data) {
        validateInput(data);

        int n = data.length;

        T[] auxdata = (T[]) new Comparable[n];
        System.arraycopy(data, 0, auxdata, 0, n);

        mergeSortBottomUp(data, auxdata);
    }

    private void mergeSortBottomUp(T[] data, T[] auxData) {
        int n = data.length;

        int midId, rightId;

        for (int w = 1; w < n; w *= 2) {

            for (int left = 0; left < n; left += 2 * w) {

                midId = left + w - 1;
                rightId = Math.min(left + 2 * w - 1, n - 1);
                merge(data, auxData, left, midId, rightId);
            }
        }
    }

    private void merge(T[] data, T[] auxData, int leftId, int midId, int rightId) {
        int n = data.length;
        System.arraycopy(data, 0, auxData, 0, n);

        int i = leftId;
        int j = midId + 1;

        for (int k = leftId; k <= rightId; k++) {
            if (i > midId) {
                data[k] = auxData[j++];

            } else if (j > rightId) {
                data[k] = auxData[i++];

            } else if (auxData[i].compareTo(auxData[j]) <= 0) {
                data[k] = auxData[i++];

            } else {
                data[k] = auxData[j++];
            }
        }

    }

    private void validateInput(T[] data) {
        if (isNull(data)) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }
    }
}
