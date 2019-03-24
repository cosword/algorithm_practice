import java.util.Scanner;

/**
 * 2018蓝桥杯: 全球变暖
 * 值得研究
 */
public class GlobalWarming {
    static int N, map[][];
    static Boolean visited[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dao = 0, sank = 0;
        N =  sc.nextInt();
        map = new int[N][N];
        visited = new Boolean[N][N];

        for(int i=0; i < N; i++){
            char[] str = sc.nextLine().toCharArray();
            for (int j=0; j < N; j++){
                if(str[j] == '#')
                    map[i][j] = 1;
                else
                    visited[i][j] = true;
            }
        }

        for (int i = 1; i <= N-2; i++){
            for(int j = 1; j <= N - 2; j++){
                if(visited[i][j] != true){
                    //岛的数量
                    sank ++;
                    //岛若能存活，dao++
                    dao += DFS(i,j)?1:0;
                }
            }
        }

        System.out.println(sank - dao);
    }

    public static Boolean DFS(int i, int j){
        if(i < 0 || j < 0 || i >= N  || j >= N || visited[i][j] == true)
            return false;
        visited[i][j] = true;
        Boolean flag = false;

        flag = getaBoolean(i, j, flag, map);
        //利用或运算符的特性，做到能访问到周围的四个点
        return DFS(i,j+1) || DFS(i,j-1) || DFS(i+1,j) || DFS(i-1,j) || flag;
    }

    public static Boolean getaBoolean(int i, int j, Boolean flag, int[][] map) {
        if(map[i][j+1] == 1 && map[i][j-1] == 1 && map[i-1][j] == 1 && map[i+1][j] == 1)
            flag = true;
        return flag;
    }
}
