package tp2.entiers.statique;
/*
 * @author Ralph Mohdad
 */
public class RechercheTableaux {

	public static int findNinT(int[] tab, int n) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == n) {
				return n;
			}
		}
		return -1;
	}

	public static int countNsinT(int[] tab, int n) {
		int count = 0;
		for (int j = 0; j < tab.length; j++) {
			if (tab[j] == n) {
				count++;
			}
		}
		return count;
	}

	public static int[] findAllNsinT(int[] tab, int n) {
		int count[] = new int[countNsinT(tab,n)];
		int x = 0;
		for(int j = 0;j<tab.length;j++) {
			if (tab[j] == n) {
				count[x]=j;
				x++;
			}
		}

		return count;
	}

	public static int findNinSortedT(int[] tab, int i) {
		int count = -1;
		for(int j = 0;j<tab.length;j++) {
			if (tab[j] == i) {
				count=j;
				break;
			}
		}
		return count;
	}

	public static int[] findAllNsinSortedT(int[] tab, int i) {
	
		
		
		
		return null;
	}

}
