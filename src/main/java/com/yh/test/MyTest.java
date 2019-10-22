package com.yh.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyTest {
    public static void main (String args[]) {
        /*Integer[] array = {1,2,3};
        List ints = Arrays.asList(array);
        array[0] = 100;
        for(int i = 0;i<ints.size();i++){
            System.out.println(ints.get(i));
        }*/

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }
        }
        for (String item : list) {
            System.out.println(item);
        }
    }
}
