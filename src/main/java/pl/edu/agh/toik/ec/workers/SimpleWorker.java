package pl.edu.agh.toik.ec.workers;

public class SimpleWorker implements Worker {

    private String name;
    private Integer steps;

    public SimpleWorker(String name, Integer steps) {
        this.name = name;
        this.steps = steps;
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
