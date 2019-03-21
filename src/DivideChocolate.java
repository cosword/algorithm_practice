import sun.font.TrueTypeFont;

import java.util.Scanner;

/**
 * 2017蓝桥杯: 8.分巧克力
 * 枚举所有正方形边长
 */
public class DivideChocolate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int H[] = new int[1000];
        int W[] = new int[1000];
        int max = 1;
        int sum = 0;

        for (int i=0; i < N; i++){
            H[i] = sc.nextInt();
            W[i] = sc.nextInt();
        }

        //System.out.println("输入完成");
        while (true){
            sum = 0;
            for(int j = 0; j < H.length; j++){
                sum += (H[j] / max) * (W[j] / max);
            }
            if(sum < K)
                break;
            max++;
        }

        System.out.println(max - 1);
    }
}
