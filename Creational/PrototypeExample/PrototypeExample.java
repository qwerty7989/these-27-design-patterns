package PrototypeExample;

interface RendererBridge {
    void renderCircle(float radiusX, float radiusY);
    void renderRect(float sideX, float sideY);
}

class RasterRendererBridge implements RendererBridge {
    @Override
    public void renderCircle(float radiusX, float radiusY) {
        System.out.println("Drawing a *raster* circle of radius X: " + radiusX + " , Y: " + radiusY);
    }

    @Override
    public void renderRect(float sideX, float sideY) {
        System.out.println("Drawing a *raster* rectangle of radius X: " + sideX + " , Y: " + sideY);
    }
}

class VectorRendererBridge implements RendererBridge {
    @Override
    public void renderCircle(float radiusX, float radiusY) {
        System.out.println("Drawing a *vector* circle of radius X: " + radiusX + " , Y: " + radiusY);
    }

    @Override
    public void renderRect(float sideX, float sideY) {
        System.out.println("Drawing a *vector* rectangle of radius X: " + sideX + " , Y: " + sideY);
    }
}

abstract class Shape {
    protected RendererBridge renderer;

    public Shape(RendererBridge renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
}

class Circle extends Shape implements Prototype {
    public float radiusX, radiusY;

    public Circle(RendererBridge renderer) {
        super(renderer);
    }

    @Override
    public void draw(){
        renderer.renderCircle(radiusX, radiusY);
    }

    public void resize(float scaleX, float scaleY) {
        radiusX *= scaleX;
        radiusY *= scaleY;
    }

    public CircleMemento resizeMemento(float scaleX, float scaleY) {
        CircleMemento old = new CircleMemento(radiusX, radiusY);
        resize(scaleX, scaleY);
        return old;
    }

    public void restoreMemento(CircleMemento old) {
        this.radiusX = old.radiusX;
        this.radiusY = old.radiusY;
    }

    @Override
    public Prototype ClonePrototype() {
        Circle newCircle = new Circle(renderer);
        newCircle.radiusX = radiusX;
        newCircle.radiusY = radiusY;
        return newCircle;
    }
}

class Rectangle extends Shape implements Cloneable {

    public Rectangle(RendererBridge renderer) {
        super(renderer);
    }

    public float sideX, sideY;

    @Override
    public void draw() {
        renderer.renderRect(sideX, sideY);
    }
}


class CircleMemento{
    public float radiusX, radiusY;
    public CircleMemento(float radiusX, float radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }
}

interface Prototype{
    Prototype ClonePrototype();
}

public class PrototypeExample {
    public static void main(String[] args){
        Object a = new Object();
        Object b = a;
        Circle circle = new Circle(new RasterRendererBridge());
        circle.radiusX = 5;
        circle.radiusY = 5;
        circle.draw();
        Circle newCircle = (Circle)circle.ClonePrototype();
        newCircle.draw();
        circle.resizeMemento(10, 20);
        newCircle.draw();
    }
}
