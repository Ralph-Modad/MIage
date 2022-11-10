package uca.l3.code39.v1;

import frame.FrameHelper;

import java.awt.*;
import java.util.Random;

public class CodeABarres {
	boolean barres[];

	public CodeABarres(boolean[] barres) {
		if (barres.length % 2 == 0) {
			throw new IllegalArgumentException("le nombre de barres doit etre impair");

		}
		this.barres = barres;
	}

	public void paint(Graphics g) {
		int x=0;
		int y=0;
		int height=100;
		for (int i = 0; i < barres.length; i++) {
			Color c = (i % 2 == 0) ? Color.black : Color.white;
			g.setColor(c);
			int width = (barres[i]) ? 6 : 3;
			g.setColor(c);
			g.fillRect(x, y, width, height);
			x+=width;

		}

	}

	public static void main(String[] args) {
		boolean[] barres = new boolean[61];
		Random gen = new Random();
		for (int i = 0; i < barres.length; i++) {
			barres[i] = gen.nextBoolean();
		}
		CodeABarres codeABarres = new CodeABarres(barres);
		new frame.FrameHelper(300, 150).draw(g -> {
			codeABarres.paint(g);
		});
	}
}
