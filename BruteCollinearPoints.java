/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;
    private final List<LineSegment> lineSegmentList = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);

        Point[] copy = points.clone();
        Arrays.sort(copy);
        validateNoDuplicates(copy);

        for (int i = 0; i < copy.length - 3; i++) {
            Point p1 = copy[i];
            for (int j = i + 1; j < copy.length - 2; j++) {
                Point p2 = copy[j];
                for (int k = j + 1; k < copy.length - 1; k++) {
                    Point p3 = copy[k];
                    double p1p2Slope = p1.slopeTo(p2);
                    if (Double.compare(p1p2Slope, p2.slopeTo(p3)) == 0) {
                        for (int m = k + 1; m < copy.length; m++) {
                            Point p4 = copy[m];
                            if (Double.compare(p1p2Slope, p3.slopeTo(p4)) == 0) {
                                lineSegmentList.add(new LineSegment(p1, p4));
                            }
                        }
                    }
                }
            }
        }
        lineSegments = lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.clone();
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

    private void validate(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateNoDuplicates(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

}
