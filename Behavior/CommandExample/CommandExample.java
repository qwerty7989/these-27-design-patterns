package CommandExample;

import java.util.Stack;

abstract class Shape {
	protected RendererBridge rendererBridge;

	protected Shape(RendererBridge rendererBridge) {
		this.rendererBridge = rendererBridge;
	}

	public abstract void draw();
	public abstract void resize(float scaleX, float scaleY);
}

class Circle extends Shape {
	public float radiusX, radiusY;

	Circle(RendererBridge rendererBridge) {
		super(rendererBridge);
	}

	@Override
	public void draw() {
		rendererBridge.renderCircle(radiusX, radiusY);
	}

	@Override
	public void resize(float scaleX, float scaleY) {
		radiusX *= scaleX;
		radiusY *= scaleY;
	}
}

class Rectangle extends Shape {
	public float sideX, sideY;

	Rectangle(RendererBridge rendererBridge) {
		super(rendererBridge);
	}

	@Override
	public void draw() {
		rendererBridge.renderRect(sideX, sideY);
	}

	@Override
	public void resize(float scaleX, float scaleY) {
		sideX *= scaleX;
		sideY *= scaleY;
	}
}

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

class VectorRendererBridge implements RenderBridge {
	@Override
	public void renderCircle(float radiusX, float radiusY) {
		System.out.println("Drawing a *vector* circle of radius X: " + radiusX + " , Y: " + radiusY);
	}

	@Override
	public void renderRect(float sideX, float sideY) {
		System.out.println("Drawing a *vector* rectangle of radius X: " + sideX + " , Y: " + sideY);

	}
}

interface Command {
	void call();
	void undo();
}

class FillColorCommand implements Command {

	@Override
	public void call() {
		// ! Can fill some color for you!

	}

	@Override
	public void undo() {
		// ! Can fill some color for you!

	}

}

class ResizeShapeCommand implements Command {
	private Shape shape;
	private float scaleX, scaleY;

	public ResizeShapeCommand(Shape shape, float scaleX, float scaleY) {
		this.shape = shape;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public void call() {
		shape.resize(scaleX, scaleY);
	}

	@Override
	public void undo() {
		shape.resize(1/scaleX, 1/scaleY);
	}
}

public class CommandExample {
	public static void main(String[] args) {
		Circle circle = new Circle(new RasterRendererBridge());
		circle.radiusX = 5;
		circle.radiusY = 6;
		circle.draw();

		Rectangle rect = new Rectangle(new VectorRendererBridge());
		rect.sideX = 3;
		rect.sideY = 4;
		rect.draw();

		rect.resize(2, 5);
		rect.draw();

		ResizeShapeCommand resizeShapeCommand = new ResizeShapeCommand(circle, 4, 4);
		resizeShapeCommand.call();
		circle.draw();
		resizeShapeCommand.undo();

		Stack<Command> commandStack = new Stack<>();
		commandStack.push(new ResizeShapeCommand(rect, 4, 5));
		commandStack.push(new ResizeShapeCommand(circle, 10, 4));
		commandStack.push(new ResizeShapeCommand(circle, 20, 3));
		commandStack.push(new ResizeShapeCommand(rect, 5, 4));
		for (Command cmd : commandStack) {
			System.out.println("======");
			cmd.call();
			circle.draw();
			rect.draw();
		}

		System.out.println("==UNDO==");
		while (!commandStack.isEmpty()) {
			Command cmd = commandStack.pop();
			System.out.println("======");
			cmd.undo();
			circle.draw();
			rect.draw();
		}
	}
}