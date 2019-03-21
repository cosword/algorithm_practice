
import java.util.Scanner;

/**
 * 2017蓝桥杯:3.求取重量
 */
public class CountWeight {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[30][30];
        double max, min;

        //1、读取输入
        for (int i= 0; i < 29; i++){
            for (int j = 0; j <= i; j++){
                matrix[i][j] = scanner.nextDouble();
            }
        }
        //System.out.println("读取完成");
        //2、计算重量, 自顶向下
        for(int i = 0; i < 29; i++){
            for(int j = 0; j <= i; j++){
                matrix[i + 1][j] += matrix[i][j]/2;
                matrix[i + 1][j + 1] += matrix[i][j] / 2;
            }
        }

        max = matrix[29][0];
        min = max;
        //3、找出最大值
        for (int i = 1; i <= 29; i++){
            max = Math.max(max, matrix[29][i]);
            min = Math.min(min, matrix[29][i]);
        }

        System.out.println((long) (max * (2086458231 / min)));

    }
}


