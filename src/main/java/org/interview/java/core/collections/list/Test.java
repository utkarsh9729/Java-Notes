//import java.util.*;
//import java.util.stream.Collectors;
//
//public class Test {
//    public int[] twoSum(int[] nums, int target) {
//
//
//        HashSet<Integer> unique = List.of(nums).stream().map(p -> (Integer) p).collect(Collectors.toSet());
//
//        for(int i=0;i<nums.length;i++){
//
//            int k = target - nums[i];
//            if(uniqe.contains(k)){
//                return new int[] {nums[i],k};
//            }
//        }
//
//        return new int[]{-1, -1};
//    }
//}
