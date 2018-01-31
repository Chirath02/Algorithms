import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    private final int n, trials;
    private final double meanResult, stddevResult;
    private final static double confidence95 = 1.96;
    private double[] results;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0  || trials <= 0)
            throw new java.lang.IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        results = new double[trials];
        calculate();
        meanResult = StdStats.mean(results);
        stddevResult = StdStats.stddev(results);
    }

    private double trial() {
        Percolation percolation = new Percolation(n);
        int randomSiteRow, randomSiteCol;
        while (!percolation.percolates()) {
            randomSiteRow = StdRandom.uniform(1, n + 1);
            randomSiteCol = StdRandom.uniform(1, n + 1);
            percolation.open(randomSiteRow, randomSiteCol);
        }
        return (((double) percolation.numberOfOpenSites()) / (n * n));
    }

    private void calculate() {
        for (int i = 0; i < trials; ++i)
             results[i] = trial();
    }

    public double mean() {
        // sample mean of percolation threshold
        return meanResult;
    }
    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddevResult;
    }
    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return (meanResult - ((confidence95 * stddevResult) / Math.sqrt(trials)));
    }
    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return (meanResult + ((confidence95 * stddevResult) / Math.sqrt(trials)));
    }
    public static void main(String[] args) {
        // test client (described below)
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean\t\t\t= " + percolationStats.mean());
        StdOut.println("stddev\t\t\t= " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
