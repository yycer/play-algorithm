package com.frankie.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: Yao Frankie
 * @date: 2020/5/23 15:45
 */
@SpringBootTest(classes = MathTest.class)
public class MathTest {

    @Test
    public void floorAndSqrtTest(){
        int c = 5;
        for (int i = 0; i <= Math.sqrt(c); i++){
            if (Math.floor(Math.sqrt(c - i * i)) == Math.sqrt(c - i * i)){
                System.out.println(true);
            }
        }
        System.out.println(false);
    }

    @Test
    public void test(){
        HashSet<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        String s = "aei";
//        boolean ret1 = s.contains('a');
    }
}
