package org.interview.java.core.stream.questions;


import java.util.Arrays;
import java.util.List;

/*
*
*  The "String Reverser" Custom CollectorTask: Write a custom Collector class (implementing Collector<String, StringBuilder, String>)
* from scratch.Behavior: It should join a stream of Strings, but reverse each string before joining them, separated by a pipe |.Input: ["abc", "def"] $\to$
* Output: "cba|fed"
*
* */
public class StringReverser {


    public static void main(String[] args) {
        List<String> str = Arrays.asList("abc","def");
        String a = str.stream().collect(new Reverser());
        System.out.println(a);
    }
}
