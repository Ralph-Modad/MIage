package tp1.vecteur;
/*
 * @author Ralph Mohdad
*/

public class Vecteur {

	private double x, y;

	// creation du constructeur
	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Calcul de la norme du vecteur
	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	//
	public Vecteur multK(double k) {
		return new Vecteur(k * x, k * y);
	}

	public static Vecteur divisionk(Vecteur v1, double k) {
		return new Vecteur(v1.x / k, v1.y / k);
	}

	public static double produitScalaire(Vecteur v1, Vecteur v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}

	public static double produitVectoriel(Vecteur v1, Vecteur v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}

	public Vecteur transpose() {
		return new Vecteur(y, x);
	}

	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}

	public Vecteur oppose() {
		return new Vecteur(-x, -y);
	}

	public static Vecteur add(Vecteur v1, Vecteur v2) {
		return new Vecteur(v1.x + v2.x, v1.y + v2.y);
	}

	public static Vecteur sub(Vecteur v1, Vecteur v2) {
		return new Vecteur(Vecteur.add(v1, v2.oppose()).x, Vecteur.add(v1, v2.oppose()).y);
	}

	public String toString() {
		return "<" + this.x + ", " + this.y + ">";

	}

}
