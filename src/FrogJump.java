import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 蓝桥杯: 青蛙跳(BFS)
 */
class Node{
    /**
     * str: 当前字符串序列
     * steps: 完成字符串的所需步数
     */
    String str;
    int steps;

    public Node(String str, int steps) {
        this.str = str;
        this.steps = steps;
    }
}

public class FrogJump {
    private static String inputStr;
    private static String targetStr;
    private static Queue<Node> queue = new LinkedList<Node>();
    private static HashMap<String, Boolean> visited = new HashMap<String, Boolean>();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inputStr = sc.nextLine();
        targetStr = sc.nextLine();

        Node start = new Node(inputStr,0);
        int len = inputStr.length();
        queue.add(start);
        visited.put(start.str, true);

        while(queue.size() != 0){
            Node temp = queue.poll();
            int steps = temp.steps;
            char[] charArray = temp.str.toCharArray();

            //枚举当前字符串的每个位置的字母可能出现的六种情况
            for(int i = 0;i < len; i++){
                if(charArray[i] == '*')
                    continue;
                for (int j = -3; j <= 3; j++){
                    //排除无法移动的位置
                    if(i + j < 0 || i + j >= len || charArray[i+j] != '*' || j==0)
                        continue;

                    swap(charArray, i ,i+j);
                    String str = String.valueOf(charArray);
                    //visited中没有该序列, 默认第一次出现所用次数都是最优
                    if(!visited.containsKey(str)){
                        Node node = new Node(str,steps+1);
                        queue.add(node);
                        visited.put(String.valueOf(charArray),true);
                    }
                    //如何符合情况就输出
                    if(targetStr.equalsIgnoreCase(str)){
                        System.out.println(steps+1);
                        return;
                    }
                    //把字符串还原继续下次交换操作
                    swap(charArray,i,i+j);
                }
            }
        }

    }

    public static char[] swap(char[] charArray, int i, int j){
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;

        return charArray;
    }
}


