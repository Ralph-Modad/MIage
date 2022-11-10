package tp2.vecteur;
/*
 * @author Ralph Mohdad
 */
public class Vecteur {
	private double[] coords;

	// création du constructeur

	public Vecteur(double... coords) {
		this(coords.length, coords);
	}

	// creation du second constructeur initialisation
	public Vecteur(int n, double... coords) {
		this.coords = new double[n];
		for (int i = 0; i < Math.min(n, coords.length); i++) {
			this.coords[i] = coords[i];
		}
	}

	public double get(int i) {
		return coords[i];
	}

	public double length() {
		double length = 0.0;
		for (double coord : coords) {
			length += Math.pow(coord, 2);
		}
		return Math.sqrt(length);
	}

	public int dimension() {
		return coords.length;
	}

	public Vecteur oppose() {
		double vo[] = new double[this.dimension()];
		for (int i = 0; i < this.dimension(); i++) {
			vo[i] -= this.coords[i];
		}
		return new Vecteur(vo);
	}

	public static Vecteur add(Vecteur... vecteurs) {
		int dim = vecteurs[0].dimension();
		double coords[] = new double[dim];
		for (Vecteur vecteur : vecteurs) {
			for (int i = 0; i < dim; i++) {
				coords[i] += vecteur.coords[i];
			}
		}
		return new Vecteur(coords);
	}

	public static double produitScalaire(Vecteur v1 ,Vecteur v2) {
	
		double res = 0.0;
		for(int i = 0;  i< v1.dimension(); i++) 
			res+= v1.get(i)* v2.get(i);
		return res;
	}
	public static Vecteur produitVectoriel(Vecteur v1, Vecteur v2) throws RuntimeException {
		if (v1.dimension() == 3 && v2.dimension() == 3) {
			return new Vecteur(v1.get(1)*v2.get(2)-v1.get(2)*v2.get(1), v1.get(2)*v2.get(0)-v1.get(0)*v2.get(2), v1.get(0)*v2.get(1)-v1.get(1)*v2.get(0));
		}
		throw new RuntimeException("Dim != 3");
	}

	
	public static Vecteur sub(Vecteur... vecteurs) {
		int dim = vecteurs[0].dimension();
		double vsub[] = new double[dim];
		for (int j = 0; j < vecteurs.length; j++) {
			for (int i = 0; i < dim; i++) {
				if (j == 0) {
					vsub[i] += vecteurs[j].get(i);
				} else {
					vsub[i] -= vecteurs[j].get(i);
				}
			}
		}
		return new Vecteur(vsub);
	}

	public Vecteur multK(double k) {
		double mul[] = new double[this.dimension()];
		for (int i = 0; i < this.dimension(); i++) {
			mul[i] = coords[i] * k;
		}
		return new Vecteur(mul);
	}

	public Vecteur transpose() {
		if (this.dimension() == 2) {
			return new Vecteur(this.coords[1], this.coords[0]);
		}
		throw new RuntimeException("dim != 2");
	}

}