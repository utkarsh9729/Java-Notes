package org.interview.java.core.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListPractice {


    public static void main(String[] args) {


        ArrayList<Integer> li = new ArrayList<>();
        li.addAll(Arrays.asList(1,2,3,4,5,6));

        List<Integer> li1 = Arrays.asList(1,2,3,4,5,6);
        li.trimToSize();
    }
}
