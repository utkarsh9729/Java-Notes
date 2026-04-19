package org.interview.java.core.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Demonstrates common ArrayList operations and best practices.
 */
public class ArrayListPractice {

    public static void main(String[] args) {
        // Initializing an ArrayList
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 9, 2));
        System.out.println("Original List: " + numbers);

        // Sorting
        Collections.sort(numbers);
        System.out.println("Sorted List: " + numbers);

        // Efficient bulk addition
        numbers.addAll(Arrays.asList(10, 11, 12));
        
        // Random access
        if (!numbers.isEmpty()) {
            System.out.println("Element at index 2: " + numbers.get(2));
        }

        // Removing elements by value (requires wrapping primitive to Object)
        numbers.remove(Integer.valueOf(8));
        System.out.println("After removing 8: " + numbers);

        // Sublist view (changes to sublist reflect in the original list)
        List<Integer> sub = numbers.subList(1, 4);
        System.out.println("Sublist (1 to 4): " + sub);
        sub.set(0, 99);
        System.out.println("Original after sublist modification: " + numbers);

        // Memory management
        if (numbers instanceof ArrayList) {
            ((ArrayList<Integer>) numbers).trimToSize();
            System.out.println("Capacity trimmed to size.");
        }
    }
}
