package neyronika1;
import java.util.Random;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle error
import java.lang.Math;
import java.lang.reflect.Array;




public class Main {	
	static float[][] c1 = new float[8000][2];
	static float[][] c2 = new float[8000][2];
	static float[][] c3 = new float[8000][2];
	static float[][] c4 = new float[8000][2];
	static int c1size = 0;
	static int c2size = 0;
	static int c3size = 0;
	static int c4size = 0;
	
	
	public static void createS1() {
		
		IO.createFile("dataset1.txt");
		IO.createFile("c1.txt");
		IO.createFile("c2.txt");
		IO.createFile("c3.txt");
		IO.createFile("c4.txt");

	        
	    Random rand = new Random();

		float[][] dataset = new float[8000][2];
	    for(int i = 0;i < 8000;i++) {
	    	dataset[i][0] = rand.nextFloat()*2 - 1;
	        dataset[i][1] = rand.nextFloat()*2 - 1;
	        IO.WriteToFile("dataset1.txt",dataset[i][0],dataset[i][1]);
	    }
	   
	    //katigoriopoihsh


	    for(int i = 0;i < 8000;i++) {
	    	c1[i][0] = -2;
	    	c2[i][0] = -2;
	    	c3[i][0] = -2;
	    	c4[i][0] = -2;
	    }
		
	    for(int i = 0;i < 8000;i++) {
	    	if(Math.pow(dataset[i][0] - 0.5,2) + Math.pow(dataset[i][1] - 0.5,2) < 0.16 ||
	    			Math.pow(dataset[i][0] + 0.5,2) + Math.pow(dataset[i][1] + 0.5,2) < 0.16) {
	    		c1[c1size][0] = dataset[i][0];
	    		c1[c1size][1] = dataset[i][1];
	    		c1size++;
	    	}else if(Math.pow(dataset[i][0] - 0.5,2) + Math.pow(dataset[i][1] + 0.5,2) < 0.16 ||
	    			Math.pow(dataset[i][0] + 0.5,2) + Math.pow(dataset[i][1] - 0.5,2) < 0.16) {
	    		c2[c2size][0] = dataset[i][0];
	    		c2[c2size][1] = dataset[i][1];
	    		c2size++;
	    	}else if((dataset[i][0] > 0 && dataset[i][1] > 0) ||
	    			(dataset[i][0] < 0 && dataset[i][1] < 0)) {
	    		c3[c3size][0] = dataset[i][0];
	    		c3[c3size][1] = dataset[i][1];
	    		c3size++;
	    		
	    	}else {
	    		c4[c4size][0] = dataset[i][0];
	    		c4[c4size][1] = dataset[i][1];
	    		c4size++;
	    	}
	    }

		
	    for(int i = 0;i < 8000;i++) {
	    	if(c1[i][0] != -2.0) {
		    	IO.WriteToFile("c1.txt",c1[i][0],c1[i][1]);
	    	}
	    	if(c2[i][0] != -2.0) {
		    	IO.WriteToFile("c2.txt",c2[i][0],c2[i][1]);
	    	}
	    	if(c3[i][0] != -2.0) {
		    	IO.WriteToFile("c3.txt",c3[i][0],c3[i][1]);
	    	}
	    	if(c4[i][0] != -2.0) {
		    	IO.WriteToFile("c4.txt",c4[i][0],c4[i][1]);
	    	}
	    }
	    
	}
	
