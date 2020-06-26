package com.op.algorithm;

import static com.op.util.Print.println;

/**
 * 种植花朵
 * <p>
 * 605. Can Place Flowers (Easy)
 * <p>
 * https://leetcode-cn.com/problems/can-place-flowers/description/
 *
 * @Author: NZY
 * @Date: 2020/6/11 19:26
 */
public class PlaceFlowers {
    public static void main(String[] args) {
        PlaceFlowers placeFlowers = new PlaceFlowers();
        int[] flowerbed = {1, 0, 0, 0, 0, 1};
        int n = 2;
        println(placeFlowers.canPlaceFlowers(flowerbed, n));
    }
    
    /**
     * flowerbed 数组中 1 表示已经种下了花朵。花朵之间至少需要一个单位的间隔，求解是否能种下 n 朵花。
     * <p>
     * Input: flowerbed = [1,0,0,0,1], n = 1
     * <p>
     * Output: True
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        if (len == 0 || len < n) {
            return false;
        }
        
        int f = 0;
        for (int i = 0; i < len; i++) {
            if (flowerbed[i] == 0
                        && (i == 0 || flowerbed[i - 1] == 0)
                        && (i == len - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                f++;
            }
            
            if (f >= n) {
                return true;
            }
        }
        
        return false;
    }
    
}
