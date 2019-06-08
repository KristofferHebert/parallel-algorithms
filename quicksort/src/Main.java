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

class Worker extends Thread{
  int[] numbers;

  public Worker(int[] numbers){
    this.numbers = numbers;
  }

	@Override
	public void run() {
    this.numbers = new QuickSort(this.numbers).sort();
	}

  public int[] sort(){
    return numbers;
  }
}


class ParallelQuickSort {
    Worker[] numbers;
    int cores;
    int total = 0;

    public int[] convert(ArrayList<Integer> integers)
    {
        int[] result = new int[integers.size()];
        for (int i=0; i < result.length; i++)
        {
            result[i] = integers.get(i).intValue();
        }
        return result;
    }

    public int[] combine(int[] first, int[] last){
        int[] result = new int[first.length + last.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(last, 0, result, first.length, last.length);
        return result;
    }

    int[] sort(){
            int middle[];
            int length = this.numbers.length;
            int result[];

            ArrayList<Integer> left = new ArrayList<Integer>();
            ArrayList<Integer> right = new ArrayList<Integer>();
            
            middle[0] = this.numbers[last];
            
            for(int i = 0; i < length; i++){
              if(this.numbers[i] < middle[0]){
                right.add(this.numbers[i]);
              } else {
                left.add(this.numbers[i]);
              }
            }

            for(int i = 0; i < length; i++){
              if(this.numbers[i] < middle[0]){
                right.add(this.numbers[i]);
              } else {
                left.add(this.numbers[i]);
              }
            }

            int leftArray = convert(left);
            int rightArray = convert(right);

            Worker leftWorker = new Worker(leftArray);
            leftWorker.start();
            Worker rightWorker = new Worker(rightArray);
            rightWorker.start();
            
            leftWorker.join();
            rightWorker.join();

            result = combine(leftWork.sort(), middle);
            result = combine(result, rightWork.sort());

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
	public static void main(String[] args) {
		int[] n = new RandomInt(100).get();
    int cores  = Runtime.getRuntime().availableProcessors();

		// QuickSort numb = new QuickSort(n);
		// int[] numbers = numb.sort();

    ParallelQuickSort pquicksort = new ParallelQuickSort(cores);
    


		int length = numbers.length;
		for (int index = 0; index < length; index++) {
			System.out.println(numbers[index]);
		}
	}
}