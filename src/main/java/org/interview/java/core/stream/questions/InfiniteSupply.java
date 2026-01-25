package org.interview.java.core.stream.questions;


import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
*
* The Infinite Supply

Task: Use Stream.iterate to generate the Fibonacci sequence.

Limit: Skip the first 5 numbers, then take the next 10.

Output: Sum of these 10 numbers using reduce.
*
* */
public class InfiniteSupply {

    public static void main(String[] args) {
        int x = Stream.iterate(new int[]{0,1}, n -> new int[]{n[1],n[0] + n[1]} ).mapToInt(n-> n[0]).skip(0).limit(5).reduce(Integer::sum).getAsInt();
        System.out.println(x);
    }
}
