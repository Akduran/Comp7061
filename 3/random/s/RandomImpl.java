public class RandomImpl extends RandomPOA {
  private int _max;

  public RandomImpl(int max) {
    _max = max; 
  }

  public int get() {  // returns random no between 1 & 10 inclusive
    return 1 + (int) (Math.random() * _max); 
  }
}

