package comp1110.ass2;

/**
 * Created by Tom on 25/09/2016.
 */
/*
import org.la4j.*;
import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.vector.dense.BasicVector;
import org.la4j.matrix.dense.Basic1DMatrix;


//neural network with one hidden layer

public class NN1HL {

        private final double LEARNING_RATE = 0.002;
        private final double WEIGHT_ESTIMATION_CONSTANT = 0.01;
        private final double BIAS_CONSTANT = 0;
        private double ACCEPTABLE_ERROR_RATE;

        private Matrix inputs;
        private Matrix outputs;
        public Matrix alphas;
        public Matrix betas;
        private Matrix biases;
        private int M; //dimension of the hidden layer
        private int K; //dimension of the output layer
        private int P; //dimension of the input layer

        //generate a value close to 0
        private double generateWeight() {
            return Math.random()/10;
        }

        //activation function
        private double sigmoid(double x) {
            return 1/(1 + Math.exp(-x));
        }
        private double diffSigmoid(double x) {
            return Math.exp(x)/((1+Math.exp(x))*(1+Math.exp(x)));
        }

        public void train(Matrix inputs, Matrix outputs, double ACCEPTABLE_ERROR_RATE) {
            this.inputs = inputs;
            this.outputs = outputs;
            this.alphas = generateAlphas(WEIGHT_ESTIMATION_CONSTANT);
            this.betas = generateBetas(WEIGHT_ESTIMATION_CONSTANT);
            this.biases = generateBiases(BIAS_CONSTANT);
            while(getError()>ACCEPTABLE_ERROR_RATE ) {
                backProp();
                System.out.println(getError());
            }


        }
        private double getError() {
            double errorSum = 0;
            for (int n = 0; n<inputs.rows();n++) {
                for(int k= 0; k<K;k++) {
                    errorSum+=Math.pow((outputs.get(n,k)-feedForward(inputs.getRow(n)).get(k)),2);
                }
            }
            return Math.sqrt(errorSum);
        }



        public NN1HL(int dimensionOfHiddenLayer, int dimensionOfInput, int dimensionOfOutput) {
            this.M = dimensionOfHiddenLayer;
            this.P = dimensionOfInput;
            this.K = dimensionOfOutput;
        }

        private Vector calculatezVector(Vector x) {
            Vector z = new BasicVector(M);
            for(int j = 0; j<M;j++) {
                double toAdd = sigmoid(biases.get(j,0)+alphas.getColumn(j).innerProduct(x));
                z.set(j,toAdd);
            }
            return z;
        }
        private double calculateZ(Vector x,int m) {
            return sigmoid(biases.get(m,0)+alphas.getColumn(m).innerProduct(x));
        }

        private void backProp() {



            //update betas
            for(int k = 0;k<K;k++) {
                for(int m= 0;m<M;m++) {
                    double derivativeSum = 0;

                    for(int n = 0; n<inputs.rows();n++) {

                        double softmaxDifferential = 1; //diffsoftmax(betas.transpose().multiply(calculatezVector(inputs.getColumn(n))),k);

                        derivativeSum += -2*(outputs.get(n,k)-feedForward(inputs.getRow(n)).get(k))*softmaxDifferential*calculateZ(inputs.getRow(n),m);

                    }
                    double prior = betas.get(m,k);
                    betas.set(m,k, prior-LEARNING_RATE*derivativeSum);
                }
            }

            //update alphas
            for(int l = 0;l<P;l++)
                for (int m = 0; m < M; m++) {
                    double derivativeSum = 0;
                    for (int n = 0; n < inputs.rows(); n++) {
                        double innerSum = 0;
                        for (int k = 0; k < K; k++) {
                            double softmaxDifferential = 1; //diffsoftmax(betas.transpose().multiply(calculatezVector(inputs.getColumn(n))),k);
                            innerSum += 2 * (outputs.get(n, k) - feedForward(inputs.getRow(n)).get(k)) * softmaxDifferential * betas.get(m, k) * diffSigmoid(alphas.getColumn(m).innerProduct(inputs.getRow(n))) * inputs.get(n, l);
                        }
                        derivativeSum = derivativeSum - innerSum;
                    }

                    double prior = alphas.get(l, m);
                    alphas.set(l, m, prior - LEARNING_RATE * derivativeSum);

                }




        }

        public Vector feedForward(Vector x) {

            Vector Z = new BasicVector(M);

            for(int m = 0; m<M;m++) {
                double z;
                //System.out.println(x);
                z = sigmoid(biases.get(m,0)+alphas.getColumn(m).innerProduct(x)); //
                Z.set(m,z);
                //System.out.println("the value of Z is "+Z);
            }

            Vector T = new BasicVector(K);

            for(int k = 0; k<K;k++) {
                double t;
                t = biases.get(k,1)+betas.getColumn(k).innerProduct(Z);
                T.set(k,t);
            }
            //using the identity function here, but this code may become useful
            Vector Y = new BasicVector(K);
            for(int k=0;k<K;k++) {
                Y.set(k,T.get(k));
            }

            return Y;

        }

        private Matrix generateBiases(double cons) {
            Matrix toReturn = new Basic1DMatrix(Math.max(M,K),2);
            for(int i = 0;i<M;i++) {
                toReturn.set(i,0,cons);
            }
            for(int i = 0;i<K;i++) {
                toReturn.set(i,1,cons);
            }
            return toReturn;
        }

        private Matrix generateAlphas(double cons) {
            //generate M columns of alphas
            Matrix toReturn = new Basic1DMatrix(P,M);

            for(int i = 0; i<M;i++) {
                for(int j = 0; j<P; j++) {
                    toReturn.set(j,i,generateWeight());
                }
            }
            return toReturn;
        }

        private Matrix generateBetas(double cons) {
            //generate K columns of betas
            Matrix toReturn = new Basic1DMatrix(M,K);
            for(int i = 0; i<K;i++) {
                for(int j = 0; j<M; j++) {
                    toReturn.set(j,i,generateWeight());
                }
            }
            return toReturn;
        }

        private double softmax(Vector T, int k) {
            double sum = 0;
            for(int i =0;i<K;i++) {
                sum += Math.exp(T.get(i));
            }
            return Math.exp(T.get(k))/sum;
        }
        private double diffsoftmax(Vector T, int k) {
            double sum = 0;
            for(int i =0;i<K;i++) {
                if(!(i==k)) {
                    System.out.println(sum);
                    sum += Math.exp(T.get(i));
                }
            }
            return softmax(T,k)*sum;

        }




    }


*/