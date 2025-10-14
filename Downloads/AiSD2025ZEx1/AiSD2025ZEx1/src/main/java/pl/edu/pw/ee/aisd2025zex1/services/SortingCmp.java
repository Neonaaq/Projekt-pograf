package pl.edu.pw.ee.aisd2025zex1.services;

public interface SortingCmp<T extends Comparable<T>> {

    void sort(T[] nums);
    
}
