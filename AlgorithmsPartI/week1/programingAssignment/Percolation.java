import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;


public class Percolation {
    private int number_of_sites, number_of_open_sites;
    private boolean[][] open_sites;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) throws java.lang.IllegalArgumentException {               // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);
        number_of_sites = n;
        number_of_open_sites = 0;
        open_sites = new boolean[n][n];
        for (int i = 0; i < n; ++i)
            uf.union(0, i + 1);
    }

    public void open(int row, int col) throws java.lang.IllegalArgumentException {   // open site (row, col) if it is not open already
        if (row < 1 || col > number_of_sites)
            throw new java.lang.IllegalArgumentException();
        if (isOpen(row, col))
            return;
        number_of_open_sites++;
        open_sites[row - 1][col - 1] = true;
        int site_number = number_of_sites * (row - 1) + col;
        boolean connected = false;
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(site_number, number_of_sites * (row - 1) + col - 1);
            connected = true;
        }
        // right
        if (col < number_of_sites && isOpen(row, col + 1)) {
            uf.union(site_number, number_of_sites * (row - 1) + col + 1);
            connected = true;
        }
        // up
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(site_number, number_of_sites * (row - 2) + col);
            connected = true;
        }
        // down
        if (row < number_of_sites && isOpen(row + 1, col)) {
            uf.union(site_number, number_of_sites * row + col);
            connected = true;
        }

        // connect to virtual site at bottom if the site is in the last row
        if (connected) {
            for (int i = 1; i <= number_of_sites; ++i) {
                site_number = ((number_of_sites - 1) * number_of_sites) + i;
                if (uf.connected(0, site_number))
                    uf.union(site_number, (number_of_sites * number_of_sites) + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) throws java.lang.IllegalArgumentException { // is site (row, col) open?
        if (row < 1 || col > number_of_sites)
            throw new java.lang.IllegalArgumentException();
        // StdOut.println(row + " "  + col + open_sites[row - 1][col - 1]);
        return open_sites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) throws java.lang.IllegalArgumentException { // is site (row, col) full?
        if (row < 1 || col > number_of_sites)
            throw new java.lang.IllegalArgumentException();
        // StdOut.println(uf.connected(0, number_of_sites * (row - 1) + col));
        return isOpen(row, col) && uf.connected(0, number_of_sites * (row - 1) + col);
    }

    public int numberOfOpenSites() {     // number of open sites
        return number_of_open_sites;
    }

    public boolean percolates() {            // does the system percolate?
        return uf.connected(0, number_of_sites * number_of_sites + 1);
    }

    public static void main(String[] args) {  // test client (optional)

    }
}
