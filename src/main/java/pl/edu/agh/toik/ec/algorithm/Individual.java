package pl.edu.agh.toik.ec.algorithm;

public class Individual {

    double[] parameters;
    double fitness = 0;

    public Individual(int parametersSize){
        parameters = new double[parametersSize];
    }
    
    public void setParameter(int index, double value){
        assert (index >= 0 && index < parameters.length) : "Parameter index out of bounds";
        parameters[index] = value;
    }
    
    public double getParameter(int index){
        assert (index >= 0 && index < parameters.length) : "Parameter index out of bounds";
        return parameters[index];
    }
    
    public int getParametersSize(){
        return parameters.length;
    }
    
    public double getFitness(){
        return fitness;
    }
    
    public void setFitness(double fitness){
        this.fitness = fitness;
    }
    
}
