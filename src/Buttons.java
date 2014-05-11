import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Buttons extends PApplet {

	public ArrayList<PElement> elements;
	public ArrayList<BWButton> buttons;
	public String portName;
	public SerialComm connect;
	PText portText;
	TextButton sendButton;

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
		sendButton = new TextButton(this, 130, 310, 50, 30, "Send", 16, 200, 200, 200) {
			public void onClick() {
				app.sendData();
			}
		};
		elements.add(sendButton);
		Thread detect = new Thread() {
			public void run() {
				getPort();
			}
		};
		detect.start();
		textSize(10);
		portText = new PText(this, 200, 330, "Searching...", 10);
		elements.add(portText);
	}

	public void draw() {
		background(255);
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
		if(connect == null) {
			System.out.println("No connection to transmit");
		} else {
			connect.writeBytes(bytesToSend);
		}
	}

	public void getPort() {
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			String currPortName = currPortId.getName();
			SerialComm test = new SerialComm();
			test.initialize(currPortName);
			try {Thread.sleep(2000);} catch (InterruptedException ie) {}
			if(test.found) {
				System.out.println("Found: " + currPortName);
				portText.setText(currPortName, 12);
				sendButton.setColor(0, 0, 0);
				connect = test;
				break;
			} else {
				System.out.println("Not found: " + currPortName);
				test.close();
			}
		}
		if(connect == null) {
			portText.setText("Not found", 12);
		}
		System.out.println("Done");
	}
}
