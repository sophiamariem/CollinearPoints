# Collinear Points

Write a program to recognize line patterns in a given set of points.

Computer vision involves analyzing patterns in visual images and reconstructing the real-world objects that produced them. The process is often broken up into two phases: feature detection and pattern recognition. Feature detection involves selecting important features of the image; pattern recognition involves discovering patterns in the features. We will investigate a particularly clean pattern recognition problem involving points and line segments. This kind of pattern recognition arises in many other applications such as statistical data analysis.

## The problem

Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.

![image](https://coursera.cs.princeton.edu/algs4/assignments/collinear/lines2.png)

## Point data type

Create an immutable data type Point that represents a point in the plane by implementing the following API:


To get started, use the data type [Point.java](https://coursera.cs.princeton.edu/algs4/assignments/collinear/files/Point.java), which implements the constructor and the draw(), drawTo(), and toString() methods. Your job is to add the following components.
- The compareTo() method should compare points by their y-coordinates, breaking ties by their x-coordinates. Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
- The slopeTo() method should return the slope between the invoking point (x0, y0) and the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as positive zero; treat the slope of a vertical line segment as positive infinity; treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
- The slopeOrder() method should return a comparator that compares its two argument points by the slopes they make with the invoking point (x0, y0). Formally, the point (x1, y1) is less than the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
- Do not override the equals() or hashCode() methods.

_Corner cases_. To avoid potential complications with integer overflow or floating-point precision, you may assume that the constructor arguments x and y are each between 0 and 32,767.

## Line segment data type
To represent line segments in the plane, use the data type [LineSegment.java](https://coursera.cs.princeton.edu/algs4/assignments/collinear/files/LineSegment.java)

## Brute force
Write a program BruteCollinearPoints.java that examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments. To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal.

The method segments() should include each line segment containing 4 points exactly once. If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.

Corner cases. Throw an IllegalArgumentException if the argument to the constructor is null, if any point in the array is null, or if the argument to the constructor contains a repeated point.

Performance requirement. The order of growth of the running time of your program should be n4 in the worst case and it should use space proportional to n plus the number of line segments returned.

## A faster, sorting-based solution
Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

- Think of p as the origin.
- For each other point q, determine the slope it makes with p.
- Sort the points according to the slopes they makes with p.
- Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
Applying this method for each of the n points in turn yields an efficient algorithm to the problem. The algorithm solves the problem because points that have equal slopes with respect to p are collinear, and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.

Write a program FastCollinearPoints.java that implements this algorithm.
The method segments() should include each maximal line segment containing 4 (or more) points exactly once. For example, if 5 points appear on a line segment in the order p→q→r→s→t, then do not include the subsegments p→s or q→t.

_Corner cases_. Throw an IllegalArgumentException if the argument to the constructor is null, if any point in the array is null, or if the argument to the constructor contains a repeated point.

_Performance requirement_. The order of growth of the running time of your program should be n2 log n in the worst case and it should use space proportional to n plus the number of line segments returned. FastCollinearPoints should work properly even if the input has 5 or more collinear points.

***

Full specification found [here](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php)

***