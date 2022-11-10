package tp1.poly;
/*
 * @author Ralph Mohdad
*/
public class Polynome {
	double a;
	double b;
	double c;

	// Constructor
	public Polynome(double a, double b, double c) {
		
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	// method
	public double delta() {
		return b * b - 4 * a * c;
	}

	// method
	public double racineReelle1() {
		if (delta() < 0) {
			return Double.NaN;
		}
		return -b + Math.sqrt(delta()) / 2 * a;
	}

	// method
	public double racineReelle2() {
		if (delta() < 0) {
			return Double.NaN;
		}
		return -b - Math.sqrt(delta()) / 2 * a;

	}

	// method
	public double eval(double x) {
		return this.a * Math.pow(x, 2) + b * x + c;

	}
}
