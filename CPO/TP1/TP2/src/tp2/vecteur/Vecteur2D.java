package tp2.vecteur;
/*
 * @author Ralph Mohdad
 */
public class Vecteur2D {

	private double x, y;

	// creation du constructeur
	public Vecteur2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Calcul de la norme du vecteur
	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	//
	public Vecteur2D multK(double k) {
		return new Vecteur2D(k * x, k * y);
	}

	public static Vecteur2D divisionk(Vecteur2D v1, double k) {
		return new Vecteur2D(v1.x / k, v1.y / k);
	}

	public static double produitScalaire(Vecteur2D v1, Vecteur2D v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}

	public static double produitVectoriel(Vecteur2D v1, Vecteur2D v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}

	public Vecteur2D transpose() {
		return new Vecteur2D(y, x);
	}

	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}

	public Vecteur2D oppose() {
		return new Vecteur2D(-x, -y);
	}

	public static Vecteur2D add(Vecteur2D v1, Vecteur2D v2) {
		return new Vecteur2D(v1.x + v2.x, v1.y + v2.y);
	}

	public static Vecteur2D sub(Vecteur2D v1, Vecteur2D v2) {
		return new Vecteur2D(Vecteur2D.add(v1, v2.oppose()).x, Vecteur2D.add(v1, v2.oppose()).y);
	}

	public String toString() {
		return "<" + this.x + ", " + this.y + ">";

	}

	
	
}
