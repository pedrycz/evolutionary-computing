package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.topology.Topology;

public class SimpleWorker implements Worker {

    private String name;
    private StopCondition stopCondition;
    private Topology topology;

    // liste kroków (interfejsy), init krok, lista parametrow
    public SimpleWorker(String name, StopCondition stopCondition, Topology topology) {
        this.name = name;
        this.stopCondition = stopCondition;
        this.topology = topology;
    }

    @Override
    public void start() {
        System.out.println("SimpleWorker " + name + " started");
    }

    @Override
    public boolean isActive() {
        return stopCondition.isActive();
    }

    @Override
    public void step() {
        System.out.println("SimpleWorker " + name + " step");
    }

}
