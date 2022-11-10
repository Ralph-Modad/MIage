package tp2;
/*
 * @author Ralph Mohdad
 */

import tp2.vecteur.Vecteur;

public class Polygone {
    // Constructor and constructor variable
	private Vecteur[] points;
		
	 public Polygone(Vecteur... v) {
	        Vecteur[] vect = new Vecteur[v.length];
	        for (int i = 0; i < v.length; i++) {
	            vect[i] = v[i];
	        }
	        this.points = vect;
	    }
	

	public Vecteur getPoint(int i) throws RuntimeException{
		if (i > this.points.length) {
			throw new RuntimeException("i > nbr de points");
		} else {
			return this.points[i];
		}
	}
	
	// Retourne le nombre de points constituant le polygone
	public int numberOfPoints() {
		return this.points.length;
	}
	
	// Détermine et renvoi le barycentre du polygone
	public Vecteur barycentre() {
		Vecteur vecteur = Vecteur.add(this.points);
		return vecteur.multK(1.0/this.points.length);
	}
	
	// Calcul et renvoi le périmètre du polygone
	public double perimetre() {
		double perimetre = 0;
		for(int i = 0; i < this.points.length; i++) {
			if (i < this.points.length - 1) {
				perimetre += Vecteur.sub(this.points[i],this.points[i+1]).length();
			} else {
				perimetre += Vecteur.sub(this.points[i], this.points[0]).length();
			}
		}
		return perimetre;
	}
	
}