package MementoExample;

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

abstract class Shape {
    protected RendererBridge renderer;

    public Shape(RendererBridge renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
}

class Circle extends Shape {
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
}

class Rectangle extends Shape {

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

public class MementoExample {
    public static void main(String[] args) {
        Circle circle = new Circle(new RasterRendererBridge());
        circle.radiusX = 3;
        circle.radiusY = 3;
        circle.draw();
        Stack<CircleMemento> mementos = new Stack<>();
        mementos.push(circle.resizeMemento(10, 20));
        circle.draw();
        mementos.push(circle.resizeMemento(30, 40));
        circle.draw();
        circle.restoreMemento(mementos.pop());
        circle.draw();
        circle.restoreMemento(mementos.pop());
        circle.draw();
    }
}
