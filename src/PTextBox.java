public class PTextBox extends PElement {

	String text = "";
	int textSize;
	int maxChars;

	public PTextBox(Buttons app, int x, int y, int width, int height, int textSize, int maxChars) {
		super(app, x, y, width, height);
		this.textSize = textSize;
		this.maxChars = maxChars;
	}

	public void render() {
		app.stroke(0);
		app.fill(255);
		app.rect(x, y, width, height);
		app.textSize(textSize);
		app.fill(0);
		app.text(text, x + (width - app.textWidth(text))/2, y + textSize + (height - textSize)/2);
	}

	public void onClick() {
		app.selected = this;
	}

	public void writeChar(char in) {
		if(text.length() < maxChars) {
			text += in;
		}
	}

	public void backspace() {
		if(text.length() != 0) {
			text = text.substring(0, text.length() - 1);
		}
	}
}
