package com.frankie.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Stack;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void printTest(){
        System.out.println("yyc");
    }

    @Test
    public void stackTest(){
        Stack<Integer> nums = new Stack<>();
        nums.push(1);
        nums.push(2);

        System.out.println(nums.pop().toString());
        System.out.println(nums);
    }
}
