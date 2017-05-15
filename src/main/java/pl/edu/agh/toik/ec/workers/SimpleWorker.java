package pl.edu.agh.toik.ec.workers;

import pl.edu.agh.toik.ec.topology.Topology;

public class SimpleWorker implements Worker {

    private String name;
    private Integer steps;
    private Topology topology;

    public SimpleWorker(String name, Integer steps, Topology topology) {
        this.name = name;
        this.steps = steps;
        this.topology = topology;
    }

    @Override
    public void start() {
        System.out.println("SimpleWorker " + name + " started");
    }

    @Override
    public boolean isActive() {
        steps = steps - 1;
        return steps >= 0;
    }

    @Override
    public void step() {
        System.out.println("SimpleWorker " + name + " step");
    }

}
