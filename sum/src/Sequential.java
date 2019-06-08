package pack;

public class Sequential {
  int sum = 0;

  public Sequential(int[] numbers){
    int length  = numbers.length;
    
    for(int index = 0; index < length; index++){
      this.sum += numbers[index];
    }
  }
  public int total(){
    return sum;
  }
}