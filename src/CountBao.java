import java.util.Scanner;

/**
 * 2017蓝桥杯
 * 标题：包子凑数
 *
 * 小明几乎每天早晨都会在一家包子铺吃早餐。他发现这家包子铺有N种蒸笼，其中第i种蒸笼恰好能放Ai个包子。每种蒸笼都有非常多笼，可以认为是无限笼。
 *
 * 每当有顾客想买X个包子，卖包子的大叔就会迅速选出若干笼包子来，使得这若干笼中恰好一共有X个包子。比如一共有3种蒸笼，分别能放3、4和5个包子。当顾客想买11个包子时，大叔就会选2笼3个的再加1笼5个的（也可能选出1笼3个的再加2笼4个的）。
 *
 * 当然有时包子大叔无论如何也凑不出顾客想买的数量。比如一共有3种蒸笼，分别能放4、5和6个包子。而顾客想买7个包子时，大叔就凑不出来了。
 *
 * 小明想知道一共有多少种数目是包子大叔凑不出来的。
 *
 * 输入
 * ----
 * 第一行包含一个整数N。(1 <= N <= 100)
 * 以下N行每行包含一个整数Ai。(1 <= Ai <= 100)
 */

public class CountBao {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N + 1];
        for(int i = 1; i <= N; i++){
            arr[i] = sc.nextInt();
        }

        int yueshu = arr[1];
        for(int i = 2; i <= N; i++){
            yueshu = yue(yueshu,arr[i]);
        }

        if(yueshu != 1){
            System.out.println("INF");
        }else {
            boolean dp[] = new boolean[10010];
            dp[0] = true;
            for(int i = 1; i <= N; i++){
                for(int j = 0; j + arr[i] <= 10000; j++){
                    //i=1时，把arr[Ai]的倍数全置为True，后面几轮循环，除了arr[Ai]自己的倍数会为True外
                    //与前面Arr[i-1]的倍数的和也为True，每一轮都计算出累加和
                    if(dp[j]){
                        System.out.println(j + arr[i]);
                        dp[j+arr[i]] = true;
                    }
                }
            }
            int sum = 0;
            for(int i = 0; i <= 10000; i++){
                if(dp[i] == false)
                    sum++;
            }
            System.out.println(sum);
        }


    }

    //辗转相除法
    public static int yue(int fenzi, int fenmu){
        if(fenzi % fenmu == 0)
            return fenmu;
        else
            return yue(fenmu, fenzi % fenmu);
    }
}
