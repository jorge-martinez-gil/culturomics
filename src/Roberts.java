
public class Roberts {
	
	static double measure (double[] v1, double[] v2) {
		
		double acum1 = 0;
		double acum2 = 0;
		
		for (int i = 0; i < v1.length; i++) {
			
		double v = 0;	
			if (Math.max(v1[i], v2[i]) == 0)
				v = 0.00001;
			else
				v = Math.max(v1[i], v2[i]);
			
			acum1 = acum1 + ((v1[i]+v2[i]) * (Math.min(v1[i], v2[i])/v));
			acum2 = acum2 + (v1[i]+v2[i]);
			
		}
		
		if (acum2 == 0) acum2 = acum2 + 0.0000001;
		return acum1/acum2;
		
	}

}
