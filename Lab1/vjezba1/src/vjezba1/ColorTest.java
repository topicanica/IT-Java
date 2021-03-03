package vjezba1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColorTest {

	@Test
	void testGetRed() {
		String hexColor="#7ad23f";
		Color c= Color.decode(hexColor);
		assertEquals(122,c.getRed());
	}

	@Test
	void testGetGreen() {
		String hexColor="#7ad23f";
		Color c= Color.decode(hexColor);
		assertEquals(210,c.getGreen());
	}

	@Test
	void testGetBlue() {
		String hexColor="#7ad23f";
		Color c= Color.decode(hexColor);
		assertEquals(63,c.getBlue());
	}

	@Test
	void testDecode() {
		String hexColor="#7ad23f";
		Color c = Color.decode(hexColor);
		assertEquals(122,c.getRed());
		assertEquals(210,c.getGreen());
		assertEquals(63,c.getBlue());

	}

	@Test
	void testRGBtoHSB() {
		String hexColor="#7ad23f";
		Color c = Color.decode(hexColor);
		float[] hsbCode = new float[3];
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
		hsbCode[0]*=360;
		hsbCode[1]*=100;
		hsbCode[2]*=100;
		float[] values = {(float) 95.918365,(float) 70.0,(float) 82.35294};
		assertEquals(values[0],hsbCode[0]);
		assertEquals(values[1],hsbCode[1]);
		assertEquals(values[2],hsbCode[2] );
		
	}

	@Test
	void testGetRGB() {
		String hexColor="#7ad23f";
		Color c = Color.decode(hexColor);
		assertEquals(8049215,c.getRGB());
	}

	@Test
	void testRGBtoHSL() {
		String hexColor="#7ad23f";
		Color c = Color.decode(hexColor);
		float[] values = {(float)95.918365,(float)62.025322, (float)53.52941};
		float[] returnValue = c.RGBtoHSL();
		assertEquals(values[0],returnValue[0]);
		assertEquals(values[1],returnValue[1]);
		assertEquals(values[2],returnValue[2]);
		

	}

	@Test
	void testRGBtoCMYK() {
		String hexColor="#7ad23f";
		Color c = Color.decode(hexColor);
		float[] values = {(float)0.41904765,(float)0.0, (float)0.6999999,(float)17.0};
		float[] returnValue = c.RGBtoCMYK();
		assertEquals(values[0],returnValue[0]);
		assertEquals(values[1],returnValue[1]);
		assertEquals(values[2],returnValue[2]);
		assertEquals(values[3],returnValue[3]);

	}

}
