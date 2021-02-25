package com.design.princple;

/**
 * 开闭原则进化前
 */
public class OpenClosedA {
    public static void main(String[] args) {
        GraphicEditorA g = new GraphicEditorA();
        g.drawShape(new RectangleA());
        g.drawShape(new CircleA());
        g.drawShape(new TriangleA());
    }
}

class GraphicEditorA {
    /**
     * 根据不同Shape对象 根据type 绘制不同图形
     */
    public void drawShape(ShapeA s) {
        if (s.m_type == 1) {
            drawRectangle(s);
        } else if (s.m_type == 2) {
            drawCircle(s);
        } else if (s.m_type == 3) {
            drawTriangle(s);
        }
    }

    public void drawRectangle(ShapeA r) {
        System.out.println("绘制矩形");
    }

    public void drawCircle(ShapeA c) {
        System.out.println("绘制圆型");
    }

    public void drawTriangle(ShapeA t) {
        System.out.println("绘制三角形");
    }
}

class ShapeA {
    int m_type;
}

/**
 * 矩形
 */
class RectangleA extends ShapeA {
    RectangleA() {
        super.m_type = 1;
    }
}

/**
 * 原型
 */
class CircleA extends ShapeA {
    CircleA() {
        super.m_type = 2;
    }
}

/**
 * 三角形【新增的话】
 */
class TriangleA extends ShapeA {
    TriangleA() {
        super.m_type = 3;
    }
}