package vjezba1;

public class Color {
	private int Red;
	private int Blue;
	private int Green;

	public Color() {}
	public int getRed() {
		return Red;
	}
	public int getGreen() {
		return Green;
	}
	public int getBlue() {
		return Blue;
	}
	public static Color decode(String hexColor) {
		Color c = new Color();
		int hex = Integer.decode(hexColor);
		c.Red = (hex & 0xFF0000) >> 16;
		c.Green = (hex & 0xFF00) >> 8;
		c.Blue = hex & 0xFF;
		return c;
	}

	public static float[] RGBtoHSB(int r,int g, int b,float[] hsbCode) {
		float hue, saturation, brightness;
		if (hsbCode == null) {
			hsbCode = new float[3];
		}
		float cmin = Math.min(r, Math.min(g, b));
		float cmax = Math.max(r, Math.max(g, b));
		
		brightness = cmax / 255.0f;
		if (cmax != 0)
		   saturation = (cmax - cmin) / cmax;
		else
		  saturation = 0;
		if (saturation == 0)
		  hue = 0;
		else {
			float redc = (cmax - r) / (cmax - cmin);
			float greenc = (cmax - g) / (cmax - cmin);
			float bluec = (cmax - b) / (cmax - cmin);
			if (r == cmax)
				hue = bluec - greenc;
			else if (g == cmax)
				hue = 2.0f + redc - bluec;
			else
				hue = 4.0f + greenc - redc;
			hue = hue / 6.0f;
			if (hue < 0)
				hue = hue + 1.0f;
		}
		  hsbCode[0] = hue;
		  hsbCode[1] = saturation;
		  hsbCode[2] = brightness;
		  return hsbCode;
	}

	public int getRGB() {
		int rgb = (Red << 16) + (Green << 8) + (Blue << 0);
		return rgb;

	}

	public float[] RGBtoHSL()
	{
		float r = this.Red/255f;
		float g = this.Green/255f;
		float b = this.Blue/255f;

		float cmin = Math.min(r, Math.min(g, b));
		float cmax = Math.max(r, Math.max(g, b));

		float h = 0;

		if (cmax == cmin)
			h = 0;
		else if (cmax == r)
			h = ((60 * (g - b) / (cmax - cmin)) + 360) % 360;
		else if (cmax == g)
			h = (60 * (b - r) / (cmax - cmin)) + 120;
		else if (cmax == b)
			h = (60 * (r - g) / (cmax - cmin)) + 240;

		float l = (cmax + cmin) / 2;
		float s = 0;
		if (cmax == cmin)
			s = 0;
		else if (l <= .5f)
			s = (cmax - cmin) / (cmax + cmin);
		else
			s = (cmax - cmin) / (2 - cmax - cmin);

		return new float[] {h, s * 100, l * 100};
	}

	public float[] RGBtoCMYK() {
		float percentageR = (float)(this.Red / 255.0 * 100);
        float percentageG = (float)(this.Green / 255.0 * 100);
        float percentageB = (float)(this.Blue / 255.0 * 100);

        float k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);

        if (k == 100) {
            return new float[]{ 0, 0, 0, 100 };
        }

        float c = (100 - percentageR - k) / (100 - k) ;
        float m = (100 - percentageG - k) / (100 - k) ;
        float y = (100 - percentageB - k) / (100 - k) ;

        return new float[]{ c, m, y, (int)k };
	}

}
