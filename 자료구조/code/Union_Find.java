/**
 * union_find
 */
public class Union_Find {

    public static int find(int n, int[] rt) {
        if(rt[n] == -1) return n;
        int v = n;
        while(rt[n] != -1) {
            n = rt[n];
        }
        int p;
        while(rt[v] != v) {
            p = rt[v];
            rt[v] = n;
            p = n;
        }
        return n;
        // Path Compression!!!!
    }
    public static void main(String[] args) {
        
    }
}