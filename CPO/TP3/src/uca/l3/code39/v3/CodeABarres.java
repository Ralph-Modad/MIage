package uca.l3.code39.v3;

import java.awt.*;


public class CodeABarres {
	private Barre[] barres;
	private final static int HEIGHT=500;

	public CodeABarres(String s) {
		barres = new Barre[s.length()];
		for(int i = 0; i<s.length();i++) {
		barres[i]=Barre.gen(s.charAt(i));	
		}
	}

	public void paint(Graphics g) {
		int x=10;
		int y=25;
		
		for (int i = 0; i < barres.length; i++) {
			Color c = (i % 2 == 0) ? Color.black : Color.white;
			g.setColor(c);
			x=barres[i].paint(g, x, y, HEIGHT);
		}

	}

	public static void main(String[] args) {
		Barre[]exemple = new Barre[61];
		for (int i = 0; i < exemple.length; i++) {
			exemple[i] = Barre.genRandom();
		}
		CodeABarres codeABarres = new CodeABarres(exemple);
		new frame.FrameHelper(300, 150).draw(g -> {
			codeABarres.paint(g);
		});
	}
}
