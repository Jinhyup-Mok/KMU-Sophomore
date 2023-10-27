import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


/**
 * AC
 */
public class BOJ5430 {

    public static ArrayDeque<Integer> deq;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bf.readLine());

        for(int i=0; i<t; i++) {
            StringTokenizer st1 = new StringTokenizer(bf.readLine());
            String control = st1.nextToken();
            
            n = Integer.parseInt(bf.readLine());

            StringTokenizer st2 = new StringTokenizer(bf.readLine(), "[],");
            deq = new ArrayDeque<>();
            for(int j=0; j<n; j++) {
                deq.add(Integer.parseInt(st2.nextToken()));
            }
            AC(control);
            
        }
    }
    public static void AC(String control) {
        for(int i=0; i<control.length(); i++) {
            if(control.charAt(i) == 'R') {
                ArrayDeque<Integer> newdeq = new ArrayDeque<>();
                for(int j=0; j<n; j++) {
                   int x = deq.pollLast();
                   newdeq.addLast(x);
                }
                deq = newdeq;
            } 
            if(control.charAt(i) == 'D') {
                if(deq.size() == 0) {
                    System.out.println("error");
                    return;
                } else {deq.remove();}
            }
        }
        System.out.println(deq);
    }
}