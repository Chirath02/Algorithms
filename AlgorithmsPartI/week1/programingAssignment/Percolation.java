import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int numberOfSites, virtualTop, virtualBottom;
    private int numberOfOpenSites;
    private boolean[][] openSites;
    private final WeightedQuickUnionUF uf, ufVirtualTopOnly;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufVirtualTopOnly = new WeightedQuickUnionUF(n * n + 2);
        numberOfSites = n;
        numberOfOpenSites = 0;
        virtualTop = 0;
        virtualBottom = (n * n) + 1;
        openSites = new boolean[n][n];

        for (int i = 1; i <= n; ++i) {
            uf.union(virtualTop, i);
            ufVirtualTopOnly.union(virtualTop, i);
        }
    }

    private int convertToIndex(int row, int col) {
        return (numberOfSites * (row - 1)) + col;
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        isInBound(row, col);
        if (isOpen(row, col))
            return;

        numberOfOpenSites++;
        openSites[row - 1][col - 1] = true;

        int siteNumber = convertToIndex(row, col);
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(siteNumber, convertToIndex(row, col - 1));
            ufVirtualTopOnly.union(siteNumber, convertToIndex(row, col - 1));
        }
        // right
        if (col < numberOfSites && isOpen(row, col + 1)) {
            uf.union(siteNumber, convertToIndex(row, col + 1));
            ufVirtualTopOnly.union(siteNumber, convertToIndex(row, col + 1));
        }
        // up
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(siteNumber, convertToIndex(row - 1, col));
            ufVirtualTopOnly.union(siteNumber, convertToIndex(row - 1, col));
        }
        // down
        if (row < numberOfSites && isOpen(row + 1, col)) {
            uf.union(siteNumber, convertToIndex(row + 1, col));
            ufVirtualTopOnly.union(siteNumber, convertToIndex(row + 1, col));
        }

        if (row == numberOfSites) {
            uf.union(virtualBottom, siteNumber);
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        isInBound(row, col);
        return openSites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        isInBound(row, col);
        return isOpen(row, col) && ufVirtualTopOnly.connected(0, convertToIndex(row, col));
    }

    public int numberOfOpenSites() {
        // number of open sites
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return uf.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        // test client (optional)
    }

    private void isInBound(int row, int col) {
        if (row < 1 || row > numberOfSites || col < 1 || col > numberOfSites)
            throw new java.lang.IllegalArgumentException();
    }
}
