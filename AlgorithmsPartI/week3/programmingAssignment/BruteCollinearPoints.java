import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class BruteCollinearPoints {
    private LineSegment[] lineSegment;
    private int numberOfLineSegments = 0;

    private void resize(int size) {
        LineSegment[] newLineSegment = new LineSegment[size];
        for (int i =0; i < numberOfLineSegments; ++i)
            newLineSegment[i] = lineSegment[i];
        lineSegment = newLineSegment;
    }

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null)
            throw new java.lang.IllegalArgumentException();

        for (int i = 0; i < points.length; ++i) {
            // any point is null
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException();
            for (int j = i + 1; j <  points.length; ++j) {
                // repeated point
                if (points[i] == points[j])
                    throw new java.lang.IllegalArgumentException();
            }
        }

        lineSegment = new LineSegment[points.length];

        double slope1, slope2, slope3;
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j <  points.length; ++j) {
                slope1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length; ++k) {
                    slope2 = points[j].slopeTo(points[k]);
                    if (slope1 == slope2) {
                        for (int l = k + 1; l < points.length; ++l) {
                            slope3 = points[k].slopeTo(points[l]);
                            if (slope2 == slope3) {
                                if (numberOfLineSegments >= lineSegment.length)
                                    resize(lineSegment.length * 2);
                                lineSegment[numberOfLineSegments++] = new LineSegment(points[i], points[l]);
                            }
                        }
                    }
                }
            }
        }

        resize(numberOfLineSegments);
    }
    public int numberOfSegments() {
        // the number of line segments
        return numberOfLineSegments;
    }
    public LineSegment[] segments() {
        // the line segments
        return lineSegment;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
