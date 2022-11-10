package version1;

// Il faudra importer du code du rendu 2 (par exemple classe Polygone et ExtractTriangles...)

// import ...


import tp2.ExtractTriangles;
import tp2.Polygone;
import tp2.vecteur.Vecteur;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ralph Mohdad
 */
public class SVGToPolygon {

	// On définit le header et footer du svg selon le standard (voir
	// https://developer.mozilla.org/fr/docs/Web/SVG/Tutorial/Getting_Started )
	private static String svgHeader = "<svg width=\"12cm\" height=\"4cm\" viewBox=\"0 0 1200 400\"\n"
			+ "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
			+ "  <desc>Mon SVG (COP - Rendu 3)</desc>\n" + "\n"
			+ "  <!-- Show outline of canvas using 'rect' element -->\n"
			+ "  <rect x=\"1\" y=\"1\" width=\"1198\" height=\"398\"\n"
			+ "        fill=\"none\" stroke=\"blue\" stroke-width=\"2\" />\n" + "\n";

	private static String svgFooter = "</svg>";

	public static int countPolygonTags(String svg) {
		// Compte le nombre de tags de type <polygon ... /> il y a dans le SVG
		Pattern pattern = Pattern.compile("<polygon(\n|.)*?/>");
		Matcher matcher = pattern.matcher(svg);
		return (int) matcher.results().count();
	}

	public static String[] findPolygonTags(String svg) {
		// D'un SVG s, on extrait une liste de strings où chaque string
		// correspond à un tag <polygon ... />
		// NB : utiliser la méthode countPolygonTags peut aider
		Pattern pattern = Pattern.compile("<polygon(\n|.)*?/>");
		Matcher matcher = pattern.matcher(svg);
		String[] string = new String[countPolygonTags(svg)];
		for (int i = 0; i < string.length; ++i) {
			matcher.find();
			string[i] = matcher.group();
		}
		return string;
	}

	public static int countPoints(String pointsAttributeString) {
		// D'une chaine de caractère de la forme "points=..." extraite d'un tag <polygon
		// ...>
		// On compte le nombre de points présents
		// Rappel : un point correspond à deux coordonées x et y
		// Example :
		// dans la chaine points="350,75 469,161 423,301 277,301 231,161"
		// 350,75 correspond à un point (x=350, y=75)

		Pattern pattern = Pattern.compile("([0-9]*,[0-9]*)");
		Matcher matcher = pattern.matcher(pointsAttributeString);
		return (int) matcher.results().count();
	}

	public static Polygone tagToPolygon(String polygonTag) {
		// On lit un tag <polygon ...> et on renvoit l'instance de la classe Polygone
		// qui correspond
		// (1) Trouver l'attribut "points=..." dans le tag
		// (2) Trouver les coordonées de chaque vecteur correspondant à un sommet du
		// polygone
		// NB : utiliser la méthode countPoints peut aider
		// (3) Créer l'instance de Polygone avec ces sommets
		Pattern pattern = Pattern.compile("points=(.|\n)*");
		Matcher matcher = pattern.matcher(polygonTag);
		matcher.find();
		String result = matcher.group();
		int nombredepoints = countPoints(result);
		pattern = Pattern.compile("([0-9]*,[0-9]*)");
		matcher = pattern.matcher(result);
		Vecteur[] vecteurs = new Vecteur[nombredepoints];
		for (int i = 0; i < nombredepoints; i++) {
			matcher.find();
			String[] string = matcher.group().split(",");
			vecteurs[i] = new Vecteur(Double.parseDouble(string[0]), Double.parseDouble(string[1]));
		}
		return new Polygone(vecteurs);
	}

	public static String polygonToTag(Polygone polygon) {
		// On crée un nouveau tag de type <polygon ... /> en suivant la spécification
		// Voir https://developer.mozilla.org/fr/docs/Web/SVG/Element/polygon
		// Vous pouvez customiser la couleur ou autre
		StringBuilder tag = new StringBuilder("<polygon fill=\"purple\" stroke=\"blue\" stroke-width=\"1\" points=\" ");

		// Pour chaque point du polygone
		// Compléter le tag avec ses coordonées
		// Au format : x,y (x et y étant des entiers)
		// Attention à ne pas oublier les espaces entre points
		for (int i = 0; i < polygon.numberOfPoints(); i++)
			tag.append(polygon.getPoint(i).get(0)).append(",").append(polygon.getPoint(i).get(1)).append(" ");
		// On ferme le tag (toujours selon la spécification SVG)
		tag.append("\" />\n");
		return tag.toString();
	}

	public static void main(String[] args) {
		String svg = "<?xml version=\"1.0\" standalone=\"no\"?>\n"
				+ "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n"
				+ "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
				+ "<svg width=\"12cm\" height=\"4cm\" viewBox=\"0 0 1200 400\"\n"
				+ "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
				+ "  <desc>Example polygon01 - pentagon and hexagon</desc>\n" + "\n"
				+ "  <!-- Show outline of canvas using 'rect' element -->\n"
				+ "  <rect x=\"1\" y=\"1\" width=\"1198\" height=\"398\"\n"
				+ "        fill=\"none\" stroke=\"blue\" stroke-width=\"2\" />\n" + "\n"
				+ "  <polygon fill=\"red\" stroke=\"blue\" stroke-width=\"10\" \n"
				+ "            points=\"350,75  469,161 \n" + "                    423,301 277,301 \n"
				+ "                    231,161\" />\n"
				+ "  <polygon fill=\"lime\" stroke=\"blue\" stroke-width=\"10\" \n"
				+ "            points=\"850,75  958,137.5 958,262.5\n"
				+ "                    850,325 742,262.6 742,137.5\" />\n" + "</svg>\n";

		StringBuilder finalSVG = new StringBuilder(SVGToPolygon.svgHeader);

		String[] polygonTags = findPolygonTags(svg); // Extraire la liste des tags <polygon .../> du SVG d'entrée;

		// Pour chaque tag <polygon ... />
		for (int i = 0; i < countPolygonTags(svg); ++i) {
			Polygone polygone = tagToPolygon(polygonTags[i]); // Convertir ce tag en une instance de la classe Polygone;
			Polygone[] triangles = ExtractTriangles.extract(polygone); // Extraire les triangles de ce polygone;

			// Pour chaque triangle
			for (Polygone triangle : triangles) {
				String triangleTag = polygonToTag(triangle);// Convertir le triangle en son équivalent en tag SVG :
															// <polygon ... />;
				// On complète le SVG final
				finalSVG.append(triangleTag);
			}
		}

		finalSVG.append(SVGToPolygon.svgFooter);
		System.out.println("Mon SVG final (triangulé) = ");
		System.out.println(finalSVG);
		// Pour tester que votre résultat est correct, copier coller le SVG final sur
		// https://www.svgviewer.dev/
		// pour voir les triangles s'afficher
	}
}