import java.awt.Point;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int size = scanner.nextInt();

            if (size <= 0) {
                break;
            }

            Point[] polygon = new Point[size];

            for (int i = 0; i < size; i++) {
                polygon[i] = new Point(scanner.nextInt(), scanner.nextInt());
            }

            if (containsCriticalPoint(polygon)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    //Qualquer curva diferente da primeira, excluindo 3 pontos colineares,
    //significa que não é convexo
    public static boolean containsCriticalPoint(Point[] polygon) {
        int firstTurn = 0;

        /*
         * p1 sempre é relacionado a iteração atual
         *
         * p2 é o próximo ponto se p1 não for o último ponto, do contrário, é o primeiro
         *
         * p3 é o próximo ponto se p1 não for o último ou penúltimo ponto. Se p1 for o penúltimo, então p3 é o primeiro
         * ponto do polígono. Se p1 for o último, então p3 é o segundo ponto do polígono.
         */
        for (int i = 0; i < polygon.length; i++) {
            Point p1, p2, p3;

            p1 = polygon[i];
            if (i + 1 == polygon.length) {
                p2 = polygon[0];
            } else {
                p2 = polygon[i + 1];
            }

            if (i + 2 >= polygon.length) {
                p3 = polygon[i + 2 - polygon.length];
            } else {
                p3 = polygon[i + 2];
            }

            int direction = crossProduct(p1, p2, p3);

            if (direction == 0) {
                continue; //Ignora 3 pontos colineares
            }

            if (i > 0) {
                if (direction != firstTurn) {
                    return true;
                }
            } else {
                firstTurn = direction; //Primeira curva
            }
        }

        return false;
    }

    /**
     * @param p1 p1
     * @param p2 p2
     * @param p3 p3
     * @return 0 -> colinear,
     * 1 -> esquerda,
     * -1 -> direita
     */
    public static int crossProduct(Point p1, Point p2, Point p3) {
        double value = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) -
                (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());

        if (value < 0) {
            return -1;
        } else if (value > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}