package tp1;
/*
 * @author Ralph Mohdad
*/
public class RegleDeTrois {
	private double m1;
	private double m2;
	
	public RegleDeTrois(double m1, double m2) {
		this.m1 = m1;
		this.m2 = m2;
	}
	public double de2Vers1(double v2) {
		return v2 * m1 / m2 ;
	}
	public double de1Vers2(double v1) {
		return v1 * m2 / m1;
	}
}
