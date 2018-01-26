/* Quick Union implementation */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id, sz;

    public WeightedQuickUnionUF(int number_of_objects) {
        id = new int[number_of_objects];
        sz = new int[number_of_objects];
        for (int i = 0; i < number_of_objects; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
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
        int rootp = root(p);
        int rootq = root(q);

        if (rootp == rootq) return;

        if (sz[rootp] > sz[rootq]) {
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];
        }
        else {
            id[rootp] = rootq;
            sz[rootq] += sz[rootp];
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);

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
