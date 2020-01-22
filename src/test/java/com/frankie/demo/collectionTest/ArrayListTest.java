package com.frankie.demo.collectionTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yao Frankie
 * @date: 2020/1/22 13:59
 */
@SpringBootTest
public class ArrayListTest {


    public List<Integer> nums = new ArrayList<>();

    @Test
    public void allowNullTest(){
        nums.add(1);
        nums.add(null);
        System.out.println(nums.toString());
    }

    @Test
    public void allowDuplicateTest(){
        nums.add(1);
        nums.add(1);
        System.out.println(nums.toString());
    }

    @Test
    public void isOrderedTest(){
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        System.out.println(nums.toString());
    }

    @Test
    public void isThreadSafetyTest(){
        for(int i = 1; i <= 100; i++){
            int finalI = i;
            new Thread(() -> nums.add(finalI)).start();
        }

        System.out.println(nums.toString());

//        [1, 2, 3, 4, 6, 7, 8, 5, 9, 10, 11, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32]
    }
}
