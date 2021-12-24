package neyronika1;

import java.util.Random;

public class perceptron {
	int inputsize = 0;
	float[] input;
	float[] weights;
	float bias = 1;
	String function;
	
	public perceptron(int inputsize,String function) {
		this.function = function;
	    Random rand = new Random();

		
		this.inputsize = inputsize;
		this.input = new float[inputsize];
		this.weights = new float[inputsize];
		
		for (int i = 0;i < inputsize;i++) {
			this.weights[i] = rand.nextFloat();
		}
	}
	
	public float relu(float value) {
		return Math.max(0,value);
	}
	public float Tanh(float value) {
		return (float)Math.tanh(value);
	}
	
	
	
	public void setInputs(float[] inputs) {
		for (int i = 0;i < this.inputsize;i++) {
			this.input[i] = inputs[i];
		}
	}
	
	

	public float evaluate() {
		float SUM = bias;
		for(int i = 0;i < this.inputsize;i++) {
			SUM += this.input[i]*this.weights[i];
		}
		if (this.function.equals("Tanh")) {
			return Tanh(SUM);
		}else if (this.function.equals("Relu")) {
			return relu(SUM);
		}else {
			return SUM;

		}
		
	}
	
	
	
	
	public void getWeights() {
		for (int i = 0;i < this.inputsize;i++) {
			System.out.println(this.weights[i]);
		}
	}
}
