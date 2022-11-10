package uca.l3.code39.v3;

import java.awt.*;

public enum Barre {

	EPAIS(3), FINS(3);
	private int width;
	private final static int WIDTH_FIN =3;
	
	
	private Barre(int width) {
		this.width= WIDTH_FIN * width;
	}

	public static Barre gen(char s) {
		if(s=='1')
			return EPAIS;
		else
			return FINS;
	}
	
	public int paint(Graphics g, int x, int y, int height) {
		g.fillRect(x, y, this.width, height);
		return x +width;
		}

	Barre genRandom() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
