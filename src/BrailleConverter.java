import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BrailleConverter {

	static int[] startXs = new int[] {1, 4, 7, 1, 4, 7};
	static int[] startYs = new int[] {1, 1, 1, 6, 6, 6};
	static Map<String, boolean[]> charMap;
	static ArrayList<BWButton> buttons;

	public static void init(ArrayList<BWButton> list) {
		buttons = list;
		charMap = new HashMap<String, boolean[]>();
		charMap.put("a", new boolean[] {true, false, false, false, false, false});
		charMap.put("b", new boolean[] {true, false, true, false, false, false});
		charMap.put("c", new boolean[] {true, true, false, false, false, false});
		charMap.put("d", new boolean[] {true, true, false, true, false, false});
		charMap.put("e", new boolean[] {true, false, false, true, false, false});
		charMap.put("f", new boolean[] {true, true, true, false, false, false});
		charMap.put("g", new boolean[] {true, true, true, true, false, false});
		charMap.put("h", new boolean[] {true, false, true, true, false, false});
		charMap.put("i", new boolean[] {false, true, true, false, false, false});
		charMap.put("j", new boolean[] {false, true, true, true, false, false});
		charMap.put("k", new boolean[] {true, false, false, false, true, false});
		charMap.put("l", new boolean[] {true, false, true, false, true, false});
		charMap.put("m", new boolean[] {true, true, false, false, true, false});
		charMap.put("n", new boolean[] {true, true, false, true, true, false});
		charMap.put("o", new boolean[] {true, false, false, true, true, false});
		charMap.put("p", new boolean[] {true, true, true, false, true, false});
		charMap.put("q", new boolean[] {true, true, true, true, true, false});
		charMap.put("r", new boolean[] {true, false, true, true, true, false});
		charMap.put("s", new boolean[] {false, true, true, false, true, false});
		charMap.put("t", new boolean[] {false, true, true, true, true, false});
		charMap.put("u", new boolean[] {true, false, false, false, true, true});
		charMap.put("v", new boolean[] {true, false, true, false, true, true});
		charMap.put("w", new boolean[] {false, true, true, true, false, true});
		charMap.put("x", new boolean[] {true, true, false, false, true, true});
		charMap.put("y", new boolean[] {true, true, false, true, true, true});
		charMap.put("z", new boolean[] {true, false, false, true, true, true});
	}

	public static void setButtons(String data) {
		char[] toWrite = new char[6];
		char[] in = data.toCharArray();
		for(int i = 0; i < data.length(); i++) {
			toWrite[i] = in[i];
		}
		for (int i = 0; i < 100; i++) {
			buttons.get(i).isSelected = false;
		}
		for (int i = 0; i < 6; i++) {
			if(toWrite[i] != '\0') {
				System.out.println("Accessing: " + Character.toString(toWrite[i]));
				boolean[] charCode = charMap.get(Character.toString(toWrite[i]));
				System.out.println(charCode);
				for (int j = 0; j < 6; j++) {
					setButton(startXs[i]+(j%2), (int)(startYs[i]+Math.floor(j/2)), charCode[j]);
				}
			}
		}
	}

	static void setButton(int x, int y, boolean setting) {
		buttons.get(x + 10*y).isSelected = setting;
	}
}
