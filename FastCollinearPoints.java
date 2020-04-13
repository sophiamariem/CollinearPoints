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

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;
    private final List<LineSegment> lineSegmentList = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        validate(points);

        Point[] copy = points.clone();
        Arrays.sort(copy);
        validateNoDuplicates(copy);

        for (Point point : points) {
            Arrays.sort(copy, point.slopeOrder());
            double slope = point.slopeTo(copy[0]);

            int count = 1;
            for (int idx = 1; idx < points.length; idx++) {
                if (point.slopeTo(copy[idx]) == slope) {
                    count++;
                }
                else {
                    addSegment(copy, point, count, idx);
                    slope = point.slopeTo(copy[idx]);
                    count = 1;
                }
            }
            addSegment(copy, point, count, points.length);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
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

    private void addSegment(Point[] copy, Point point, int count, int idx) {
        if (count >= 3) {
            Arrays.sort(copy, idx - count, idx);
            if (point.compareTo(copy[idx - count]) < 0) {
                lineSegmentList.add(new LineSegment(point, copy[idx - 1]));
            }
        }
    }

}
