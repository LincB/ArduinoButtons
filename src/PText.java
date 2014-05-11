public class PText extends PElement {
	
	String text;
	int textSize;
	
	public PText(Buttons app, int x, int y, String text, int textSize) {
		super(app, x, y, (int)app.textWidth(text), textSize);
		this.text = text;
		this.textSize = textSize;
	}
	
	public void render() {
		app.textSize(textSize);
		app.fill(0);
		app.text(text, x, y + textSize/2);
	}
	
	public void setText(String text, int textSize) {
		this.text = text;
		this.textSize = textSize;
	}
	
}
