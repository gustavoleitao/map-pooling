package br.com.logique.map.pooling;


import java.time.Duration;
import java.util.concurrent.*;

public class PoolingManager {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final BlockingDeque<Runnable> jobs = new LinkedBlockingDeque();

    public void schedule(Job job) {
        jobs.offer(SafeRunnable.of(job));
    }

    public void scheduleFirst(Job job) {
        jobs.offerFirst(SafeRunnable.of(job));
    }

    private Runnable pool(Duration duration) throws InterruptedException {
        return jobs.poll(duration.getSeconds(), TimeUnit.SECONDS);
    }

    public void start() {
        executor.scheduleWithFixedDelay(() -> {
            try {
                Runnable job = jobs.poll(1, TimeUnit.SECONDS);
                job.run();
            } catch (InterruptedException e) {
                System.out.println("Sem nada para fazer agora =)");
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
    }

    public void stop(Duration awaitTime) throws InterruptedException {
        executor.shutdown();
        executor.awaitTermination(awaitTime.getSeconds(), TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        PoolingManager pooling = new PoolingManager();
        pooling.start();

        pooling.schedule(new Job("Baixa prioridade"));

        Thread.sleep(1000 * 1);

        pooling.schedule(new Job("Baixa prioridade"));
        pooling.schedule(new Job("Baixa prioridade"));
        pooling.schedule(new Job("Baixa prioridade"));
        pooling.scheduleFirst(new Job("Alta!!"));

        Thread.sleep(1000 * 10);

        System.out.println("Finalizando...");
        pooling.stop(Duration.ofMinutes(1));
        System.out.println("Finalizado!");


    }

}
