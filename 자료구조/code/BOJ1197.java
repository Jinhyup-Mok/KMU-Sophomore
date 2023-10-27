import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 최소 스패닝 트리
class Edge implements Comparable<Edge> {
    int s, e, c;
    public Edge(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }
    @Override
    public int compareTo(Edge o) { return this.c - o.c; }
}

public class BOJ1197 {

    public static int find(int unode) {
        int rnode = unode;

        while(parent[rnode] != rnode) rnode = parent[rnode];
        while(parent[unode] != unode) {
            int tmp = parent[unode];
            parent[unode] = rnode;
            unode = tmp;
        }
        return rnode;
    }

    public static int V;
    public static int E;
    public static int start;
    public static int end;
    public static int cost;
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Edge> node = new ArrayList<>();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken()); 

        for(int i=0; i<E; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            int cost = Integer.parseInt(st1.nextToken());

            node.add(new Edge(start, end, cost));
        }

        parent = new int[V+1];
        for(int i=1; i<V+1; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> edges = new PriorityQueue<>(node);

        int cost_sum = 0;
        while(!edges.isEmpty()) {
            Edge e = edges.poll();
            
            int start_r = find(e.s);
            int end_r = find(e.e);
            if(start_r != end_r) {
                cost_sum += e.c;
                parent[start_r] = end_r;
            }
        }
        System.out.println(cost_sum);
    }
}

