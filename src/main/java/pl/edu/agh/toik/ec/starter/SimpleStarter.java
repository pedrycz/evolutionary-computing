package pl.edu.agh.toik.ec.starter;

import pl.edu.agh.toik.ec.workers.Worker;

import java.util.List;

public class SimpleStarter implements Starter {

    private List<Worker> workers;

    public SimpleStarter(List<Worker> workers){
        this.workers = workers;
    }

    @Override
    public void run() {
        for (Worker worker : workers){
            worker.start();
            while (worker.isActive()){
                worker.step();
            }
        }
    }

}
