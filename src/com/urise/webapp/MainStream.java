package com.urise.webapp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {

    public static int minValue(Integer[] values) {
        AtomicInteger result = new AtomicInteger();
        final int[] i = {0};
        Arrays.stream(values).distinct().sorted(Comparator.reverseOrder()).forEach(integer -> {
            result.addAndGet((int) (integer * Math.pow(10, i[0])));
            i[0]++;
        });
        return result.get();
    }

    public static void main(String[] args) {
        System.out.println(minValue(new Integer[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new Integer[]{9, 8}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }

    public static List<Integer> oddOrEven(List<Integer> values) {
        Map<Boolean, List<Integer>> oddsAndEvens = values.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddsAndEvens.get(oddsAndEvens.get(false).size() % 2 != 0);
    }

}
