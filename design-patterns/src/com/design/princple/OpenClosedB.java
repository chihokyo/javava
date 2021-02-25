package com.design.princple;

/**
 * 开闭原则进化后
 */
public class OpenClosedB {
    public static void main(String[] args) {

        GraphicEditorB g = new GraphicEditorB();
        g.drawShape(new RectangleB());
        g.drawShape(new CircleB());
        g.drawShape(new TriangleB());
        g.drawShape(new OtherGraphic());
    }
}

class GraphicEditorB {

    public void drawShape(ShapeB s) {
        s.draw();
    }
}

abstract class ShapeB {
    int m_type;

    public abstract void draw();
}

class RectangleB extends ShapeB {
    RectangleB() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}

class CircleB extends ShapeB {
    CircleB() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}

class TriangleB extends ShapeB {
    TriangleB() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("绘制三角形");
    }
}

// 这个时候如果新增其他图像
class OtherGraphic extends ShapeB {
    OtherGraphic() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        System.out.println("Other");
    }
}