package utils;

import com.badlogic.gdx.graphics.Color;

public class HexColor extends Color {
	public HexColor(String hexval) {
		Color c = HexColor.colorFromHexString(hexval);
		r = c.r;
		g = c.g;
		b = c.b;
		a = c.a;
	}

	// masks out color values with bitwise and operator
	public static Color colorFromHex(long hex) {
		float r,g,b,a;
		if (hex < 0xFF000000L){
			// 3 color values, no alpha = 6 digits
			r = (hex & 0xFF0000L) >> 16;
			g = (hex & 0x00FF00L) >> 8;
			b = (hex & 0x0000FFL);
			a = 0xFF; // default full opacity
		}
		else {
			// 3 color values + alpha = 8 digits
			r = (hex & 0xFF000000L) >> 24;
			g = (hex & 0x00FF0000L) >> 16;
			b = (hex & 0x0000FF00L) >> 8;
			a = (hex & 0x000000FFL);
		}
		
		return new Color(r / 255f, g / 255f, b / 255f, a / 255f);
	}

	public static Color colorFromHexString(String s) {
		if (s.startsWith("0x"))
			s = s.substring(2);
		if (s.startsWith("#"))
			s = s.substring(1);

		if (! (s.length() == 8 || s.length() == 6)) // RRGGBBAA or RRGGBB 
			throw new IllegalArgumentException(
					"String must have the form RRGGBBAA or RRGGBB");

		return colorFromHex(Long.parseLong(s, 16));
	}

}
