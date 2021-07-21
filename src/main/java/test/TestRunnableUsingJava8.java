package test;

import java.util.concurrent.*;

public class TestRunnableUsingJava8 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        callRunnable();
        callExecutors();
        try {
            callableUsingExecutorsFuture();

            scheduledTask();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Future<String> completableFuture = completableFuture();
        String result =completableFuture.get();

        System.out.println("Rsult = "+result);

    }

    private static Future<String> completableFuture() {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit( ()-> {
           Thread.sleep(500);
           completableFuture.complete("Hello 11");
           return null;
        });
        return completableFuture;
    }

    private static void scheduledTask() throws InterruptedException {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> System.out.println("Milliseconds="+System.nanoTime());
        ScheduledFuture<?> future =executorService.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1330);

        long remainDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay : %sms", remainDelay);
    }

    private static void callableUsingExecutorsFuture() throws ExecutionException, InterruptedException {

        Callable<Integer> task = () -> {
            try{
                TimeUnit.SECONDS.sleep(5);
                return 123;
            } catch (InterruptedException ex) {
                throw new IllegalAccessException("Exception="+ ex);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(task);
        System.out.println("Check isDone="+future.isDone());
        Integer result = future.get();
        System.out.println("Check isDone="+future.isDone());
        System.out.println("result="+result);

    }

    private static void callExecutors() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String tName = Thread.currentThread().getName();
            System.out.println("executorService ="+tName);
        });
    }

    private static void callRunnable() {
        Runnable task =() -> {
            String tName = Thread.currentThread().getName();
            System.out.println("Hello ="+tName);
        };
        task.run();
        Thread t = new Thread(task);
        t.start();
        System.out.println("Done!.");
    }
}
