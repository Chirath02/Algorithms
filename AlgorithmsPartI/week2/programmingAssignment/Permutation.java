import java.lang.String;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int numberOfElements = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());

        for (String s : rq)
            StdOut.print(s + " ");
        StdOut.println();

        for (int i = 0; i < numberOfElements; ++i)
            StdOut.println(rq.dequeue());
    }
}
