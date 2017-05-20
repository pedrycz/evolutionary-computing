package pl.edu.agh.toik.ec.workers;

/**
 * Created by piotrek on 20.05.2017.
 */
public class IterationCondition implements StopCondition {

    private int steps;

    public IterationCondition(int steps){
        this.steps = steps;
    }

    @Override
    public boolean isActive() {
        return steps-- > 0;
    }
}
