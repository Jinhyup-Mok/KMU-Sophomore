import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1753_array {

    public static int n;
    public static long[][] node;
    public static long[] d;
    public static boolean[] visit;
    public static int INF = Integer.MAX_VALUE;
    public static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        node = new long[n+1][n+1];

        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(i==j) {
                    node[i][j] = 0;
                } else {
                    node[i][j] = INF-1;
                }
            }
        }

        for(int i=0; i<e ; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(node[u][v] == -1) node[u][v] = w;
            else if(node[u][v] > w) node[u][v] = w;
        }
        d = new long[n+1];
        visit = new boolean[n+1];

        dijkstra(k);
        for(int i=1; i<n+1; i++) {
            if(d[i] == INF-1) System.out.println("INF");
            else System.out.println(d[i]);
        }
    }
    public static int minNode() {
        long min = Integer.MAX_VALUE;
        int idx = 0;
        for(int i=1; i<n+1; i++) {
            if(!visit[i] && d[i] < min) {
                min = d[i];
                idx = i;
            }
        }
        return idx;
    }

    public static void dijkstra(int start) {
        for(int i=1; i<n+1; i++) {
            d[i] = node[start][i];
        }
        visit[start] = true;
        for(int i=0; i<n-1; i++) {
            int curr = minNode();
            visit[curr] = true;
            for(int j=1; j<n+1; j++) {
                if(!visit[j] && d[curr] + node[curr][j] < d[j]) d[j] = d[curr] + node[curr][j];
            }
        }
    }
}