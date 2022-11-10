package tp1;
/*
 * @author Ralph Mohdad
*/
import tp1.vecteur.Vecteur;

public class Triangle {

	public Vecteur v3;
	public Vecteur v1;
	public Vecteur v2;

	public Triangle(Vecteur v1, Vecteur v2, Vecteur v3) {

		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public double perimetre() {
		return Vecteur.add(v1, v2.oppose()).length() + Vecteur.add(v1, v3.oppose()).length()+ Vecteur.add(v3, v2.oppose()).length();
	}

	public Vecteur barycentre() {
		return  Vecteur.add(Vecteur.add(Vecteur.divisionk(v1, 3), Vecteur.divisionk(v2, 3)), Vecteur.divisionk(v3, 3));

	}
	
	public double distanceCentre() {
		Vecteur barycentre = this.barycentre();
		Vecteur GA = Vecteur.add(v1, barycentre.oppose());
		return GA.length();
	}
}
