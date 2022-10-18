
public class Smoothing {
	
	public static double[] smoothMA (double [] series, int period) {
		
		MovingAverage ma = new MovingAverage(period);
		double [] nserie = new double [series.length];
		
		for (int i = 0; i < series.length; i++) {
            ma.newNum(series[i]);
            nserie[i] = ma.getAvg();
        }
		
		
		return nserie;
	}
	
	public static double[] smoothEMA (double [] series, int days) {
		
		double exponent = 2.0 / (days + 1);
		ExponentialMovingAverage ema = new ExponentialMovingAverage (exponent);
		double [] nserie = new double [series.length];
		
		for (int i = 0; i < series.length; i++) {
			nserie[i] = ema.average(series[i]);
		}
		
		
		return nserie;
		
	}

}
