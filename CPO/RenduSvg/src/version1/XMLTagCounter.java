package version1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Ralph Mohdad
 *
 */
public class XMLTagCounter {

	private static final Pattern dataPattern  = Pattern.compile("<([^>]+)>", Pattern.DOTALL);
	private static final Pattern dataPattern2  = Pattern.compile("<[^>!?]+>" , Pattern.DOTALL);
	
    public static int countTags(String s, boolean ignoreSpecials) {
        // Compter le nombre de fois où on trouve le pattern dans s
    	
    	int nb = 0;
    	Matcher m = (ignoreSpecials == true) ? dataPattern2.matcher(s) :  dataPattern.matcher(s);
    	
    	while(m.find()) {
    		nb++;
    	}
    	return (nb);
    }
	
    public static void displayTags(String s, boolean ignoreSpecials) {
        // Afficher chacun des tags trouvé ( avec System.out.println(...))
    	Matcher m = (ignoreSpecials == true) ? dataPattern2.matcher(s) :  dataPattern.matcher(s);
    	
    	int compteurs = 1 ;
    	while(m.find()) {
    		System.out.println("[Balise n°" + compteurs+" ]\n ");
        	System.out.println( m.group(0));
        	compteurs ++;
    	}
    }
	
    public static void main(String[] args) {
        String s = "<?xml version=\"1.0\" standalone=\"no\"?>\n"
                + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n"
                + "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n"
                + "<svg width=\"12cm\" height=\"4cm\" viewBox=\"0 0 1200 400\"\n"
                + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
                + "  <desc>Example polygon01 - pentagon and hexagon</desc>\n"
                + "\n"
                + "  <!-- Show outline of canvas using 'rect' element -->\n"
                + "  <rect x=\"1\" y=\"1\" width=\"1198\" height=\"398\"\n"
                + "        fill=\"none\" stroke=\"blue\" stroke-width=\"2\" />\n"
                + "\n"
                + "  <polygon fill=\"red\" stroke=\"blue\" stroke-width=\"10\" \n"
                + "            points=\"350,75  469,161 \n"
                + "                    423,301 277,301 \n"
                + "                    231,161\" />\n"
                + "  <polygon fill=\"lime\" stroke=\"blue\" stroke-width=\"10\" \n"
                + "            points=\"850,75  958,137.5 958,262.5\n"
                + "                    850,325 742,262.6 742,137.5\" />\n"
                + "</svg>\n";

        // Compter le nb total de balises (on attend la réponse 10)
        int count = XMLTagCounter.countTags(s, false);
        System.out.println("Nombre total de tags = " + count);
        XMLTagCounter.displayTags(s, false);

        // Compter le nb de balises hors balises spéciales (on attend la réponse 7)
        int count2 = XMLTagCounter.countTags(s, true);
        System.out.println("Nombre de tags (hors commentaires et headers) = " + count2);
    }
    
    

}