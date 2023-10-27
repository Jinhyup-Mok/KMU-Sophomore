import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 최소 스패닝 트리
class Edge implements Comparable<Edge> {
    int s, e;
    double c;
    public Edge(int s, int e, double c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }
    @Override
    public int compareTo(Edge o) { return Double.compare(this.c, o.c); }
}

class Star {
    double x, y;
    public Star(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ4386 {

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
    public static double star_dist(Star s1, Star s2) {
        return Math.sqrt(Math.pow(Math.abs(s1.x - s2.x), 2) + Math.pow(Math.abs(s1.y - s2.y), 2));
    }

    public static int n;
    public static double x;
    public static double y;
    public static double cost;
    public static int[] parent;
    public static PriorityQueue<Edge> star = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        

        n = Integer.parseInt(st.nextToken());

        Star[] arr = new Star[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            x = Double.parseDouble(st1.nextToken());
            y = Double.parseDouble(st1.nextToken());
            arr[i] = new Star(x, y);
        }
        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                star.add(new Edge(i, j, star_dist(arr[i], arr[j])));
            }
        }
        parent = new int[n+1];
        for(int i=1; i<n+1; i++) {
            parent[i] = i;
        }

        double star_cost = 0.0;
        for(int i=0; i<star.size(); i++) {
            Edge e = star.poll();
            int rx = find(e.s);
            int ry = find(e.e);
            if(rx != ry) {
                star_cost += e.c;
                parent[rx] = ry;
            }
        }
        System.out.printf("%.2f", star_cost);
        System.out.print("\n");
    }
}

