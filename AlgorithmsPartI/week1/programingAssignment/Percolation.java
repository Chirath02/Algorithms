import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int numberOfSites;
    private int numberOfOpenSites;
    private boolean[][] openSites;
    private final WeightedQuickUnionUF uf;

    public Percolation(int n) {               // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);
        numberOfSites = n;
        numberOfOpenSites = 0;
        openSites = new boolean[n][n];
        for (int i = 0; i < n; ++i)
            uf.union(0, i + 1);
    }

    public void open(int row, int col) {   // open site (row, col) if it is not open already
        isInBound(row, col);
        if (isOpen(row, col))
            return;
        numberOfOpenSites++;
        openSites[row - 1][col - 1] = true;
        int siteNumber = numberOfSites * (row - 1) + col;
        // left
        if (col > 1 && isOpen(row, col - 1))
            uf.union(siteNumber, numberOfSites * (row - 1) + col - 1);
        // right
        if (col < numberOfSites && isOpen(row, col + 1))
            uf.union(siteNumber, numberOfSites * (row - 1) + col + 1);
        // up
        if (row > 1 && isOpen(row - 1, col))
            uf.union(siteNumber, numberOfSites * (row - 2) + col);
        // down
        if (row < numberOfSites && isOpen(row + 1, col))
            uf.union(siteNumber, numberOfSites * row + col);

        // connect to virtual site at bottom if the site is in the last row
        for (int i = 1; i <= numberOfSites; ++i) {
            siteNumber = ((numberOfSites - 1) * numberOfSites) + i;
            if (uf.connected(0, siteNumber))
                uf.union(siteNumber, (numberOfSites * numberOfSites) + 1);
        }
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        isInBound(row, col);
        // StdOut.println(row + " "  + col + openSites[row - 1][col - 1]);
        return openSites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        isInBound(row, col);
        // StdOut.println(uf.connected(0, numberOfSites * (row - 1) + col));
        return isOpen(row, col) && uf.connected(0, numberOfSites * (row - 1) + col);
    }

    public int numberOfOpenSites() {     // number of open sites
        return numberOfOpenSites;
    }

    public boolean percolates() {            // does the system percolate?
        return uf.connected(0, numberOfSites * numberOfSites + 1);
    }

    public static void main(String[] args) {  // test client (optional)

    }

    private void isInBound(int row, int col) {
        if (row < 1 || row > numberOfSites || col < 1 || col > numberOfSites)
            throw new java.lang.IllegalArgumentException();
    }
}
