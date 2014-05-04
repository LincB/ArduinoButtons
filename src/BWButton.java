public class BWButton extends PElement {
	
	boolean isSelected;
	
	public BWButton(Buttons app, int x, int y, int width, int height) {
		super(app, x, y, width, height);
		isSelected = false;
	}
	
	public void render() {
		if(isSelected) {
			app.stroke(0);
			app.fill(0);
		} else {
			app.stroke(0);
			app.fill(255);
		}
		app.rect(x, y, width, height);
	}
	
	public void onClick() {
		isSelected = !isSelected;
	}
	
}
