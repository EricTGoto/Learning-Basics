import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class FastCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] collinearSegments;


    public FastCollinearPoints(Point[] points) {
        numberOfSegments = 0;
        collinearSegments = new LineSegment[1];

        if (points == null || containsNull(points) || containsDuplicatePoints(points))
            throw new IllegalArgumentException("Enter a valid argument");

        int length = points.length;
        int sameSlope = 0;
        int startIndex;
        int endIndex = length - 1;

        Point[] temp = new Point[length];

        // create a copy of points to refer to
        for (int j = 0; j < length; j++) {
            temp[j] = points[j];
        }

        // for each element in the array, sort the array with respect to the element and the slope they make with other elements
        // then check if surrounding elements have the same slope, if so they are collinear
        for (int i = 0; i < length; i++) {
            Point origin = temp[i];
            Arrays.sort(points, 0, endIndex + 1, origin.slopeOrder());
            if (numberOfSegments == collinearSegments.length) expandArray();
            for (int k = endIndex; k > 1; k--) {
                if (points[endIndex].slopeTo(points[k - 1]) == points[endIndex].slopeTo(points[k - 2])) {
                    sameSlope++;
                    //System.out.println(sameSlope + " " + origin.slopeTo(points[k]) + " " + origin.slopeTo(points[k - 1]));
                } else {
                    if (sameSlope >= 2) {
                        startIndex = endIndex - sameSlope - 1;
                        int numCollinearPoints = sameSlope + 2;
                        Point[] collinearPoints = new Point[numCollinearPoints];
                        //System.out.println(sameSlope);
                        int index = 0;
                        for (int z = startIndex; z <= endIndex; z++) {
                            collinearPoints[index++] = points[z];
                            //System.out.println(z);
                        }

                        Arrays.sort(collinearPoints);

                        collinearSegments[numberOfSegments] = new LineSegment(collinearPoints[0], collinearPoints[numCollinearPoints - 1]);
                        numberOfSegments++;
                        endIndex = startIndex;
                        break;
                    }
                    sameSlope = 0;
                }

            }
            sameSlope = 0;
        }
        arrayShrink();
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return collinearSegments;
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
            for (int i = k + 1; i < length - 1; i++) {
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
            //System.out.println(fcp.segments()[k]);
        }


        /*
        Arrays.sort(points, points[2].slopeOrder());
        for (int k = 0; k < points.length; k++) {
            System.out.println(points[k]);
        }
*/
    }
}
