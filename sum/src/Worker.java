package pack;

public class Worker extends Thread{
  int total = 0;
  int start;
  int end;
  int[] numbers;

  public Worker(int[] numbers, int start, int end){
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

	@Override
	public void run() {
    int length  = numbers.length;
    
    for(int index = start; index < end; index++){
      this.total += numbers[index];
    }
	}

  public int total(){
    return total;
  }
}