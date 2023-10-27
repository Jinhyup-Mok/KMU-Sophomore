import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10828 {
    public static int top_idx;
    public static int maxsize;
    public static int size;
    public static int idx = 0;
    public static int[] stack;
    public static void push(int n, int[] stack) {
        stack[idx] = n;
        top_idx= idx;
        idx++;
        size++;
    }
    public static int pop() {
        int del;
        if(size != 0) {
            del = stack[top_idx];
            stack[top_idx--] = 0;
            size--;
        } else {
            del = -1;
        }
        return del;
        
    }
    public static int top() {
        if(size != 0) return stack[top_idx];
        else return -1;
    }
    public static int size() {return size;}

    public static int empty() {
        if(size == 0) return 1;
        else return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        maxsize = n;
        stack = new int[maxsize];

        for(int i=0; i<n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String arg = st1.nextToken();
            if(arg.equals("push")) {
                int x = Integer.parseInt(st1.nextToken());
                push(x, stack);
            }
            if(arg.equals("pop")) {
                System.out.println(pop());
            }
            if(arg.equals("size")) {
                System.out.println(size());
            }
            if(arg.equals("empty")) {
                System.out.println(empty());
            }
            if(arg.equals("top")) {
                System.out.println(top());
            }
        }
    }
}
