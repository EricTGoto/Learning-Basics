import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class FastCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] collinearSegments;


    public FastCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException("Argument is null");
        if (containsNull(points)) throw new IllegalArgumentException("Argument can't contain null points");
        if (containsDuplicatePoints(points)) throw new IllegalArgumentException("Argument contains duplicate points");

        numberOfSegments = 0;
        collinearSegments = new LineSegment[1];

        int length = points.length;
        int sameSlope = 0;
        int startIndex = 0;
        int pointsIndex = 0;
        Point[] copyArray = new Point[length];

        // create a copy of points to refer to
        for (int j = 0; j < length; j++) {
            copyArray[j] = points[j];
        }

        // for each element in the array, sort the array with respect to the element and the slope they make with other elements
        // then go through the sorted array and check if there are blocks of 4 ore more elements with the same slope

        Arrays.sort(copyArray, points[0].slopeOrder());
        for (int i = 0; i < length; i++) {
            Point origin = points[i];

            double slope = origin.slopeTo(copyArray[pointsIndex]);
            for (int k = pointsIndex; k < length - 1; k++) {
                if (numberOfSegments == collinearSegments.length) expandArray();

                double nextSlope = origin.slopeTo(copyArray[k + 1]);

                if (slope == nextSlope) {
                    sameSlope++;
                }

                if (sameSlope > 1 && slope != nextSlope ||
                        (k == length - 2 && sameSlope > 1)) {

                    if (k == length - 2 && slope == nextSlope) startIndex = k - sameSlope + 1;
                    else startIndex = k - sameSlope;


                    int numCollinearPoints = sameSlope + 2;

                    Point[] collinearPoints = new Point[numCollinearPoints];

                    for (int z = 0; z < numCollinearPoints - 1; z++) {
                        collinearPoints[z] = copyArray[z + startIndex];
                    }
                    collinearPoints[numCollinearPoints - 1] = origin;

                    // sort by natural order
                    Arrays.sort(collinearPoints);

                    if (origin == collinearPoints[0]) {
                        collinearSegments[numberOfSegments] = new LineSegment(collinearPoints[0], collinearPoints[numCollinearPoints - 1]);
                        numberOfSegments++;
                    }

                    slope = nextSlope;
                    sameSlope = 0;
                } else if (slope != nextSlope) {
                    slope = nextSlope;
                    sameSlope = 0;
                }
            }
            // if we couldn't find anything then we keep the point and re-sort points with a different point
            if (i + 1 != length) {
                Arrays.sort(copyArray, pointsIndex, length, points[i + 1].slopeOrder());
            }
            sameSlope = 0;
        }
        arrayShrink();
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] temp = new LineSegment[numberOfSegments];
        for (int k = 0; k < numberOfSegments; k++) {
            temp[k] = collinearSegments[k];
        }
        return temp;
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
        for (int k = 0; k < length; k++) {
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
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        //StdDraw.enableDoubleBuffering();
        //StdDraw.setXscale(0, 32768);
        //StdDraw.setYscale(0, 32768);
        //for (Point p : points) {
        //    p.draw();
        //}
        //StdDraw.show();

        FastCollinearPoints fcp = new FastCollinearPoints(points);
        int length = fcp.numberOfSegments;
        for (int k = 0; k < fcp.numberOfSegments; k++) {
            System.out.println(fcp.segments()[k]);
        }
        System.out.println(fcp.numberOfSegments);

    }
}
