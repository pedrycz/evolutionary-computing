package pl.edu.agh.toik.ec.workers;

public interface Worker {

    void start();

    boolean isActive();

    void step();

}
