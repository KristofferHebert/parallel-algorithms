package pack;
import pack.Worker;

public class Parallel {
    Worker[] totals;
    int cores;
    int total = 0;

    public Parallel(int cores) {
        this.totals = new Worker[cores];
        this.cores = cores;
    }

    public int total(int[] numbers) {
        try {

            int section = numbers.length / cores;

            for (int index = 0; index < cores; index++) {
                totals[index] = new Worker(numbers, index * section, section * (index + 1));
                totals[index].start();
            }

            for (int index = 0; index < totals.length; index++) {
                totals[index].join();
            }

            for (int index = 0; index < totals.length; index++) {
                total += totals[index].total();
            }
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
        return total;


    }
}