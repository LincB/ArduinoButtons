public abstract class PElement {
	
	int minX, minY, maxX, maxY;
	Buttons app;
	int x, y, width, height;
	
	public PElement(Buttons app, int x, int y, int width, int height) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		minX = x;
		minY = y;
		maxX = x + width;
		maxY = y + height;
	}
	
	public abstract void render();
	public void onClick() {}
	public void writeChar(char in){}
}
