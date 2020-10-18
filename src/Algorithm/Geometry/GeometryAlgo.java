package Algorithm.Geometry;

public class GeometryAlgo {
    //判断p1p2 和p3p4是否相交
    public static boolean isCross(IntPoint p1, IntPoint p2, IntPoint p3, IntPoint p4) {
        int d1 = getDirection(p1, p2, p3);
        int d2 = getDirection(p1, p2, p4);
        int d3 = getDirection(p3, p4, p1);
        int d4 = getDirection(p3, p4, p2);
        if (d1 * d2 < 0 && d3 * d4 < 0) return true;
        else if (d1 == 0 && isOnStage(p1, p2, p3)) return true;
        else if (d2 == 0 && isOnStage(p1, p2, p4)) return true;
        else if (d3 == 0 && isOnStage(p3, p4, p1)) return true;
        else if (d4 == 0 && isOnStage(p3, p4, p2)) return true;
        return false;
    }

    //判断p1p3与p1p2的方向
    public static int getDirection(IntPoint p1, IntPoint p2, IntPoint p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }

    //判断p3是否在p1p2的线段上,前提是p1p3 与p1p2共线
    public static boolean isOnStage(IntPoint p1, IntPoint p2, IntPoint p3) {
        int minx;
        int maxx;
        int miny;
        int maxy;
        if (p1.x < p2.x) {
            minx = p1.x;
            maxx = p2.x;
        } else {
            minx = p2.x;
            maxx = p1.x;
        }
        if (p1.y < p2.y) {
            miny = p1.y;
            maxy = p2.y;
        } else {
            miny = p2.y;
            maxy = p1.y;
        }
        if (minx <= p3.x && p3.x <= maxx && miny <= p3.y && p3.y <= maxy) return true;
        else return false;
    }
}