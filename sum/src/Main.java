import pack.RandomInt;
import pack.Sequential;
import pack.Parallel;

public class Main {
  public static void main(String[] args) {

    int cores  = Runtime.getRuntime().availableProcessors();
    RandomInt numbers = new RandomInt(100000000);

    long begin = System.currentTimeMillis();
    Sequential seq = new Sequential(numbers.get());    
    long end = System.currentTimeMillis();
    System.out.println("Sequential Total: " + seq.total() + " Completed: " + ((end - begin)) + " milliseconds");

    long pbegin = System.currentTimeMillis();
    Parallel par = new Parallel(cores);
    long pend = System.currentTimeMillis();
    System.out.println("Parallel Total: " + par.total(numbers.get()) + " Completed: " + ((pend - pbegin)) + " milliseconds");

  }
}