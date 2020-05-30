import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] collinearSegments;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null || containsNull(points) || containsDuplicatePoints(points))
            throw new IllegalArgumentException("Enter a valid argument");

        numberOfSegments = 0;
        collinearSegments = new LineSegment[1];

        int length = points.length;

        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    for (int l = k + 1; l < length; l++) {
                        double slope1 = points[l].slopeTo(points[k]);
                        double slope2 = points[l].slopeTo(points[j]);
                        double slope3 = points[l].slopeTo(points[i]);

                        if (numberOfSegments == collinearSegments.length) expandArray();

                        if (slope1 == slope2 && slope1 == slope3) {
                            Point smallestPoint = smallestPoint(points[i], points[j], points[k], points[l]);
                            Point largestPoint = largestPoint(points[i], points[j], points[k], points[l]);

                            collinearSegments[numberOfSegments] = new LineSegment(smallestPoint, largestPoint);
                            numberOfSegments++;
                        }
                    }
                }
            }
        }
        arrayShrink();
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        int length = collinearSegments.length;
        LineSegment[] temp = new LineSegment[collinearSegments.length];
        for (int k = 0; k < length; k++)
            temp[k] = collinearSegments[k];
        return temp;
    }

    private Point smallestPoint(Point a, Point b, Point c, Point d) {
        Point[] array = {a, b, c, d};
        Arrays.sort(array);
        return array[0];
    }

    private Point largestPoint(Point a, Point b, Point c, Point d) {
        Point[] array = {a, b, c, d};
        Arrays.sort(array);
        return array[3];
    }

    private void expandArray() {
        int newLength = collinearSegments.length * 2;
        LineSegment[] temp = new LineSegment[newLength];
        for (int k = 0; k < newLength / 2; k++) {
            temp[k] = collinearSegments[k];
        }
        collinearSegments = temp;
    }

    private boolean containsDuplicatePoints(Point[] points) {
        int length = points.length;

        for (int k = 0; k < length - 1; k++) {
            for (int i = k + 1; i < length; i++) {
                if (points[k].compareTo(points[i]) == 0) return true;
            }
        }
        return false;
    }

    private boolean containsNull(Point[] points) {
        int length = points.length;

        for (int k = 0; k < length; k++) {
            if (points[k] == null) return true;
        }
        return false;
    }

    private void arrayShrink() {
        LineSegment[] temp = new LineSegment[numberOfSegments];
        for (int k = 0; k < numberOfSegments; k++) {
            temp[k] = collinearSegments[k];
        }
        collinearSegments = temp;
    }

    public static void main(String[] args) {
        In i = new In(args[0]);
        int numPoints = i.readInt();
        Point[] points = new Point[numPoints];
        int x = 0;
        while (!i.isEmpty()) {
            points[x] = new Point(i.readInt(), i.readInt());
            x++;
        }
/*
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

*/
        BruteCollinearPoints b = new BruteCollinearPoints(points);

        for (int k = 0; k < b.numberOfSegments; k++) {
            System.out.println(b.collinearSegments[k]);
        }
        System.out.println(b.collinearSegments.length);
    }
}
