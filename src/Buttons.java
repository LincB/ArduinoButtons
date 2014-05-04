import java.util.ArrayList;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Buttons extends PApplet {
	
	public ArrayList<PElement> elements;
	public ArrayList<BWButton> buttons;

	public static void main(String[] args) {
		PApplet.main(new String[] {"Buttons"});
	}
	
	public void setup() {
		elements = new ArrayList<PElement>();
		buttons = new ArrayList<BWButton>();
		size(310, 350);
		background(255);
		stroke(0);
		fill(255);
		int startX = 10;
		int startY = 10;
		int currX = startX;
		int currY = startY;
		int width = 20;
		int height = 20;
		int xPad = 10;
		int yPad = 10;
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				BWButton newButton = new BWButton(this, currX, currY, width, height);
				elements.add(newButton);
				buttons.add(newButton);
				currX += width + xPad;
			}
			currY += height + yPad;
			currX = startX;
		}
		PElement sendButton = new PElement(this, 130, 310, 50, 30) {
			public void render() {
				app.stroke(0);
				app.fill(255);
				app.rect(x, y, width, height);
				int textSize = 16;
				app.textSize(textSize);
				app.fill(0);
				app.text("Send", x + (width - app.textWidth("Send"))/2, y + textSize + (height - textSize)/2);
			}
			
			public void onClick() {
				app.sendData();
			}
		};
		elements.add(sendButton);
	}
	
	public void draw() {
		for(PElement element : elements){
			element.render();
		}
	}
	
	public void mousePressed() {
		for(PElement e : elements){
			if(mouseX >= e.minX && mouseX <= e.maxX && mouseY >= e.minY && mouseY <= e.maxY){
				e.onClick();
			}
		}
	}
	
	public void sendData() {
		int bitIndex = 0;
		int byteIndex = 0;
		byte[] bytesToSend = new byte[15];
		System.out.println("New set:");
		for(int i = 0; i < 100; i++) {
			byte readBit = (byte)(buttons.get(i).isSelected ? 1 : 0);
			System.out.println(readBit);
			bytesToSend[byteIndex] = (byte)(bytesToSend[byteIndex] | readBit << bitIndex);
			bitIndex++;
			if(bitIndex == 7) {
				System.out.println("Byte: " + bytesToSend[byteIndex]);
				bitIndex = 0;
				byteIndex++;
			}
		}
		System.out.println("Byte: " + bytesToSend[14]);
	}
}
