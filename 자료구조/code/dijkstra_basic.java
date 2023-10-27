public class dijkstra_basic {

    public static int number = 6;
    public static int INF = 1000000000;

    public static boolean[] v; // 방문한 노드
    public static int[] d; // 최단 거리
    public static int[][] a = {
            {0,2,5,1,INF,INF},
            {2, 0, 3, 2, INF, INF},
            {5,3,0,3,1,5},
            {1,2,3,0,1,INF},
            {INF,INF,1,1,0,2},
            {INF,INF,5,INF,2,0},
    };
    public static void main(String[] args) {
        v = new boolean[6]; // 방문한 노드
        d = new int[6]; // 최단 거리

        dijkstra(0);
        for(int i=0; i<number; i++) {
            System.out.println(d[i]);
        }
    }

    // 가장 최소 거리를 가지는 정점 반환.
    public static int getSmallIndex() {
        int min = INF;
        int index = 0;
        for(int i=0; i<number;i++) {
            if(d[i] < min && !v[i]) {
                // 방문하지 않는 노드중에서~ 현재 최솟값보다 작은값이 존재한다면
                // 그 값으로 값 갱신 -> 그 위치 반환
                min = d[i];
                index = i;
            }
        }
        return index;
    }

    // 다익스트라를 수행하는 함수
    public static void dijkstra(int start) {
        for(int i=0; i<number; i++) {
            d[i] = a[start][i]; // 시작점으로 부터 시작했을때 모든 경로의 비용을 담는다
        }
        v[start] = true; // 방문 완료 표시
        for(int i=0; i<number-2; i++) {
            int curr = getSmallIndex();
            v[curr] = true;
            for(int j=0; j<6; j++) {
                if(!v[j]) {
                    if(d[curr] + a[curr][j] < d[j]) {
                        d[j] = d[curr] + a[curr][j];
                    }
                }
            }
        }
    }
}