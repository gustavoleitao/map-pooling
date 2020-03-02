package br.com.logique.map.pooling;

public class Job implements Runnable {

    private String jobName;

    public Job(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("Job:" + jobName);
        try {
            Thread.sleep(1000); // to simulate actual execution time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}