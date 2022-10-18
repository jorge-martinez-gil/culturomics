import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.distance.*;
import net.sf.javaml.distance.dtw.DTWSimilarity;


public class principal {
	
	static double [][] vector = new double [56][201]; 
	
	static int periodo = 20;
	
	static int FechaInicio = 1802;
	static int FechaFin = 2000;
		
	// Source Word 
	static ArrayList<String> par1 = new ArrayList<String>();
	
	// Target Word
	static ArrayList<String> par2 = new ArrayList<String>();
	
	// Official Values
	public static ArrayList<Double> valores = new ArrayList<Double>();
	
	// Resultado
	public static ArrayList<Double> RESULTADO = new ArrayList<Double>();
	
	static Smoothing smooth = new Smoothing ();

	static double [][] vector_aux; 

	
	
	public static void cargar() {
		// TODO Auto-generated method stub

		
		
		try {
			
			File archivo = new File("google.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			for (int i = 0; i < 56; i++) {
				int j = 0;
				String linea = br.readLine();
				linea = br.readLine();
				while (j < 201) {

					int ini = linea.indexOf("[");
					int fin = linea.indexOf("]");
					String a = linea.substring(ini+7, fin);
				
					//System.out.println (a);
					vector [i][j] = Double.valueOf(a);
				
					linea = linea.substring(fin + 1);
					j++;
				}	
			}
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		for (int i = 0; i < 56; i++)
			for (int j = 0; j < 201; j++) {
				vector[i][j] = vector[i][j] * 100000;
				System.out.println((vector[i][j]));
			}
		
	}

	



	
	private static double[] reduction (double [] series) {

		double max = 0;
		for (int i = 0; i < series.length; i++) {
			if (series[i] >= max) max = series[i];
		}
		
		double [] newSeries = new double [series.length];
		
		for (int i = 0; i < series.length; i++)
			newSeries[i] = series[i]/max;
		
		/*for (int i = 0; i < series.length; i++)
			System.out.println("*" + newSeries[i]);*/
		
		
		return newSeries;

	}
	
	private static double [] baselineRemoval (double [] series ) {
		
		double [] ser = new double [series.length];
		
		double acum = 0;
		
		for (int i = 0; i < series.length; i++) {
			acum = acum + series[i];
		}
		
		double mean = acum / series.length;
		//System.out.println("-->" + mean);
		
		
		for (int j = 0; j < series.length; j++) {
			ser[j] = series[j] - mean;
			//System.out.println(ser[j]);
		}
		
		
		return ser;
		
	}
	
	private static double [] rescaling (double [] series){
		
		
		double [] ser = new double [series.length];
		
		double value = StandardDeviation.standardDeviationCalculate(series);
		
		for (int i = 0; i < series.length; i++) {
			ser[i] = series[i]/value;
			//System.out.println (ser[i]);
		}
		
		return ser;
		
	}
	
	  /**
	   * Comparar usando Cosine Distance
	   * Que puede consistir de varias frases o párrafos.
	   *
	   * @etiqueta texto específico de la etiqueta
	   */
		public static double compararCosine (int a, int b) {
		
			CosineDistance cosine = new CosineDistance();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return (1/(cosine.measure (instance, instance2)+1));
			
		
		}
	
		
		/**
		* Comparar usando Cosine Distance
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararChebychev (int a, int b) {
		
			ChebychevDistance chebychev = new ChebychevDistance ();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( 1/(chebychev.measure (instance, instance2)+1));
		
		}
		
		/**
		* Comparar usando DTW Similarity
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararDTW (int a, int b) {
		
			DTWSimilarity dtw = new DTWSimilarity();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( dtw.measure(instance, instance2));
		
		}
		
		/**
		* Comparar usando Euclidean Distance
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararEuclidean (int a, int b) {
		
			EuclideanDistance euclidean = new EuclideanDistance ();
					
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( 1/(euclidean.measure (instance, instance2)+1));
		
		}
		
		/**
		* Comparar usando Jaccard Distance
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararJaccard (int a, int b) {
		
			JaccardIndexDistance jaccard = new JaccardIndexDistance ();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( 1/(jaccard.measure (instance, instance2)+1));
		
		}
		
		/**
		* Comparar usando Manhattan Distance
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararManhattan (int a, int b) {
		
			ManhattanDistance manhattan = new ManhattanDistance ();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( 1/(manhattan.measure (instance, instance2)+1));
		
		}
		
		/**
		* Comparar usando Roberts Similarity
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararRoberts (int a, int b) {
		
			Roberts ro = new Roberts ();
												
			return ( ro.measure (vector[a], vector[b]));
		
		}
		
		/**
		* Comparar usando Pearson Correlation
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararPearson (int a, int b) {
		
			PearsonCorrelationCoefficient pearson = new PearsonCorrelationCoefficient ();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( pearson.measure (instance, instance2));
		
		}
	
		/**
		* Comparar usando Roberts Similarity
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		@SuppressWarnings("static-access")
		public static double compararRuzicka (int a, int b) {
		
			Ruzicka ru = new Ruzicka ();
												
			return (ru.measure (vector[a], vector[b]));
		
		}
		
		
		/**
		* Comparar usando Spearman Correlation
		* Que puede consistir de varias frases o párrafos.
		 * @return 
		*
		* @etiqueta texto específico de la etiqueta
		*/		
		public static double compararSpearman (int a, int b) {
		
		    SpearmanRankCorrelation spearman = new SpearmanRankCorrelation ();
									
			Instance instance = new DenseInstance(vector[a],"positive");
			Instance instance2 = new DenseInstance(vector[b],"positive");
		
			return ( spearman.measure(instance, instance2));
		
		}
	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)  {

           cargar ();
		   cargar2 ();
           
		 simple ();
           
		   
		 // for (int i = 0; i < 201; i++)
			//   System.out.println(vector[0][i]);
		   
         try {
        	  System.out.println("FIN");
			timeWindow();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void simple () {
		
		
		for (int i = 0; i < 56; i++) {
			/*Reduction*/
			//vector[i] = reduction (vector[i]);
		
			/*Baseline Removal*/
			//vector[i] = baselineRemoval (vector[i]);
		
			/*Rescaling*/
			//vector[i] = rescaling (vector[i]);
		
			/*Simple Smoothing*/
			//vector[i] = Smoothing.smoothMA (vector[i], 50);
		
			/*Exponential Smoothing*/
			//vector[i] = smooth.smoothEMA (vector[i], 50);
		}
		
		for (int i = 0; i < 56; i=i+2)
			RESULTADO.set(i/2, compararCosine (i, i+1));
		
		calidad("Cosine");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararChebychev (i, i+1));
		
		calidad("Chebychev");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararDTW (i, i+1));
		
		calidad("DTW");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararEuclidean (i, i+1));
		
