
public class Polynomial {
	double coefficients[];
	
	public Polynomial () {
		coefficients = new double[] {0};
	}
	
	public Polynomial (double coefficients[]) {
		this.coefficients = coefficients;
	}
	
	public Polynomial add (Polynomial p) {
		
		int maxLength = Math.max(p.coefficients.length, this.coefficients.length);
		double newArray[] = new double[maxLength];
		
		for (int i = 0; i < maxLength; i++) {
			if (i < p.coefficients.length && i < this.coefficients.length) {
				newArray[i] = this.coefficients[i] + p.coefficients[i];
			}
			else if (i < p.coefficients.length) {
				newArray[i] = p.coefficients[i];
			}
			else if (i < this.coefficients.length) {
				newArray[i] = this.coefficients[i];
			}
		}
		
		Polynomial py = new Polynomial(newArray);
		
		return py;
	}
	
	public double evaluate (double x) {
		
		double result = 0;
		
		for (int i = 0; i < this.coefficients.length; i++) {
			result = result + coefficients[i] * Math.pow(x, i);
		}
		
		return result;
	}
	
	public boolean hasRoot (double x) {
		
		double result = evaluate(x);
		if (result == 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
