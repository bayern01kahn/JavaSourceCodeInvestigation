package justin.algorithm.dynamicProgramming;

import org.junit.Test;

/**
 * 动态规划之金矿问题:
 * 总共有10人, 有5种金矿,对应的 重量-所需人数  200-3  300-4 350-3  400-5 500-5,
 * 求怎么分配能达到 最大收益
 *
 * 解法思路 3个要素:
 * 1.找到最优子结构
 * 2.状态转换方程
 * 3.边界 (递归出口)
 *
 * 解决方案
 * 1.递归
 * 2.二维数组- 优化方式:自底向上求解
 *
 * 也可参考: https://www.cnblogs.com/fengranqingyu/p/8452888.html
 */
public class DP_GoldMine {

    @Test
    public void work() {
        int worker = 10;
        int g[] = {200, 300, 350, 400, 500};
        int p[] = {3,4,3,5,5};

        long starttime = System.nanoTime();
        int bestWeight = getBestWeight(worker, g.length, p, g);
        long costtime =  System.nanoTime() - starttime;
        System.out.println(worker+"人 最大收益为: "+ bestWeight+ " => 递归算法-耗时: "+costtime+"ns");

        starttime = System.nanoTime();
        int bestWeightByArray = getBestWeightBy2DArray(worker, p, g);
        costtime =  System.nanoTime() - starttime;
        System.out.println(worker+"人 最大收益为: "+ bestWeightByArray+ " => 二维数组-耗时: "+costtime+ "ns");
    }

    /**
     * 二维数组-自底向上算法  时间复杂度为 O(n*w)
     *
     * @param worker  工人总数
     * @param p       金矿所需工人数组
     * @param g       金矿对应重量
     * @return
     */
    private int getBestWeightBy2DArray(int worker, int[] p, int[] g) {
        int goldMineNumber = g.length;
        int resultTable[][] = new int[goldMineNumber+1][worker+1];

        /**
         *  自底向上 求 最优解.  这样 数组最右下角为最优解
         *
         *  n\w              0       1       2       3       4       5       6       7       8       9       10
         *
         *                   [i][0]  [i][1]  [i][2]  [i][3]  [i][4]  [i][5]  [i][6]  [i][7]  [i][8]  [i][9]  [i][10]
         *         [0][j]    0       0       0       0       0       0       0       0       0       0       0
         *  200-3  [1][j]    0       0       0       200     200     200     200     200     200     200     200
         *  300-4  [2][j]    0       0       0       200     300     300     300     500     500     500     500
         *  350-3  [3][j]    0       0       0       350     350     350     550     650     650     650     650
         *  400-5  [4][j]    0       0       0       350     350     400     550     650     750     750     850
         *  500-5  [5][j]    0       0       0       350     350     500     550     650     850     850     900
         */

        //因为需要计算 i-1的情况, 所以直接从 i=1 开始. 则需要将 第0行都赋值为0
        for(int i = 1; i<=goldMineNumber; i++){
            for(int j =3; j<=worker; j++){    //因为列出来二维数组可见 第0,1,2列都为0 所以直接从第3列开始
                if(i==1){  // 只为赋一轮值
                    resultTable[0][j]=0;      //因为需要计算 i-1的情况, 又是从 i=1 开始. 则需要将 第0行都赋值为0
                }
                if(j>=p[i-1]){
                    resultTable[i][j] = Math.max(resultTable[i-1][j], resultTable[i-1][j-p[i-1]]+g[i-1]);
                }
            }
        }
        return resultTable[goldMineNumber][worker];
    }

    /**
     * 递归-状态转化方程式  时间复杂度为 O(2^n) , 因为很多重复的数据调用造成
     *
     * @param worker  工人总数
     * @param number  剩余工人数
     * @param p       金矿所需工人数组
     * @param g       金矿对应重量
     * @return
     */
    private int getBestWeight(int worker, int number, int[] p, int[] g) {
        if(worker <= 2){
            return 0;
        }
        if(number == 0){
            return 0;
        }
        return Math.max(getBestWeight(worker, number-1, p, g),
                        getBestWeight(worker-p[number-1], number-1, p, g)+g[number-1]);
    }
}
