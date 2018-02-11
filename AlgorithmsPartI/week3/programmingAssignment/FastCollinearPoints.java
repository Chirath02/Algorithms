import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;


public class FastCollinearPoints {
    private LineSegment[] lineSegment;
    private int numberOfLineSegments = 0;
    private SlopeList[] slopeList;

    private void resize(int size) {
        LineSegment[] newLineSegment = new LineSegment[size];
        for (int i =0; i < numberOfLineSegments; ++i)
        newLineSegment[i] = lineSegment[i];
        lineSegment = newLineSegment;
    }

    private void addLineSegment(Point p, Point q) {
        if (numberOfLineSegments >= lineSegment.length)
        resize(lineSegment.length * 2);
        lineSegment[numberOfLineSegments++] = new LineSegment(p, q);
        StdOut.println(p + " " + q);
    }

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        lineSegment = new LineSegment[points.length];
        slopeList = new SlopeList[points.length];

        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j)
                slopeList[j] = new SlopeList(points[i].slopeTo(points[j]), j);

            Arrays.sort(slopeList, SlopeList.BY_SLOPE);

            int p = 0, q = 0;
            SlopeList temp = slopeList[0];
            for (int j = 1; j < points.length; ++j) {
                if (temp.compareTo(slopeList[j]) == 0)
                    q++;
                else {
                    temp = slopeList[j];
                    if (q - p >= 3)
                    addLineSegment(points[p], points[q]);
                    p = q = j;
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return numberOfLineSegments;
    }

    public LineSegment[] segments() {
        // the line segments
        StdOut.println(lineSegment);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);

        for (LineSegment segment : collinear.segments()) {
            // StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
