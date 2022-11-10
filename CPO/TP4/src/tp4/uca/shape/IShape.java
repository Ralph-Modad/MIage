package tp4.uca.shape;
import java.awt.Graphics;
import tp2.vecteur.*;

public interface IShape {
	
public Vecteur getAnchor();

public void setAnchor(int x, int y);

public double area();

public double perimeter();

public void paint (Graphics g);	


}
