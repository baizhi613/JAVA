package org.example;

public class FibonacciThread implements Runnable{
    public void  printFibonacci(){
        long num1 = 0;
        long num2 = 1;
        while (true) {
            System.out.print(num1 + " ");
            long nextNum = num1 + num2;
            num1 = num2;
            num2 = nextNum;
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try{
            printFibonacci();
            waitForSecond();
            waitForYears();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public synchronized void waitForSecond() throws InterruptedException{
        wait(500);
    }

    public synchronized void waitForYears() throws InterruptedException{
        wait();
    }

    public synchronized void notifyNow() throws InterruptedException{
        notify();
    }

}
