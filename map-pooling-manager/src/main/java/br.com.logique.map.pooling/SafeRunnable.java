package br.com.logique.map.pooling;

public class SafeRunnable implements Runnable {

    private Runnable runnable;

    private SafeRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public static SafeRunnable of(Runnable runnable){
        return new SafeRunnable(runnable);
    }

    @Override
    public void run() {
        try{
            runnable.run();
        }catch (Throwable e){
            System.out.println("alguma exceção frave ocorreu.");
        }

    }
}
