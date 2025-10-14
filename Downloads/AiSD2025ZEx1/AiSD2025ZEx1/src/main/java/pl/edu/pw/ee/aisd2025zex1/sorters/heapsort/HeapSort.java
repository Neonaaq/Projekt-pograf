package pl.edu.pw.ee.aisd2025zex1.sorters.heapsort;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class HeapSort<T extends Comparable<T>> implements SortingCmp<T> {

    @Override
    public void sort(T[] data) {
        validateParams(data);

        buildHeap(data);

        int n = data.length;

        for (int i = n - 1; i > 0; i--) {
            swap(data, 0, i);
            heapify(data, 0, i);
        }
    }

    private void validateParams(T[] data) {
        if (isNull(data)) {
            throw new RuntimeException("Input args (data) cannot be null!");
        }
    }

    private void buildHeap(T[] data) {
        int n = data.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, i, n);
        }
    }

    private void heapify(T[] data, int parentId, int maxId) {
        int leftChildId = 2 * parentId + 1;
        int rightChildId = 2 * parentId + 2;
        int largestValId = parentId;

        if (leftChildId < maxId && data[leftChildId].compareTo(data[largestValId]) > 0) {
            largestValId = leftChildId;
        }

        if (rightChildId < maxId && data[rightChildId].compareTo(data[largestValId]) > 0) {
            largestValId = rightChildId;
        }

        if (largestValId != parentId) {
            swap(data, parentId, largestValId);
            heapify(data, largestValId, maxId);
        }
    }

    private void swap(T[] data, int firstId, int secondId) {
        if (firstId != secondId) {

            T firstVal = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstVal;
        }
    }

}
