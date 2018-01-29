import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;


public class PercolationStats {
    private int n, trials;
    private double mean_result = 0.0, stddev_result = 0.0;
    private double[] results;

    private double trial() {
        Percolation percolation = new Percolation(n);
        int random_site_row, random_site_col;
        while (!percolation.percolates()) {
            random_site_row = StdRandom.uniform(1, n + 1);
            random_site_col = StdRandom.uniform(1, n + 1);
            percolation.open(random_site_row, random_site_col);
        }
        return (((double)percolation.numberOfOpenSites()) / (n * n));
    }

    private void calculate() {
        double result;
        for (int i = 0; i < trials; ++i)
             results[i] = trial();
    }

    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n <= 0  || trials <= 0)
            throw new java.lang.IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        results = new double[trials];
        calculate();
        mean_result = StdStats.mean(results);
        stddev_result = StdStats.stddev(results);
    }

    public double mean() {                          // sample mean of percolation threshold
        return mean_result;
    }
    public double stddev() {                        // sample standard deviation of percolation threshold
        return stddev_result;
    }
    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return (mean_result - ((1.96 * stddev_result) / Math.sqrt(trials)));
    }
    public double confidenceHi() {                  // high endpoint of 95% confidence interval
        return (mean_result + ((1.96 * stddev_result) / Math.sqrt(trials)));
    }
    public static void main(String[] args) {       // test client (described below)
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean\t\t\t= " + percolationStats.mean());
        StdOut.println("stddev\t\t\t= " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
