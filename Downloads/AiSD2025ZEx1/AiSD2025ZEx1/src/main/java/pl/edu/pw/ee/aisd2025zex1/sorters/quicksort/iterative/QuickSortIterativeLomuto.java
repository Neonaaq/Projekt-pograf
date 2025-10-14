package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class QuickSortIterativeLomuto<T extends Comparable<T>> implements SortingCmp<T> {

    @Override
    public void sort(T[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }

        quicksort(data);
    }

    private void quicksort(T[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {

            while (n > 0) {
                n--;
                left = starts.remove(n);
                right = ends.remove(n);
                pivot = partition(data, left, right);

                if (pivot - 1 > left) {
                    starts.add(left);
                    ends.add(pivot - 1);
                    n++;
                }

                if (pivot + 1 < right) {
                    starts.add(pivot + 1);
                    ends.add(right);
                    n++;
                }
            }
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
            T firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }
}
