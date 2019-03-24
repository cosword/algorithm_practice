

/**
 * 2019蓝桥杯第三题，求模取后四位相加即可
 *
 */
public class ComputeXn {
    public static void main(String[] args) {
        long[] a = new long[20190325];
        a[1] = 1;
        a[2] = 1;
        a[3] = 1;

        for (int i = 4; i <= 20190324; i++) {
            a[i] = a[i - 1] % 1000 + a[i - 2] % 10000 + a[i - 3] % 10000;
        }
        System.out.println(a[20190324]);
    }
}


