import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        int[][] pointCoords = new int[][]{
                {3, 9},
                {5, 2},
                {-1, -4},
                {8, 3},
                {-6, 90},
                {23, 3},
                {4, -11}
        };

        Point[] points = new Point[pointCoords.length];

        for (int i = 0; i < pointCoords.length; i++) {
            points[i] = new Point(pointCoords[i][0], pointCoords[i][1]);
        }

        // find the convex hull
        List<Point> convexHull = grahamScan(points);

        for (Point p : convexHull) {
            System.out.println(p);
        }


       /* galeria1();

        System.out.println("---");

        galeria2();*/
    }

    /*private static void galeria1() {
        int corners = 4;
        int[][] coords = new int[][]{
                {0, 0},
                {3, 0},
                {3, 3},
                {0, 3}
        };

        containsCriticalPoint(corners, coords);
    }

    private static void galeria2() {
        int corners = 4;
        int[][] coords = new int[][]{
                {0, 0},
                {3, 0},
                {1, 1},
                {0, 3}
        };

        containsCriticalPoint(corners, coords);
    }*/


    public static List<Point> grahamScan(Point[] points) {
        Arrays.sort(points, Comparator.comparing(Point::getX));
        ArrayList<Point> lSup = new ArrayList<>();

        lSup.add(points[0]);
        lSup.add(points[1]);

        for (int i = 2; i < points.length; i++) {
            do {
                lSup.add(points[i]);
            } while (lSup.size() > 2 && !ccw(lSup.get(lSup.size() - 3), lSup.get(lSup.size() - 2), lSup.get(lSup.size() - 1)));

            lSup.remove(lSup.size() - 2);

        }

        ArrayList<Point> lInf = new ArrayList<>();
        lInf.add(points[points.length - 1]);
        lInf.add(points[points.length - 2]);

        for (int i = points.length - 3; i >= 0; i--) {
            do {
                lInf.add(points[i]);
            } while (lInf.size() > 2 && !ccw(lInf.get(lInf.size() - 3), lInf.get(lInf.size() - 2), lInf.get(lInf.size() - 1)));

            lInf.remove(lInf.size() - 2);
        }

        lSup.remove(0);
        lSup.remove(lSup.size() - 1);

        lInf.remove(0);
        lInf.remove(lInf.size() - 1);

        return Stream.concat(lSup.stream(), lInf.stream()).collect(Collectors.toList());
    }

    public static boolean ccw(Point p1, Point p2, Point p3) {
        double value = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) -
                (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());

        return value < 0.000001;
    }
}
