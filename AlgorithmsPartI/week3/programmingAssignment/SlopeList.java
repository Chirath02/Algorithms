import java.util.Comparator;


public class SlopeList {
    private final double slope;
    private final int key;
    public static final Comparator<SlopeList> BY_SLOPE = new BySlope();

    public SlopeList(double slope, int key) {
        this.slope = slope;
        this.key = key;
    }

    public static boolean less(SlopeList slope1, SlopeList slope2) {
        return (slope1.slope - slope2.slope) < 0;
    }

    public int compareTo(SlopeList that) {
        double val = this.slope - that.slope;
        if (val < 0)
            return -1;
        if (val > 0)
            return 1;
        return 0;
    }

    public String toString() {
        return slope + " " + key;
    }

    public static class BySlope implements Comparator<SlopeList> {
        public int compare(SlopeList v, SlopeList w) {
            return v.compareTo(w);
        }
    }
}
