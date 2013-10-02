public class Random {
  private int _max;

  public Random(int max) { _max = max; }
  public int get() { return (int) (Math.random() * _max); }

  public static void main(String[] args) {
    Random r = new Random(10);

    for (int i = 0; i < 5; i++)
      System.out.println(r.get());
  }
};
