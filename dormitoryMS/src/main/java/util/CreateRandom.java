package util;

import java.awt.Color;

public class CreateRandom {
	public static String getRandomCode() {
		String code = "";
		for (int i = 0; i < 4; i++) {
			char c = (char)('A' + Math.random()*('Z' - 'A' + 1));
			code += c;
		}
		return code;
	}

	public static Color getRandomColor() {
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		Color color = new Color(r, g, b);
		return color;
	}
}
