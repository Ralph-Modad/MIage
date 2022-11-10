package tp2;
/*
 * @author Ralph Mohdad
 */
public class ExtractTriangles {

public static Polygone[] extract(Polygone p) {

	        Polygone[] triangles = new Polygone[p.numberOfPoints() - 2];
	        for (int i = 1; i < p.numberOfPoints() - 1; i++) {
	            triangles[i - 1] = new Polygone(p.getPoint(0), p.getPoint(i), p.getPoint(i + 1));
	        }
	        return triangles;
	    }
}
