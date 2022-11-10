package tp1;
/*
 * @author Ralph Mohdad
*/
import tp1.vecteur.Vecteur;

public class Bezier {

	Vecteur P0,P1,P2,P3;

	public Bezier(Vecteur P0, Vecteur P1, Vecteur P2, Vecteur P3) {
	this.P0 = P0;
	this.P1 = P1;
	this.P2 = P2;
	this.P3 = P3;
	}

	public Vecteur getBegin() {
		return P0;
	}

	public Vecteur getEnd() {
		
		return P3;
	}

	public Vecteur getControl1() {
		
		return P1;
	}

	public Vecteur getControl2() {
		
		return P2;
	}


	public Vecteur eval(double t) {
		double t1;
		double t3;
		t1 =  Math.pow(t, 3) * P0.getX() + 3 * Math.pow(t, 2) * (1 - t) * P1.getX() + 3 * t * Math.pow((1-t), 2) * P2.getX() + Math.pow((1-t), 3) * P3.getX();
	    t3 =  Math.pow(t, 3) * P0.getY() + 3 * Math.pow(t, 2) * (1 - t) * P1.getY() + 3 * t * Math.pow((1-t), 2) * P2.getY() + Math.pow((1-t), 3) * P3.getY();
	   Vecteur v = new Vecteur (t1,t3);
	   return v;
	}
		
}
	
