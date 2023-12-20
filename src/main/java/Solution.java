import java.util.Arrays;
import java.util.Comparator;


public class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int mod = 1_000_000_007;
        long sum = 0;
        int[][] merged = new int[n][2];
        for(int i = 0; i < n; i++ ){
            sum = sum + Math.abs(nums1[i] - nums2[i]);
            merged[i] = new int[]{nums1[i], nums2[i]};
        }
        Arrays.sort(nums1);
        Arrays.sort(merged, Comparator.comparingInt(arr -> arr[0]));

        int maxBenefit = 0;
        for(int i = 0; i < n; i++){
            int val1 = merged[i][0];
            int val2 = merged[i][1];
            int absDiff = Math.abs(val1 - val2);

            int tryDiff = binarySearch(nums1, val2);
            int diffDiff = absDiff - tryDiff;
            maxBenefit = Math.max(maxBenefit, diffDiff);
        }
        System.out.println(maxBenefit);
        System.out.println(sum);
        return (int) ((sum - maxBenefit) % mod);
    }
    private int binarySearch(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        int mid = l + (r - l ) / 2;

        while(l <= r){
            mid = l + (r - l ) / 2;
            if(arr[mid] == target){
                return 0;
            }
            if(target < arr[mid]){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        int minus = Integer.MAX_VALUE;
        int plus = Integer.MAX_VALUE;
        int midVal = Math.abs(arr[mid] - target);
        if(mid > 0){
            minus = Math.abs(arr[mid - 1] - target);
        }
        if(mid < arr.length - 1){
            plus = Math.abs(arr[mid + 1] - target);
        }
        return Math.min(midVal, Math.min(plus, minus));
    }
}
