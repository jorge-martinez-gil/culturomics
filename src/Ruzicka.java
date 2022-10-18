
public class Ruzicka {
	
	static double measure (double [] v1, double [] v2) {
		
		double acum1 = 0;
		double acum2 = 0;
		
		for (int i = 0; i < v1.length; i++) {
			acum1 = acum1 + Math.min(v1[i], v2[i]);
			acum2 = acum2 + Math.max(v1[i], v2[i]);
		}	
		return acum1/acum2;
	}

}
