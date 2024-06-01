import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
	double coefficients[];
	int exponents[];
	
	public Polynomial () {
		coefficients = new double[0];
		exponents = new int[0];
	}

	public Polynomial (File newFile) {
		coefficients = new double[0];
		exponents = new int[0];
		try {
			//System.out.println("hello");
			Scanner input = new Scanner(newFile);
			String polyString = input.nextLine();
			input.close();
			char polyCharArray[] = polyString.toCharArray();
			//System.out.println(Arrays.toString(polyCharArray));
			

			int i = 0;
			while (i < polyCharArray.length) {
				// dissect the expression into blocks

				char polyBlock[] = {polyCharArray[i]};
				//char polyBlock[] = {};
				int j = 1;
				while (i + j < polyCharArray.length && (polyCharArray[i + j] != '-' && polyCharArray[i + j] != '+')) {
					polyBlock = Arrays.copyOf(polyBlock, polyBlock.length + 1);
					polyBlock[polyBlock.length - 1] = polyCharArray[i + j];
					j++;
				}
				//System.out.println(Arrays.toString(polyBlock));
				double newCoefficient;
				int newExponent;
				int xLocation = -1;
				for (int index = 0; index < polyBlock.length; index++) {
					if (polyBlock[index] == 'x') {
						xLocation = index;
					}
				}
				if (xLocation == -1) {
					newExponent = 0;
					newCoefficient = Double.parseDouble(new String(polyBlock));
					//System.out.println(newCoefficient);
				}
				else {
					char polyBlockCoefficient[] = Arrays.copyOfRange(polyBlock, 0, xLocation);
				    char polyBlockExponent[] = Arrays.copyOfRange(polyBlock, xLocation + 1, polyBlock.length);
					newExponent = Integer.parseInt(new String(polyBlockExponent));
					newCoefficient = Double.parseDouble(new String(polyBlockCoefficient));
					//System.out.println(newExponent);
					//System.out.println(newCoefficient);
				}
				this.coefficients = Arrays.copyOf(this.coefficients, this.coefficients.length + 1);
				this.exponents = Arrays.copyOf(this.exponents, this.exponents.length + 1);
				this.coefficients[this.coefficients.length - 1] = newCoefficient;
				this.exponents[this.exponents.length - 1] = newExponent;

				i = i + j;
			}
			System.out.println(Arrays.toString(this.coefficients));
			System.out.println(Arrays.toString(this.exponents));
		}
		catch (FileNotFoundException a){
			System.out.println("File not found.");
		}
	}
	
	public Polynomial (double inputCoefficients[], int inputExponents[]) {

		//double nonEmptyCoefficients[] = new double[0];
		//int nonEmptyExponents[] = new int[0];

		coefficients = new double[0];
		exponents = new int[0];

		for (int i = 0; i < inputCoefficients.length; i++) {
			if (inputCoefficients[i] == 0) {
				continue;
			}
			else if (this.containsExponentWithIndex(inputExponents[i]) == -1) {
				coefficients = Arrays.copyOf(coefficients, coefficients.length + 1);
				exponents = Arrays.copyOf(exponents, exponents.length + 1);
				coefficients[coefficients.length - 1] = inputCoefficients[i];
				exponents[exponents.length - 1] = inputExponents[i];
			}
			else {
				coefficients[this.containsExponentWithIndex(inputExponents[i])] += inputCoefficients[i];
			}
		}

		System.out.println(Arrays.toString(coefficients));
		System.out.println(Arrays.toString(exponents));
		// think about sorting the arguments, perhaps writing a function to do so
	}
	// there will be two arguments for the input

	public int containsExponentWithIndex (int x) {
		for (int i = 0; i < this.exponents.length; i++) {
			if (this.exponents[i] == x) {
				return i;
			}
		}
		return -1;
	}

	public Polynomial squeezing () {
		Polynomial squeezedPolynomial = new Polynomial();
		for (int i = 0; i < this.coefficients.length; i++) {
			if (this.coefficients[i] != 0) {
				squeezedPolynomial.coefficients = Arrays.copyOf(squeezedPolynomial.coefficients, squeezedPolynomial.coefficients.length + 1);
				squeezedPolynomial.exponents = Arrays.copyOf(squeezedPolynomial.exponents, squeezedPolynomial.exponents.length + 1);
				squeezedPolynomial.coefficients[squeezedPolynomial.coefficients.length - 1] = this.coefficients[i];
				squeezedPolynomial.exponents[squeezedPolynomial.exponents.length - 1] = this.exponents[i];
			}
		}
		return squeezedPolynomial;
	}

	public Polynomial add (Polynomial p) {
		if (this.coefficients.length == 0 && p.coefficients.length == 0) {
			Polynomial newPolynomial = new Polynomial();
			return newPolynomial;
		}
		if (this.coefficients.length != 0 && p.coefficients.length == 0) {
			return this; // I am not sure, i will check that later
		}
		if (this.coefficients.length == 0 && p.coefficients.length != 0) {
			return p;
		}

		Polynomial newTempPolynomial = new Polynomial();

		//double newTempCoefficients[] = new double[0];
		//int newTempExponents[] = new int[0];

		//add them together
		for (int i = 0; i < this.coefficients.length; i++) {
			if (newTempPolynomial.containsExponentWithIndex(this.exponents[i]) == -1) {
				newTempPolynomial.coefficients = Arrays.copyOf(newTempPolynomial.coefficients, newTempPolynomial.coefficients.length + 1);
				newTempPolynomial.exponents = Arrays.copyOf(newTempPolynomial.exponents, newTempPolynomial.exponents.length + 1);
				newTempPolynomial.coefficients[newTempPolynomial.coefficients.length - 1] = this.coefficients[i];
				newTempPolynomial.exponents[newTempPolynomial.exponents.length - 1] = this.exponents[i];
			}
			else {
				newTempPolynomial.coefficients[newTempPolynomial.containsExponentWithIndex(this.exponents[i])] += this.coefficients[i];
			}
		}

		for (int i = 0; i < p.coefficients.length; i++) {
			if (newTempPolynomial.containsExponentWithIndex(p.exponents[i]) == -1) {
				newTempPolynomial.coefficients = Arrays.copyOf(newTempPolynomial.coefficients, newTempPolynomial.coefficients.length + 1);
				newTempPolynomial.exponents = Arrays.copyOf(newTempPolynomial.exponents, newTempPolynomial.exponents.length + 1);
				newTempPolynomial.coefficients[newTempPolynomial.coefficients.length - 1] = p.coefficients[i];
				newTempPolynomial.exponents[newTempPolynomial.exponents.length - 1] = p.exponents[i];
			}
			else {
				newTempPolynomial.coefficients[newTempPolynomial.containsExponentWithIndex(p.exponents[i])] += p.coefficients[i];
			}
		}

		//System.out.println(Arrays.toString(newTempPolynomial.coefficients));
		//System.out.println(Arrays.toString(newTempPolynomial.exponents));
		Polynomial squeezedPolynomial = newTempPolynomial.squeezing();

		System.out.println(Arrays.toString(squeezedPolynomial.coefficients));
		System.out.println(Arrays.toString(squeezedPolynomial.exponents));

		return squeezedPolynomial;

		// we haven't done the squeezing part (done)
	}
	
	public double evaluate (double x) {
		double result = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			result = result + this.coefficients[i] * Math.pow(x, this.exponents[i]);
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

	public Polynomial multiply (Polynomial p) {

		Polynomial newTempPolynomial = new Polynomial();

		for (int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < p.coefficients.length; j++) {
				double newCoefficient = this.coefficients[i] * p.coefficients[j];
				int newExponent = this.exponents[i] + p.exponents[j];
				if (newTempPolynomial.containsExponentWithIndex(newExponent) == -1) {
					newTempPolynomial.coefficients = Arrays.copyOf(newTempPolynomial.coefficients, newTempPolynomial.coefficients.length + 1);
					newTempPolynomial.exponents = Arrays.copyOf(newTempPolynomial.exponents, newTempPolynomial.exponents.length + 1);
					newTempPolynomial.coefficients[newTempPolynomial.coefficients.length - 1] = newCoefficient;
					newTempPolynomial.exponents[newTempPolynomial.exponents.length - 1] = newExponent;
				}
				else {
					newTempPolynomial.coefficients[newTempPolynomial.containsExponentWithIndex(newExponent)] += newCoefficient;
				}
			}
		}
		//System.out.println(Arrays.toString(newTempPolynomial.coefficients));
		//System.out.println(Arrays.toString(newTempPolynomial.exponents));
		Polynomial squeezedPolynomial = newTempPolynomial.squeezing();

		System.out.println(Arrays.toString(squeezedPolynomial.coefficients));
		System.out.println(Arrays.toString(squeezedPolynomial.exponents));

		return squeezedPolynomial;
	}

	public void saveToFile(File outputFile) {
		try {
			FileWriter fileWriter = new FileWriter(outputFile, true);
			String outputStringArray[] = {};
			for (int i = 0; i < this.coefficients.length; i++) {
			    if (this.exponents[i] == 0 && this.coefficients[i] != 0) {
					outputStringArray = Arrays.copyOf(outputStringArray, outputStringArray.length + 1);
					outputStringArray[outputStringArray.length - 1] = Double.toString(this.coefficients[i]);
				}
				else if (this.exponents[i] != 0 && this.coefficients[i] != 0) {
					if (this.coefficients[i] > 0) {
						outputStringArray = Arrays.copyOf(outputStringArray, outputStringArray.length + 1);
					    outputStringArray[outputStringArray.length - 1] = "+";
					}
					outputStringArray = Arrays.copyOf(outputStringArray, outputStringArray.length + 3);
					outputStringArray[outputStringArray.length - 3] = Double.toString(this.coefficients[i]);
					outputStringArray[outputStringArray.length - 2] = "x";
					outputStringArray[outputStringArray.length - 1] = Integer.toString(this.exponents[i]);
				}
			}
			String outputString = "";
			for (String str : outputStringArray) {
				outputString = outputString + str;
			}
			System.out.println(outputString);
			fileWriter.write(outputString);
			fileWriter.close();
		}
		catch (IOException a) {
			System.out.println("File not found.");
		}
	}
}
