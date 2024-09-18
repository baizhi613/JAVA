package org.example;

/**
 * @author lys612411
 */
public class PascalTriangleThread extends Thread{
    public void printPascalTriangle(){
        int[] prevRow = new int[1];
        prevRow[0] = 1;
        while (true) {
            for (int value : prevRow) {
                System.out.print(value + " ");
            }
            System.out.println();
            int[] nextRow = new int[prevRow.length + 1];
            nextRow[0] = 1;
            nextRow[nextRow.length - 1] = 1;
            for (int i = 1; i < nextRow.length - 1; i++) {
                nextRow[i] = prevRow[i - 1] + prevRow[i];
            }
            prevRow = nextRow;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(){
        printPascalTriangle();
    }
}