import java.util.Scanner;

public class questions2 {
    /**
     *
     * 输入一个N和M， M拆成n份（n>=2）并且相乘， 判断相乘结果是否大于N
     * 1. M拆成n份，要求最大乘积
     * 2. 判断
     * 这道题主要是在于如何求得最大乘积吧
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        in.close();
        // 计算最大乘积
        long maxProduct = getMaxProduct(M);

        // 输出结果
        System.out.println(maxProduct > N ? "Yes" : "No");
    }
    private static long getMaxProduct(int M){
        if (M <= 1) return 0; // M <= 1 无有效拆分

        long[] dp = new long[M + 1];
        dp[1] = 1; // 单独的1没有意义，初始值
        for(int i = 2; i<=M;i++){
            for(int j = 1;j<i;j++){
                //j * (i - j):拆分 i 为 j 和 i - j 两部分后的乘积
                //j * dp[i - j]: 继续拆分
                //
                dp[i] =  Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[M];
    }
}
