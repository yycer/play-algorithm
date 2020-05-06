package com.frankie.demo;

import com.frankie.demo.utils.SealUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

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

    @Test
    public void test(){
        Object a = null;
        CharSequence result = (CharSequence) a;
        System.out.println(result);
    }

    @Test
    public void sealCertNoTest(){
//        String certNo = "111";
//        String certNo = "0123456789";
        String phone = "11111222223";

//        String result = SealUtils.showHideField(certNo, 0, 4);
        String result = SealUtils.showHideField(phone, 3, 4);
        System.out.println(result);
    }


    @Test
    public void referenceTest() throws IOException {
//        SealUtils sealUtils = new SealUtils();
//        sealUtils.testReference();

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "yyc");
        map.put("name", "frankie");

        System.out.println(map);
    }
}
