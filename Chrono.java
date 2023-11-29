import java.util.function.Runnable;

public class Chrono {

    public static void chrono(Runnable runner) {
        long startTime = System.nanoTime();
        runner.run();
        long delay = System.nanoTime() - startTime;
        System.out.println((delay * 1e-6 + "ms"));
    }
    
}
