package pl.edu.agh.toik.ec.algorithm.constraint;

import pl.edu.agh.toik.ec.algorithm.Individual;

public class BasicConstraintStrategy extends ConstraintStrategy {

    private Double parameterMin = null;
    private Double parameterMax = null;

    @Override
    public void fixIndividual(Individual individual) {
        if (parameterMin == null && parameterMax == null)
            return;
        Double range = null;
        if (parameterMin != null && parameterMax != null)
            range = parameterMax - parameterMin;
        for (int i = 0; i < individual.getParametersSize(); i++) {
            if (parameterMax != null && individual.getParameter(i) > parameterMax) {
                double bounceDistance = range != null ? (individual.getParameter(i) % range)
                        : individual.getParameter(i);
                individual.setParameter(i, 2 * parameterMax - bounceDistance);
            } else if (parameterMin != null && individual.getParameter(i) < parameterMin) {
                double bounceDistance = range != null ? (individual.getParameter(i) % range)
                        : individual.getParameter(i);
                individual.setParameter(i, 2 * parameterMin - bounceDistance);
            }
        }
    }

    public Double getParameterMin() {
        return parameterMin;
    }

    public void setParameterMin(Double parameterMin) {
        this.parameterMin = parameterMin;
    }

    public Double getParameterMax() {
        return parameterMax;
    }

    public void setParameterMax(Double parameterMax) {
        this.parameterMax = parameterMax;
    }

}


