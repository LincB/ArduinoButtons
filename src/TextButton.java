public class TextButton extends PElement {
	
	String text;
	int textSize, r, g, b;
	
	public TextButton(Buttons app, int x, int y, int width, int height, String text, int textSize, int r, int g, int b) {
		super(app, x, y, width, height);
		this.text = text;
		this.textSize = textSize;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void render() {
		app.stroke(r, g, b);
		app.fill(255);
		app.rect(x, y, width, height);
		app.textSize(textSize);
		app.fill(r, g, b);
		app.text(text, x + (width - app.textWidth(text))/2, y + textSize + (height - textSize)/2);
	}
	
	public void setColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setText(String text, int textSize) {
		this.text = text;
		this.textSize = textSize;
	}
}
