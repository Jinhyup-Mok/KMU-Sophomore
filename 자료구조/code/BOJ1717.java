import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class  BOJ1717{

    public static int find(int u, int[] pr) {
        if(pr[u] == -1) return u;
        int v = u;
        while(pr[u] != -1) { // pr[u] != -1 : -1이라는 의미는 해당 노드가 최상위 root노드라는 것!
            u = pr[u]; // 이단계에서 u는 입력된 노드의 root노드를 가리킨다.
        } 
        // 위의 while문 종료후 u는 최상위 노드!
        int p;
        // Path Compression!!!!
        while(pr[v] != u) { // 초기에 입력된 u가 최상위 노드가 되기 전까지.
            p = pr[v]; // 임시 변수 p에 v에 해당하는 root노드값을 담는다.
            pr[v] = u; // v에 해당하는 root값을 u로 변경 -> 최상위 노드로 모두 연결!
            v = p; // v = v에 해당하는 root노드값
            // 다시 올라가서 아직도 최상위 노드가 아닐때 계속 반복
        }
        return u;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] pr = new int[n+1];
        for(int i=0; i<pr.length; i++) {
            pr[i] = -1; // 초기화
        }
        for(int i=0; i<m ;i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st2.nextToken());
            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            
            if(c == 0) {
                // union
                int ur = find(u, pr); // root를 찾는다.
                int vr = find(v, pr);

                if(ur != vr) { // root가 다를 경우
                  pr[vr] = ur; // root에 붙이기
                }
                  
            } else {
                if(u == v) {
                    System.out.println("YES"); 
                    continue;
                }
                // find, find, check
                int ur = find(u, pr);
                int vr = find(v, pr);

                if(ur == vr) System.out.println("YES");
                else System.out.println("NO");
            }
        }
        br.close();
    }
}
