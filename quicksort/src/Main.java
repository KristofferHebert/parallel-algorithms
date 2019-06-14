import java.util.Random;
import java.util.ArrayList;

class RandomInt {
    int[] numbers;
    public int[] get() {
        return numbers;
    }
    public RandomInt(int length) {
        this.numbers = new int[length];
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            this.numbers[i] = 1 + random.nextInt(101);
        }
    }
}

class Worker extends Thread {
    int[] numbers;

    public Worker(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        this.numbers = new QuickSort(this.numbers).sort();
    }

    public int[] sort() {
        return numbers;
    }
}


class ParallelQuickSort {
    int[] numbers;

    ParallelQuickSort(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] convert(ArrayList < Integer > integers) {
        int[] result = new int[integers.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = integers.get(i).intValue();
        }
        return result;
    }
    public void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
    public int[] combine(int[] first, int[] last) {
        int[] result = new int[first.length + last.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(last, 0, result, first.length, last.length);
        return result;
    }

    int[] trim(int[] array, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    int[] sort() {
        int length = this.numbers.length;
        int result[];
        int middle = this.numbers[(length / 2)];

        int leftArray[] = new int[length];
        int leftLength = 0;
        int rightArray[] = new int[length];
        int rightLength = 0;

        for (var i = 0; i < length; i++) {
            if (this.numbers[i] >= middle) {
                rightArray[rightLength] = this.numbers[i];
                rightLength++;
            } else {
                leftArray[leftLength] = this.numbers[i];
                leftLength++;
            }
        }

        leftArray = trim(leftArray, leftLength);
        rightArray = trim(rightArray, rightLength);
        Worker leftWorker = new Worker(leftArray);
        leftWorker.start();
        Worker rightWorker = new Worker(rightArray);
        rightWorker.start();

        try {

            leftWorker.join();
            rightWorker.join();

        } catch (InterruptedException error) {
            error.printStackTrace();
        }
        result = combine(leftWorker.sort(), rightWorker.sort());
        return result;

    }
}

class QuickSort {
    int[] numbers;
    public QuickSort(int[] numbers) {
        this.numbers = numbers;
        this.sortNumbers(0, numbers.length - 1);
    }

    int section(int first, int last) {
        int middle = this.numbers[last];
        int index = first - 1;

        for (int start = first; start < last; start++) {
            if (this.numbers[start] <= middle) {
                index++;
                int temporary = this.numbers[index];
                this.numbers[index] = this.numbers[start];
                this.numbers[start] = temporary;
            }
        }

        int temporary = this.numbers[index + 1];
        this.numbers[index + 1] = this.numbers[last];
        this.numbers[last] = temporary;

        return index + 1;
    }

    void sortNumbers(int first, int last) {

        if (first < last) {
            int index = section(first, last);
            sortNumbers(first, index - 1);
            sortNumbers(index + 1, last);
        }
    }

    int[] sort() {
        return numbers;
    }
}

public class Main {
    public static void printArray(int[] numbers) {
        System.out.print('[');
        for (int index = 0; index < numbers.length; index++) {
            System.out.print(numbers[index]);
            if (index < numbers.length - 1) {
                System.out.print(',');
            }
        }
        System.out.println(']');
        System.out.println("");
    }
    public static void main(String[] args) {
        int[] rand = new RandomInt(50).get();
        int[] numbers;

        System.out.println("Unsorted Array");
        printArray(rand);

        System.out.println("Sequential QuickSort");
        QuickSort numb = new QuickSort(rand);
        numbers = numb.sort();
        printArray(numbers);

        System.out.println("Parallel QuickSort");
        ParallelQuickSort pquicksort = new ParallelQuickSort(rand);
        numbers = pquicksort.sort();
        printArray(numbers);

    }
}