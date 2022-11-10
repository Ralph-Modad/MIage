package ellipse;

import java.awt.Graphics;

import tp2.vecteur.Vecteur;
import tp4.uca.shape.IShape;

public class Ellipse implements IShape {
	private Vecteur centre;
	private double a;
	private double b;

	public Ellipse(Vecteur centre, double a, double b) {
		this.centre = centre;
		this.a = a;
		this.b = b;
	}

	
	public Vecteur getAnchor() {
		// TODO Auto-generated method stub
		return centre;
	}

	
	public void setAnchor(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	
	public double area() {
		// TODO Auto-generated method stub
		return Math.PI *a *b ;
	}

	
	public double perimeter() {
		// TODO Auto-generated method stub
		return 2 *Math.PI * Math.sqrt((a*a+b*b)* 0.5);
	}

	public void paint(Graphics g) {
		g.drawOval((int)centre.get(0), (int)centre.get(1), (int)a*2 , (int) b*2 );

	}

}
