package neyronika1;

import java.lang.Math;




public class Main {
	public static final int hiddenlayers = 2;
	public static final int perceptronsPerLayer[] = {2,6,6,4};
	public static final int batchSize = 40;
	public static final float learningRate = 0.05f;
	
	
	static perceptron perceptrons[][] = new perceptron[hiddenlayers+2][perceptronsPerLayer[max(perceptronsPerLayer)-1]];
	static float[][] perceptronOutputs = new float[hiddenlayers+2][perceptronsPerLayer[max(perceptronsPerLayer)-1]];
	
	
	public static void updateWeights() {
		for(int i = 0;i < hiddenlayers+2;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].updateWeights();
			}
		}
	}
	public static void updateBatchWeights() {
		for(int i = 0;i < hiddenlayers+2;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].updateBatchWeights();
			}
		}
	}
	
	public static float calculateError(float [] x,float[] rightanswer) {
		float Error = 0;

		for(int i = 0;i < 4;i++) {
			Error += Math.pow((x[i] - rightanswer[i]),2);
		}
		return Error;
	}
	
	public static void backprop(float[] rightanswer) {
		//////////////////////////////////
		//calculate deltas and propagate//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < 4;i++) {//output layer
			perceptrons[hiddenlayers + 1][i].calcDelta(rightanswer[i]);
		}
		
		for(int i = hiddenlayers;i >= 0;i--) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				float[] deltas = new float[perceptronsPerLayer[i+1]];
				float[] weights = new float[perceptronsPerLayer[i+1]];
				for(int o = 0;o < perceptronsPerLayer[i+1];o++) {
					deltas[o] = perceptrons[i+1][o].getDelta();
					weights[o] = perceptrons[i+1][o].getWeights()[j];
				}
				perceptrons[i][j].setNextPerceptronDeltas(deltas);
				perceptrons[i][j].setOutputWeights(weights);
				perceptrons[i][j].calcDelta();
			}
		}


	}
	
	public static float[] passforward(float[] inputs) {
		///////////////////////////////
		//PUT INPUTS AND PASS FORWARD//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < perceptronsPerLayer[0];i++) { //layerinput
			perceptrons[0][i].setInputs(inputs[i]);
			perceptrons[0][i].evaluate();
			perceptronOutputs[0][i] = perceptrons[0][i].getOutput();
		}
		for(int i = 1;i < hiddenlayers+2;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].setInputs(perceptronOutputs[i-1]);
				perceptrons[i][j].evaluate();
				perceptronOutputs[i][j] = perceptrons[i][j].getOutput();
			}
		}
		for(int i = 0;i < 4;i++) {
			perceptronOutputs[hiddenlayers + 1][i] = perceptrons[hiddenlayers + 1][i].getOutput();
		}
		return perceptronOutputs[hiddenlayers + 1];
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		
		//S1_S2.createS1();
		//S1_S2.createS2();
		
		///////////////////////
		//LOAD DATA FROM FILE//
		//////////////////////////////////////////////////////////////////////////
		
		
		float[][] dataFromFile = IO.ReadFromFile("dataset1withExamples.txt", 8000,3);
		float[][] inputs = new float[8000][2];
		float[] rightAnswers = new float[8000];

		
		for(int i = 0;i < 8000;i++) {
			inputs[i][0] =  dataFromFile[i][0];
			inputs[i][1] =  dataFromFile[i][1];

			rightAnswers[i] = dataFromFile[i][2]; 
		}
		

		//////////////////////
		//CREATE PERCEPTRONS//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < perceptronsPerLayer[0];i++) perceptrons[0][i] = new perceptron(1,"Tanh",0,batchSize,learningRate);
		for(int i = 0;i < perceptronsPerLayer[0];i++) perceptrons[0][i].setNextLayerLength(perceptronsPerLayer[1]);
		for(int i = 1;i < hiddenlayers+1;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j] = new perceptron(perceptronsPerLayer[i],"Tanh",1,batchSize,learningRate);
				perceptrons[i][j].setNextLayerLength(perceptronsPerLayer[i+1]);
			}
		}
		for(int i = 0;i < perceptronsPerLayer[hiddenlayers + 1];i++) {
			perceptrons[hiddenlayers+1][i] = new perceptron(perceptronsPerLayer[hiddenlayers],"Sig",1,batchSize,learningRate);
		}
		
		////////////////////////////////
		//calculate right answer array//
		//////////////////////////////////////////////////////////////////////

		float[][] rightanswer = new float[8000][4];
		for(int i = 0;i < 8000;i++) {
			for(int j = 0;j < 4;j++) {
				if(j == rightAnswers[i] - 1) {
					rightanswer[i][j] = 1;
				}else {
					rightanswer[i][j] = 0;
				}
			}
		}
		//////////////////////////////////////////////////////////////////////


		/////////
		//TRAIN//
		//////////////////////////////////////////////////////////////////////
		float prevMSE = 0;
		float MSE = 0;
		int h = 0;
		IO.createFile("Errors.txt");
		IO.ClearFile("Errors.txt");
		
		while(h < 700 || Math.abs(prevMSE - MSE) > 0.02) { //epochs
			prevMSE = MSE;
			h++;
			MSE = 0;
			for(int p = 0;p < 4000/batchSize;p++) {

				for(int batch = 0;batch < batchSize;batch++) {
					
					MSE += calculateError(passforward(inputs[p*batchSize + batch]), rightanswer[p*batchSize + batch]);
					backprop(rightanswer[p*batchSize + batch]);
					updateWeights();
				}
				updateBatchWeights();

			}
			float success = 0; //test success rate
			for(int p = 4000;p < 8000;p++) {
				if((float)max(passforward(inputs[p])) == (float)rightAnswers[p]) {
					success++;
				}
			}
			
			//System.out.println(prevMSE - MSE + " " + h);
			System.out.println(success/4000 + "% "+ h);
			IO.WriteToFile("Errors.txt",success/4000, h);
		}
		
		//////////////////////////////////////////////////////////////////////


		
	    long end = System.currentTimeMillis();
	    
	    System.out.println((end - start) + " ms");

	}
	public static int max(float[] in) {
		float max = 0;
		int ret = 0;
		for(int i = 0;i<in.length;i++) {
			if(in[i] > max) {
				max = in[i];
				ret = i+1;
			}
		}
		return ret;
	}
	public static int max(int[] in) {
		float max = 0;
		int ret = 0;
		for(int i = 0;i<in.length;i++) {
			if(in[i] > max) {
				max = in[i];
				ret = i+1;
			}
		}
		return ret;
	}
	
}



