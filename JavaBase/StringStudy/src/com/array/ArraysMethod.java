package com.array;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sean on 2018/4/25.
 */
public class ArraysMethod {
    public static void main(String[] args) {
        Integer[] arr = {1, 32, 31, 2, 3, 2, 123, 3};
        List<Integer> list = Arrays.asList(arr);
        Arrays.sort(arr);
        Arrays.parallelSort(arr);
        Arrays.binarySearch(arr, 2);
        Integer[] arr2=Arrays.copyOf(arr,4);
        Arrays.copyOfRange(arr,1,5);
        Arrays.equals(args,arr2);

    }
}
