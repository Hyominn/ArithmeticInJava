package algorithm;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static foundation.util.Print.println;

/**
 * 分配饼干
 * <p>
 * 455. Assign Cookies (Easy)
 * <p>
 * https://leetcode-cn.com/problems/assign-cookies/description/
 *
 * @Author: NZY
 * @Date: 2020/6/9 14:57
 */
public class AssignCookies {
    public static void main(String[] args) {
        println("1");
    }

    /**
     * 每个孩子都有一个满足度 grid，每个饼干都有一个大小 size，只有饼干的大小大于等于一个孩子的满足度，该孩子才会获得满足。求解最多可以获得满足的孩子数量。
     * <p>
     * 给一个孩子的饼干应当尽量小并且又能满足该孩子，这样大饼干才能拿来给满足度比较大的孩子。
     * 因为满足度最小的孩子最容易得到满足，所以先满足满足度最小的孩子。
     * <p>
     * 在以上的解法中，我们只在每次分配时饼干时选择一种看起来是当前最优的分配方法，但无法保证这种局部最优的分配方法最后能得到全局最优解。我们假设能得到全局最优解，并使用反证法进行证明，即假设存在一种比我们使用的贪心策略更优的最优策略。如果不存在这种最优策略，表示贪心策略就是最优策略，得到的解也就是全局最优解。
     * <p>
     * 假设在某次选择中，贪心策略选择给当前满足度最小的孩子分配第 m 个饼干，第 m 个饼干为可以满足该孩子的最小饼干。假设存在一种最优策略，可以给该孩子分配第 n 个饼干，并且 m < n。我们可以发现，经过这一轮分配，贪心策略分配后剩下的饼干一定有一个比最优策略来得大。因此在后续的分配中，贪心策略一定能满足更多的孩子。也就是说不存在比贪心策略更优的策略，即贪心策略就是最优策略。
     * <p>
     * Input: grid[1,3], size[1,2,4]
     * Output: 2
     */
    public int findContentChildren(@NotNull int[] grid, int[] size) {
        if (grid.length == 0 || size.length == 0) {
            return 0;
        }
        Arrays.sort(grid);
        Arrays.sort(size);
        int gi = 0, si = 0;
        while (gi < grid.length && si < size.length) {
            if (grid[gi] <= size[si]) {
                gi++;
            }
            si++;
        }
        return gi;
    }


}
