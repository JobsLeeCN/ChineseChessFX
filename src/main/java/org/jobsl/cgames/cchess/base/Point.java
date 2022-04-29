package org.jobsl.cgames.cchess.base;

/**
 * 坐标对象实体
 *
 * @author JobsLee
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
