/* Quick Union implementation */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int number_of_objects) {
        id = new int[number_of_objects];
        for (int i = 0; i < number_of_objects; ++i)
            id[i] = i;
    }

    public int root(int element) {
        if (id[element] == element)
            return element;
        return root(id[element]);
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        id[root(p)] = root(q);
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
            else {
                StdOut.println("Already connected");
            }
        }
    }
}
