package com.frankie.demo.stream;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Yao Frankie
 * @date: 2020/5/22 11:27
 */
public class PlayStream {

    public static void main(String[] args) {
        sortAndDistinct();
    }

    private static void sortAndDistinct() {

        List<Product> products = new ArrayList<>();

        Product a1 = new Product("A", LocalDateTime.now().minusDays(10), "20.00");
        Product a2 = new Product("A", LocalDateTime.now().minusDays(5),  "20.00");
        Product a3 = new Product("A", LocalDateTime.now().minusDays(20), "20.00");
        Product b1 = new Product("B", LocalDateTime.now().minusDays(30), "30.00");
        Product b2 = new Product("B", LocalDateTime.now().minusDays(40), "30.00");

        products.add(a1);
        products.add(a2);
        products.add(a3);
        products.add(b1);
        products.add(b2);

//        TreeSet<String> set = new TreeSet<>();
//        List<Product> ret = products.stream()
//                .sorted(Comparator.comparing(Product::getBuyTime).reversed())
//                .filter(p -> set.add(p.getName()))
//                .collect(Collectors.toList());
//
//        System.out.println(ret);

        Map<String, List<Product>> map = products.stream().collect(Collectors.groupingBy(Product::getName));
        List<Product> ret = map.entrySet().stream()
                .sorted(Comparator.comparing(m -> m.getValue().stream().map(Product::getBuyTime).max(Comparator.naturalOrder()).orElse(LocalDateTime.now())))
                .flatMap(m -> m.getValue().stream().sorted(Comparator.comparing(Product::getBuyTime)))
                .collect(Collectors.toList());

        System.out.println(ret);
    }
}
