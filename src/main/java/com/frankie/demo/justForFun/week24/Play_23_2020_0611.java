package com.frankie.demo.justForFun.week24;

/**
 * @author: Yao Frankie
 * @date: 2020/6/1 9:40
 */
public class Play_23_2020_0611 {

    public static void main(String[] args) {
        p394(); // 394. Decode String
    }

    private static void p394() {
        String s = "3[a]2[bc]";
        String a = decodeString(s);
        System.out.println(a);
    }

    static int i = 0;

    private static String decodeString(String s) {
        int len = s.length();
        int times = 0;
        StringBuilder sb = new StringBuilder();

        while (i < len){

            // digit
            while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                times = times * 10 + s.charAt(i) - '0';
                i++;
            }

            // [
            if (s.charAt(i) == '['){
                i++;
                String tmp = decodeString(s);

                // Process times
                while (times-- > 0){
                    sb.append(tmp);
                }
                times++;
            }

            // ]
            else if (s.charAt(i) == ']'){
                i++;
                return new String(sb);
            }

            // letters
            else {
                sb.append(s.charAt(i));
                i++;
            }
        }

        return new String(sb);
    }
}