import com.sun.javafx.collections.MappingChange;

import java.util.*;

/**
 * 蓝桥杯: 大臣的旅行(非连通图DFS)
 * 数据到10000还是会运行超时
 *  改进
 *  1、使用多线程
 *  2、使用单变量只记录同一起点的路径最大距离
 *  3、使用LinkeList做next,改成邻接表
 */
class Address{
    public int id;
    public LinkedList<Integer> next = new LinkedList<Integer>();
    public Address(int id){
        this.id = id;
    }
}

public class MinisterTravel {
    private static Queue<Address> queue = new LinkedList<Address>();
    private static HashMap<Integer, Address> map = new HashMap<Integer,Address>();
    private static Hashtable<Integer,Address> table;
    private static Map<String, Integer> road = new HashMap<String, Integer>();
    private static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        matrix = new int[N+1][N+1];
        //这里写复杂了，就是想创建N个城市的顶点
        for(int i = 1; i < N ; i++){
           int x = sc.nextInt();
           int y = sc.nextInt();
           int dis = sc.nextInt();
           if(!map.containsKey(x)){
               Address address = new Address(x);
               address.next.add(y);
               map.put(x, address);
               queue.add(address);
           }else{
               Address address = map.get(x);
               address.next.add(y);
           }

           if(!map.containsKey(y)){
               Address address = new Address(y);
               address.next.add(x);
               map.put(y, address);
               queue.add(address);
           }else{
               Address address = map.get(y);
               address.next.add(x);
           }

           matrix[x][y] = dis;
           matrix[y][x] = dis;
        }
        //System.out.println(map.get(2).next.size());

        while (queue.size()!=0){
            Address address = queue.poll();
            //给个节点开头的路径配置一个visited数组
            Boolean visited[] = new Boolean[N + 1];
            for(int j = 0; j <= N; j++){
                visited[j] = false;
            }
            //存在与该点相连的其他顶点
            if(address.next.size() != 0 ){
                DFS(address, String.valueOf(address.id),visited);
            }

        }

        //对HashMap排序，这里会增加时间和空间复杂度
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(road.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<String,Integer> mapping:list){
            //System.out.println(mapping.getKey()+":"+mapping.getValue());
            char[] arr = mapping.getKey().toCharArray();
            int free = computeFree(mapping.getValue());
            System.out.println(free);
            break;
//            if(Integer.valueOf(arr[0]) > Integer.valueOf(arr[arr.length - 1]))
//                System.out.println("大臣J从城市" + arr[arr.length - 1] + "到城市" + arr[0]
//                        + "要花费" + String.valueOf(free) + "的路费");
//            else
//                System.out.println("大臣J从城市" + arr[0] + "到城市" + arr[arr.length - 1]
//                        + "要花费" + String.valueOf(free) + "的路费");
//            break;
        }


    }

    public static void DFS(Address address, String start, Boolean[] visited){
        visited[address.id] = true;
        for (Integer id:address.next){
            if(visited[id] == true)
                continue;
            String dis = start + "-" + String.valueOf(id);
            if(!road.containsKey(dis) && visited[id] != true){
                if(road.get(start) != null)
                    road.put(dis, road.get(start)+matrix[address.id][id]);
                else
                    road.put(dis, matrix[address.id][id]);
            }

            if(map.get(id).next.size() != 0){
                DFS(map.get(id), dis, visited);
            }
            else
                return;
        }
        return;
    }

    public static int computeFree(int x){
        int sum = 0;
        for(int i = 1; i <= x; i++)
            sum += (i+10);
        return sum;
    }
}



