package pl.edu.pw.ee.aisd2025zex1.sorters.countingsort;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

public class CountingSort implements Sorting {

    private static final int DEFAULT_MAX_VAL = 10_000;

    private final int maxVal;

    public CountingSort() {
        this(DEFAULT_MAX_VAL);
    }

    public CountingSort(int maxVal) {
        assert maxVal > 0;
        this.maxVal = maxVal;
    }

    @Override
    public void sort(int[] nums) {
        validateInput(nums);

        int n = nums.length;
        int nOfCounters = maxVal + 1;

        int[] sorted = new int[n];
        int[] counters = new int[nOfCounters];

        for (int i = 0; i < n; i++) {
            counters[nums[i]]++;
        }

        for (int i = 1; i < nOfCounters; i++) {
            counters[i] += counters[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            sorted[counters[nums[i]]] = nums[i];
            counters[nums[i]]--;
        }

        System.arraycopy(sorted, 0, nums, 0, n);
    }

    private void validateInput(int[] nums) {
        validateNotNull(nums);
        validateInPositiveRange(nums);
    }

    private void validateNotNull(int[] nums) {
        if (isNull(nums)) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }
    }

    private void validateInPositiveRange(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                throw new IllegalArgumentException(
                        format("No number can be less than zero (nums[%d]=%d)!", i, nums[i]));
            }
            if (nums[i] > maxVal) {
                throw new IllegalArgumentException(
                        format("No number can be greater than \"maxVal\" (nums[%d]=%d)!", i, nums[i]));
            }
        }
    }

}
