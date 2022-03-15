public class Main {

    public static void main(String[] args) {
        galeria1();
        galeria2();
        galeria3();
        galeria4();
    }

    private static void galeria1() {
        int corners = 4;
        int[][] coords = new int[][]{
                {0, 0},
                {3, 0},
                {3, 3},
                {0, 3}
        };

        containsCriticalPoint(corners, coords); //No
    }

    private static void galeria2() {
        int corners = 4;
        int[][] coords = new int[][]{
                {0, 0},
                {3, 0},
                {1, 1},
                {0, 3}
        };

        containsCriticalPoint(corners, coords); //Yes
    }

    private static void galeria3() {
        int corners = 5;
        int[][] coords = new int[][]{
                {0, 0},
                {100, 0},
                {50, 100},
                {30, 30},
                {50, 90}
        };

        containsCriticalPoint(corners, coords); //No
    }

    private static void galeria4() {
        int corners = 6;
        int[][] coords = new int[][]{
                {0, 0},
                {0, 10},
                {30, 10},
                {30, 0},
                {0, 30},
                {30, 30}
        };

        containsCriticalPoint(corners, coords); //No
    }

    public static void containsCriticalPoint(int corners, int[][] coords) {
        Point[] points = new Point[corners];

        for (int i = 0; i < corners; i++) {
            points[i] = new Point(coords[i][0], coords[i][1]);
        }

        boolean isConvex = true;

        //Usando -2 por causa de ArrayOutOfBounds
        //Qualquer curva com orientação diferente da primeira significa que o polígono é não convexo(?)
        if (!ccw(points[0], points[1], points[2])) {
            for (int i = 0; i < corners - 2; i++) {
                if (ccw(points[i], points[i + 1], points[i + 2])) {
                    isConvex = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < corners - 2; i++) {
                if (!ccw(points[i], points[i + 1], points[i + 2])) {
                    isConvex = false;
                    break;
                }
            }
        }

        if (!isConvex) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean ccw(Point p1, Point p2, Point p3) {
        double value = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) -
                (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());

        return value < 0.000001;
    }
}
