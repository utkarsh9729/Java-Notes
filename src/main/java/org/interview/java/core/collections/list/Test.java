package org.interview.java.core.collections.list;

import java.util.HashSet;
import java.util.Set;

/**
 * Example of Two Sum problem using a HashSet for O(n) lookup.
 */
public class Test {
    /**
     * Finds two numbers in the array that add up to the target.
     * Returns the two numbers if found, otherwise [-1, -1].
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return new int[]{complement, num};
            }
            seen.add(num);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Test solver = new Test();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solver.twoSum(nums, target);
        System.out.println("Result: [" + result[0] + ", " + result[1] + "]");
    }
}
