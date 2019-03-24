import java.util.Scanner;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

class Main
{
    static int n,map[][],vis[][];
    static boolean dfs(int i,int j)
    {
        if(i<0||i>=n||j<0||j>=n||vis[i][j]==1) return false;
        vis[i][j]=1;
        boolean f=false;
        f = GlobalWarming.getaBoolean(i, j, f, map);

        return dfs(i, j+1)||dfs(i, j-1)||dfs(i+1, j)||dfs(i-1, j)||f;
    }
    public static void main(String args[])
    {
        int r=0,t=0;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        map=new int[n][n];
        vis=new int[n][n];
        for(int i=0;i<n;i++)
        {
            String s=sc.next();
            for(int j=0;j<n;j++)
                if(s.charAt(j)!='.')
                    map[i][j]=1;
                else
                    vis[i][j]=1;
        }

        System.out.println();
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(vis[i][j]);
            }
            System.out.println();
        }

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if (vis[i][j] != 1) {
                    r++;
                    t += dfs(i, j) ? 1 : 0;
                }

        System.out.println("r="+String.valueOf(r));
        System.out.println("t="+String.valueOf(t));
        System.out.println(r-t);
    }
}
