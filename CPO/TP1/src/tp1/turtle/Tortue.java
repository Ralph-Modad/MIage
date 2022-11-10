package tp1.turtle;
/*
 * @author Ralph Mohdad
*/
import tp1.RegleDeTrois;

public class Tortue {
	 
	 private double x,y;
	 private double orientation ;
	 private RegleDeTrois rule = new RegleDeTrois(Math.PI, 180);

	public Tortue(double x,double y, double orientation ){
		this.x = x;
		this.y = y;
		this.orientation  = orientation ;
	}
//initialisation de la tortue a la position (0,0, et 90 degré)
	public Tortue() {
		this.x=0;
		this.y=0;
		this.orientation  = Math.PI/2;// en radian 
	}

	public double getX() {	
		return x;
	}
	public double getY() {	
		return y;
	}
	//conversion de l'orientation en degre afin d'obtenir un return en degré
	public double getDirection() { 
		int angleDeg = (int) rule.de1Vers2(orientation);
		return Math.floorMod(angleDeg, 180);
		
	}

	public void avance(double step) {
		
		x +=    (step * Math.cos(orientation ));
		y +=    (step * Math.sin(orientation ));
	}

	public void recule(double step) {
		x -=   (step * Math.cos(orientation ));
		y -=   (step * Math.sin(orientation ));
	}
	
	public void droite(double angle) {
		
		orientation -= rule.de2Vers1(angle);
		
	}
	
	public void gauche(int i) {
		
		orientation += rule.de2Vers1(i);
		
		
	}
	public void setPosition(int x, int y) {
	this.x=x;
	this.y=y;
		
	}
	
	

}
