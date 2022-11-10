package rectangle;

import java.awt.Graphics;

import tp2.vecteur.Vecteur;
import tp4.uca.shape.IShape;

public class Rectangle implements IShape{

	private Vecteur anchor;
	private double largeur ;
	private double hauteur ;
	
	public Rectangle(Vecteur anchor, double largeur, double hauteur) {
		this.anchor=anchor;
		this.largeur=largeur;
		this.hauteur=hauteur;
	}
	

	@Override
	public Vecteur getAnchor() {
		// TODO Auto-generated method stub
		return anchor;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return hauteur *largeur;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 2* hauteur + 2 *largeur  ;
	}

	@Override
	public void paint(Graphics g) {
	g.drawRect((int)anchor.get(0), (int)anchor.get(1), (int)largeur , (int) hauteur );	
	}


	@Override
	public void setAnchor(int x, int y) {
		// TODO Auto-generated method stub
	}
}
