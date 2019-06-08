package pack;
import java.util.Random;

public class RandomInt {
    int[] numbers;
    public int[] get(){
      return numbers;
    }
    public RandomInt(int length){
        this.numbers = new int[length];
        for (int i = 0; i < length; i++){
            Random random = new Random();
            this.numbers[i] = 1 + random.nextInt(101);
        }             
    }    
}