	public static void createS2() {
		
		IO.createFile("dataset.txt");

	        
	    Random rand = new Random();
	    


		float[][] dataset = new float[1200][2];
    	//dataset[i][0] = rand.nextFloat() * (maxX - minX) + minX;
	    for(int i = 0;i < 150;i++) { //1
	    	dataset[i][0] = (float) (rand.nextFloat() * (0.75 - 1.25) + 1.25);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0.75 - 1.25) + 1.25);
	    }
	    for(int i = 150;i < 300;i++) { //2
	    	dataset[i][0] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    }
	    for(int i = 300;i < 450;i++) { //3
	    	dataset[i][0] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    }
	    for(int i = 450;i < 600;i++) { //4
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    }
	    for(int i = 600;i < 750;i++) { //5
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    }
	   
	    for(int i = 750;i < 825;i++) { //6
	    	dataset[i][0] = (float) (rand.nextFloat() * (0.6 - 0.8) + 0.8);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.4) + 0.4);
	    }
	    for(int i = 825;i < 900;i++) { //7
	    	dataset[i][0] = (float) (rand.nextFloat() * (0.6 - 0.8) + 0.8);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.6 - 2) + 2);
	    }
	    for(int i = 900;i < 975;i++) { //8
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.2 - 1.4) + 1.4);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.4) + 0.4);
	    }
	    for(int i = 975;i < 1050;i++) { //9
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.2 - 1.4) + 1.4);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.6 - 2) + 2);
	    }
	    for(int i = 1050;i < 1200;i++) { //10
	    	dataset[i][0] = (float) (rand.nextFloat() * (0 - 2) + 2);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 2) + 2);
	    }
	   
	   
	   
		
	    for(int i = 0;i < 1200;i++) {
	    	IO.WriteToFile("dataset.txt",dataset[i][0],dataset[i][1]);
	    }
	}
	
	
	
	public static void createNoise() {
	    int rng = 0;
	    Random rand = new Random();

	    for(int i = 0;i < 8000;i++) {
			if(rand.nextFloat() < 0.1 && c1[i][0] != -2.0) { //c1
				rng = rand.nextInt(3)+1;
				if(rng == 1) {
			    	IO.WriteToFile("c2.txt",c1[i][0],c1[i][1]);
				}else if (rng == 2) {
			    	IO.WriteToFile("c3.txt",c1[i][0],c1[i][1]);
				}else if (rng == 3) {
			    	IO.WriteToFile("c4.txt",c1[i][0],c1[i][1]);
				}
				c1[i][0] = -2;
			}
			if(rand.nextFloat() < 0.1 && c2[i][0] != -2.0) { //c2
				rng = rand.nextInt(3)+1;

				if(rng == 1) {
			    	IO.WriteToFile("c1.txt",c2[i][0],c2[i][1]);
				}else if (rng == 2) {
			    	IO.WriteToFile("c3.txt",c2[i][0],c2[i][1]);
				}else if (rng == 3) {
			    	IO.WriteToFile("c4.txt",c2[i][0],c2[i][1]);
				}
				c2[i][0] = -2;
			}
			if(rand.nextFloat() < 0.1 && c3[i][0] != -2.0) { //c3
				rng = rand.nextInt(3)+1;
				if(rng == 1) {
			    	IO.WriteToFile("c1.txt",c3[i][0],c3[i][1]);
				}else if (rng == 2) {
			    	IO.WriteToFile("c2.txt",c3[i][0],c3[i][1]);
				}else{
			    	IO.WriteToFile("c4.txt",c3[i][0],c3[i][1]);
				}
				c3[i][0] = -2;
			}
			if(rand.nextFloat() < 0.1 && c4[i][0] != -2.0) { //c4
				rng = rand.nextInt(3)+1;
				if(rng == 1) {
			    	IO.WriteToFile("c1.txt",c4[i][0],c4[i][1]);
				}else if (rng == 2) {
			    	IO.WriteToFile("c2.txt",c4[i][0],c4[i][1]);
				}else if (rng == 3) {
			    	IO.WriteToFile("c3.txt",c4[i][0],c4[i][1]);
				}
				c4[i][0] = -2;
			}
	    }
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		int D = 2;
		int H1 = 2;
		int H2 = 2;
		int P = 4;
	
		//createS1();
		//createS2();
		
		
		float[] test = {1f};
//		perceptron P1 = new perceptron(5);
//		P1.setInputs(test);
//		P1.getWeights();
//		System.out.println(P1.evaluate());
		
		
		perceptron layerinput[ ]= new perceptron[D];
		perceptron layerH1[ ]= new perceptron[H1];
		perceptron layerH2[ ]= new perceptron[H2];
		perceptron layeroutput[ ]= new perceptron[P];

		float LIoutputs[] = new float[D];
		float LH1outputs[] = new float[H1];
		float LH2outputs[] = new float[H2];
		float Loutputs[] = new float[P];


		
		for(int i = 0;i < D;i++) layerinput[i] = new perceptron(1,"Tanh");
		for(int i = 0;i < H1;i++) layerH1[i] = new perceptron(Array.getLength(layerinput),"Tanh");
		for(int i = 0;i < H2;i++) layerH2[i] = new perceptron(Array.getLength(layerH1),"Tanh");
		for(int i = 0;i < P;i++) layeroutput[i] = new perceptron(Array.getLength(layerH2),"output");
		

		for(int i = 0;i < D;i++) { //layerinput
			layerinput[i].setInputs(test);
			LIoutputs[i] = layerinput[i].evaluate();
			System.out.println(layerinput[i].evaluate() +  "  Input  " + i );
		}
		for(int i = 0;i < H1;i++) { //h1
			layerH1[i].setInputs(LIoutputs);
			LH1outputs[i] = layerH1[i].evaluate();
			System.out.println(layerH1[i].evaluate() + "  H1  " + i);
		}
		for(int i = 0;i < H2;i++) { //h2
			layerH2[i].setInputs(LH1outputs);
			LH2outputs[i] = layerH2[i].evaluate();
			System.out.println(layerH2[i].evaluate() + "  H2  " + i);
		}
		for(int i = 0;i < P;i++) { //h2
			layeroutput[i].setInputs(LH2outputs);
			Loutputs[i] = layeroutput[i].evaluate();
			System.out.println(layeroutput[i].evaluate() + "  OUT  " + i);
		}
		
		

		
	    long end = System.currentTimeMillis();
	    
	    System.out.println((end - start) + " ms");

	}
}
