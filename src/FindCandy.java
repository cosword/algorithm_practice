import java.util.HashSet;
import java.util.Scanner;

/**
 * 找出2个质数最大不能组合数
 */
public class FindCandy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int min = Math.min(n,m);
        int target = n * m;
        int result;
        HashSet<Integer> hashSet = new HashSet<Integer>();

        for(int i = 0; i <= m ; i++){
            for(int j = 0; j <= n; j++){
                if((result = (i*n + j*m)) <= target){
                    hashSet.add(result);
                }else{
                    break;
                }
            }
        }

        for (int i=target; i>=min; i--){
            if(!hashSet.contains(i)){
                System.out.println(i);
                break;
            }
        }
    }
}
