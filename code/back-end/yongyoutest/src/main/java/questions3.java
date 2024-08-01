import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class questions3 {
    /**
     * 你有病吧，回溯+DP
     *输入一个N代表任务总数，输入一个tasks[]数组表示任务执行时间，
     * 输入一个M表示maxStep任务跳转最大步长，从头到尾，如果task[i]为-1就代表无法跳转，
     * 找到从头到尾的最小总任务执行时间；
     * 排序：1. 如果有方案1和方案2， 第1个不同索引处选最小的2. 如果所有索引相同，选任务切换次数最少的
     *
     */
    private static class Result {
        long minTime;
        int switchCount;

        Result(long minTime, int switchCount) {
            this.minTime = minTime;
            this.switchCount = switchCount;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // 任务总数
        int[] tasks = new int[N];
        for (int i = 0; i < N; i++) {
            tasks[i] = in.nextInt(); // 任务执行时间
        }
        int M = in.nextInt(); // 最大跳转步长
        in.close();

        Result result = findMinExecutionTime(tasks, M);

    }

    private static Result findMinExecutionTime(int[] tasks, int M) {
        int n = tasks.length;
        if (n == 0) return new Result(0, 0);
        /**
         * dp[i] 用于存储到达任务 i 的最小总执行时间。
         * prev[i] 用于存储到达任务 i 的前一个任务索引，以便回溯路径
         */
        long[] dp = new long[n];
        int[] prev = new int[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        Arrays.fill(prev, -1);

        dp[0] = tasks[0];
        prev[0] = 0;

        for (int i = 0; i < n; i++){
            if (tasks[i] == -1) continue;
            //j代表步长
            for(int j = 1; j<=M; j++){
                int next = i + j;
                if(next < n && tasks[next] != -1){
                    dp[next] = Math.min(dp[next], dp[i] + tasks[next]);
                }
            }
        }

        // 检查是否能到达最后一个任务
        if (dp[n - 1] == Long.MAX_VALUE) return new Result(-1, 0);



    }




        public static ArrayList<Integer> find(int taskNum, ArrayList<Integer> tasks, int maxStep){
        //dp代表到i的时候总任务执行时间
        int[] dp = new int[taskNum+1];

        //只能跳到i+k, (1<=k<=maxStep)
        //遍历tasks
        for(int i = 0; i<taskNum;i++){
            //在里面选
            for(int j = 1; j<=maxStep;j++){
                //能跳,在范围内
                if(i+j <= taskNum){
                    //执行最小，dp是总任务执行时间?
                    dp[i] = Math.min(dp[i-1], dp[i]+dp[j]);
                }
            }
        }



        return null;
    }
}