		calidad("Euclidean");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararJaccard (i, i+1));
		
		calidad("Jaccard");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararManhattan (i, i+1));
		
		calidad("Manhattan");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararRoberts (i, i+1));
		
		calidad("Roberts");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararPearson (i, i+1));
		
		calidad("Pearson");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararRuzicka (i, i+1));
		
		calidad("Ruzicka");
		
		for (int i = 0; i < 56; i=i+2) 
			RESULTADO.set(i/2, compararSpearman (i, i+1));
		
		calidad("Spearman");
		
		
	}
	
	
	private static void prepararVector (int a, int b, int lon) {
	
		  vector = new double [56][lon];
			
		  for (int i = 0; i < 56; i++)
			  for (int j = 0; j < lon; j++)	
				  vector[i][j] = vector_aux[i][j+a]; 
		  
	}
	
	
	public static void timeWindow () throws InterruptedException {
		
		double max = 0;
		double num = 0;
		String window = null;
		
		
		//for (int i = 0; i < 56; i++) 
			//vector[i] = Smoothing.smoothMA (vector[i], 50);
		
	    vector_aux = new double [56][201];
		for (int i = 0; i < 56; i++) {
			for (int j = 0; j < 201; j++) {		
				vector_aux[i][j] = vector[i][j]; 
			}
		}
		
		
		for (int a = 0; a < 199; a++) {
			  for (int b = a+2; b < 201; b++) {	
			
		    prepararVector (a, b, b-a+1);
	
		    System.out.println (a + "::" + b + " Length: " + (b-a+1));
		    System.out.println ("Maximum until now: " + max + " in " + window);
		    Thread.sleep(1200);
					
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararCosine (i, i+1));
			
			num = Math.abs(calidad("Cosine"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararChebychev (i, i+1));
			
			num = Math.abs(calidad("Chebychev"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararDTW (i, i+1));
			
			num = Math.abs(calidad("DTW"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararEuclidean (i, i+1));
			
			num = Math.abs(calidad("Euclidean"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararJaccard (i, i+1));
			
			num = Math.abs(calidad("Jaccard"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararManhattan (i, i+1));
			
			num = Math.abs(calidad("Manhattan"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararRoberts (i, i+1));
			
			num = Math.abs(calidad("Roberts"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararPearson (i, i+1));
			
			num = Math.abs(calidad("Pearson"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararRuzicka (i, i+1));
			
			num = Math.abs(calidad("Ruzicka"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			for (int i = 0; i < 56; i=i+2) 
				RESULTADO.set(i/2, compararSpearman (i, i+1));
			
			num = Math.abs(calidad("Spearman"));
			if (num > max) {max = num; window = a + "::" + b;}
			
			  }}
		
	}
	
	
	public static double calidad (String value) {
		
		
		double aaa [] = new double [valores.size()];
		double bbb [] = new double [valores.size()];
		for (int i = 0; i < RESULTADO.size(); i++) {
			//System.out.println("View " + par1.get(i) + " " + par2.get(i)+ " " + valores.get(i) + " " + RESULTADO.get(i));
			aaa[i] = (double) RESULTADO.get(i);
			bbb[i] = (double) valores.get(i);
		}
	
       System.out.println(value + "##" + getPearsonCorrelation (aaa, bbb));

       return (getPearsonCorrelation (aaa, bbb));
		
	}
	
	
	/** 
	 * Compute the Pearson Correlation Coefficient
	 */ 
	public static double getPearsonCorrelation(double[] scores1, double[] scores2) {
		double result = 0;
		double sum_sq_x = 0;
		double sum_sq_y = 0;
		double sum_coproduct = 0;
		double mean_x = scores1[0];
		double mean_y = scores2[0];
		for (int i = 2; i < scores1.length + 1; i += 1) {
			double sweep = Double.valueOf(i - 1) / i;
			double delta_x = scores1[i - 1] - mean_x;
			double delta_y = scores2[i - 1] - mean_y;
			sum_sq_x += delta_x * delta_x * sweep;
			sum_sq_y += delta_y * delta_y * sweep;
			sum_coproduct += delta_x * delta_y * sweep;
			mean_x += delta_x / i;
			mean_y += delta_y / i;
		}
		double pop_sd_x = (double) Math.sqrt(sum_sq_x / scores1.length);
		double pop_sd_y = (double) Math.sqrt(sum_sq_y / scores1.length);
		double cov_x_y = sum_coproduct / scores1.length;
		result = cov_x_y / (pop_sd_x * pop_sd_y);
		return result;
	}
	
	public static void cargar2() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File("text.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String a, b, c;
			String linea = new String();
			String linea2 = new String();

			a = new String();
			b = new String();
			c = new String();

			a = null;
			b = null;
			c = null;

			int id, id2 = 0;
			linea = br.readLine();
			linea2 = null;
			while (linea != null) {

				id = linea.indexOf("#");
				a = linea.substring(0, id);

				linea2 = linea.substring(id + 1);
				id2 = linea2.indexOf("#");

				b = linea2.substring(0, id2);
				c = linea2.substring(id2 + 1);

				// System.out.println (a+b+c);
				linea = br.readLine();

				par1.add(a);
				par2.add(b);
				valores.add(Double.valueOf(c));
				RESULTADO.add(0.0);

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


}
