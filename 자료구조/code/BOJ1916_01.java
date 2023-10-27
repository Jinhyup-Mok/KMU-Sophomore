import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1916 {

    public static int[][] node;
    public static long[] d;
    public static boolean[] visit;
    public static int n;
    public static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
       
        StringTokenizer st_ = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st_.nextToken());
        node = new int[n+1][n+1];

        for (int i=1; i<n+1; i++) {
            for (int j=1; j<n+1; j++) {
                if(i==j) {
                    node[i][j] = 0;
                    continue;
                }
                node[i][j] = Integer.MAX_VALUE-1;
            }
        }

        for(int i=0; i<m; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            int bus = Integer.parseInt(st1.nextToken());

            if(node[start][end] == -1) node[start][end] = bus;
            else if(node[start][end] > bus) node[start][end] = bus;
        }

        d = new long[n+1];
        visit = new boolean[n+1];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st2.nextToken());
        int b = Integer.parseInt(st2.nextToken());


        dijkstra(a);
        System.out.println(d[b]);

    }

    public static int minNode() {
        long min = Integer.MAX_VALUE;
        int idx = 0;
        for(int i=1; i<n+1; i++) {
            if(d[i] < min && !visit[i]) {
                min = d[i];
                idx = i;
            }
        }
        return idx;
    }

    public static void dijkstra(int start_node) {
        for(int i=1; i<n+1; i++) {
            d[i] = node[start_node][i]; // 시작노드와 연결된 노드까지의 경로 비용저장
        }
        visit[start_node] = true; // 방문 처리
        for(int i=0; i<n-1; i++) {
            int curr = minNode();
            visit[curr] = true;
            for(int j=1; j<n+1; j++) {
                if(!visit[j]) {
                    if(d[curr] + node[curr][j] < d[j]) {
                        d[j] = d[curr]+node[curr][j];
                    } 
                }
            }
        }
    }
}