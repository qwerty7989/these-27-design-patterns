package SingletonExample;

import java.util.Stack;

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

class Circle extends Shape{

    public Circle(RendererBridge renderer) {
        super(renderer);

    }

    public float radiusX, radiusY;

    @Override
    public void draw(){
        renderer.renderCircle(radiusX, radiusY);
    }

    public void resize(float scalex, float scaley){
        radiusX *= scalex;
        radiusY *= scaley;
    }

    public CircleMemento resizeMemento(float scalex, float scaley){
        CircleMemento old = new CircleMemento(radiusX, radiusY);
        resize(scalex, scaley);
        return old;
    }

    public void restoreMemento(CircleMemento old){
        this.radiusX = old.radiusX;
        this.radiusY = old.radiusY;
    }
}

class CircleMemento{
    public float radiusX, radiusY;
    public CircleMemento(float radiusX, float radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }
}

abstract class Shape {

    protected RendererBridge renderer;

    public Shape(RendererBridge renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();

}

interface Command{
    void call();

    void undo();
}

class Singleton {
    //static value stay same when the last deploy
    private static Singleton INSTANCE;
    public Circle circle;
    Stack<Command> command = new Stack<>();
    public static Singleton getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }
}

public class SingletonExample {
    public static void main(String[] args){
        Singleton.getInstance().circle = new Circle(new RasterRendererBridge());
    }
}
