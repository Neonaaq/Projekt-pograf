package pl.edu.pw.ee.aisd2025zex1.sorters.utils;

import java.lang.reflect.Array;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createRandomData;

public abstract class GeneralSortTest<T extends Comparable<T>> {

    protected SortingCmp<T> sorter;

    public GeneralSortTest(SortingCmp<T> sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_When_InputIsNull() {
        // given
        T[] nums = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            sorter.sort(nums);
        });

        // then
        String message = "Input args (data) cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ReturnEmptyArray_When_InputIsEmpty() {
        // given
        T[] nums = (T[]) Array.newInstance(Double.class, 0);

        // when
        sorter.sort(nums);

        // then
        assertThat(nums).isEmpty();
    }

    @Test
    public void should_CorrectlyAscendingSort_When_InputIsRandomAndHuge() {
        // given
        int size = 10_000;
        T[] nums = (T[]) createRandomData(size);
        T[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlySort_When_SmallDataFromMyOwnExample() {
        // given
        T[] nums = (T[]) new Integer[]{2, 1, 9, 13, 8, 5};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted();
    }

    @Test
    public void should_CorrectlySort_When_OnlyFirstIsWrong() {
        // given
        T[] nums = (T[]) new Integer[]{5, 1, 2, 3, 4};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted();
    }

    @Test
    public void should_CorrectlySort_When_OnlyLastIsWrong() {
        // given
        T[] nums = (T[]) new Integer[]{1, 2, 3, 4, 0};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted();
    }

    @Test
    public void should_CorrectlySort_When_UnsortedSizeIsEven() {
        // given
        T[] nums = (T[]) new Integer[]{2, 1, 4, 3};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums).
                isSorted();
    }

    @Test
    public void should_CorrectlySort_When_UnsortedSizeIsOdd() {
        // given
        T[] nums = (T[]) new Integer[]{4, 2, 5, 3, 1};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted();
    }

}
