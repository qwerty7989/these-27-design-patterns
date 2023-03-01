package BridgeExample;

abstract class OldShape {
	public abstract void drawRaster();
	public abstract void drawVector();
	// ! This is against O in SOLID. This make changing the drawing method change every extends class.
}

class OldCircle extends OldShape {

	@Override
	public void drawRaster() {
		// ! Holy sheet, these are not acceptable.

	}

	@Override
	public void drawVector() {
		// ! Holy sheet, these are not acceptable.

	}


}

class OldRect extends OldShape {
	@Override
	public void drawRaster() {
		// ! Holy sheet, these are not acceptable.

	}

	@Override
	public void drawVector() {
		// ! Holy sheet, these are not acceptable.

	}
}

abstract class Shape {
	protected RenderBridge renderBridge;

	public Shape(RenderBridge renderBridge) {
		this.renderBridge = renderBridge;
	}

	public abstract void draw();
}

class Circle extends Shape {
	public float radiusX, radiusY;

	Circle(RenderBridge renderBridge) {
		super(renderBridge);
	}

	@Override
	public void draw() {
		renderBridge.renderCircle(radiusX, radiusY);
	}
}

class Rectangle extends Shape {
	public float sideX, sideY;

	Rectangle(RenderBridge renderBridge) {
		super(renderBridge);
	}

	@Override
	public void draw() {
		renderBridge.renderRect(sideX, sideY);
	}
}

interface RenderBridge {
	void renderCircle(float radiusX, float radiusY);
	void renderRect(float sideX, float sideY);
}

class RasterRendererBridge implements RenderBridge {
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

public class BridgeExample {
	public static void main(String[] args) {
		Circle circle = new Circle(new RasterRendererBridge());
		circle.radiusX = 5;
		circle.radiusY = 6;
		circle.draw();

		Rectangle rect = new Rectangle(new VectorRendererBridge());
		rect.sideX = 3;
		rect.sideY = 4;
		rect.draw();
	}
}