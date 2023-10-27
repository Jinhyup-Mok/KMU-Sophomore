import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753_priorityQueue {
    public static List<ArrayList<Node>> arraylist;
    public static int[] d;
    public static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        arraylist = new ArrayList<>();
        d = new int[n+1];
        for(int i = 0; i<n+1; i++) {
            arraylist.add(new ArrayList<>());
            d[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arraylist.get(u).add(new Node(v, w));
        }

        dijkstra(k);
        StringBuilder stb = new StringBuilder();
        for(int i = 1; i<n+1; i++){
            if(d[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(d[i]);
        }
        System.out.print(stb);
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> node = new PriorityQueue<>();
        d[start] = 0;
        node.add(new Node(start, 0));
        while(!node.isEmpty()) {
            
            Node curr = node.poll();
            int size = arraylist.get(curr.v).size();

            for(int i = 0; i<size; i++) {

                Node next = (Node)arraylist.get(curr.v).get(i);

                if(curr.w + next.w < d[next.v]) {
                    d[next.v] = curr.w + next.w;
                    node.add(new Node(next.v, d[next.v]));
                }
            }
        }
    }
}
class Node implements Comparable<Node>{
    int v, w;
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
    @Override
    public int compareTo(Node n){ return this.w - n.w;}
}
