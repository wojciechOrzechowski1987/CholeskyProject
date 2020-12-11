import java.util.ArrayList;

public class AuxiliaryTable {

    private int s1Number= 0, s2Number =0, s3Number = 0;
    private final ArrayList<Integer> s1Numbers = new ArrayList<>();
    private final ArrayList<Integer> s1w1 = new ArrayList<>();
    private final ArrayList<Integer> s1w2 = new ArrayList<>();
    private final ArrayList<Integer> s1w3 = new ArrayList<>();
    private final ArrayList<Integer[]> s1ia1 = new ArrayList<>();
    //private ArrayList<Integer[]> s1ia2 = new ArrayList<>();
    //private ArrayList<Integer[]> s1im = new ArrayList<>();

    private final ArrayList<Integer> s2Numbers = new ArrayList<>();
    private final ArrayList<Integer> s2w1 = new ArrayList<>();
    private final ArrayList<Integer> s2w2 = new ArrayList<>();
    private final ArrayList<Integer> s2w3 = new ArrayList<>();
    private final ArrayList<Integer[]> s2ia1 = new ArrayList<>();
    private final ArrayList<Integer[]> s2ia2 = new ArrayList<>();
    //private ArrayList<Integer[]> s2im = new ArrayList<>();

    private final ArrayList<Integer> s3Numbers = new ArrayList<>();
    private final ArrayList<Integer> s3w1 = new ArrayList<>();
    private final ArrayList<Integer> s3w2 = new ArrayList<>();
    private final ArrayList<Integer> s3w3 = new ArrayList<>();
    private final ArrayList<Integer[]> s3ia1 = new ArrayList<>();
    private final ArrayList<Integer[]> s3ia2 = new ArrayList<>();
    private final ArrayList<Integer[]> s3im = new ArrayList<>();

    public AuxiliaryTable(int n) {
        slot1(n);
        slot2(n);
        slot3(n);
        //slotTest(n);
    }
    
    private void slot1(int n) {
        for (int i1 = 0; i1 < n; i1++) {
                s1Number += 1;
                s1Numbers.add(s1Number);
                s1w1.add(i1+1);
                s1w2.add(i1+1);
                s1w3.add(i1+1);
                s1ia1.add(new Integer[]{i1+1,i1+1});
                }
    }

    private void slot2(int n) {
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2= i1+1; i2 < n; i2++) {
                    s2Number += 1;
                    s2Numbers.add(s2Number);
                    s2w1.add(i1+1);
                    s2w2.add(i2+1);
                    s2w3.add(i1+1);
                    s2ia1.add(new Integer[]{i2+1,i1+1});
                    s2ia2.add(new Integer[]{i1+1,i1+1});
            }
        }
    }

    private void slot3(int n) {
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2= i1+1; i2 < n; i2++) {
                for (int i3 =i1 + 1; i3 <= i2; i3++) {
                    s3Number += 1;
                    s3Numbers.add(s3Number);
                    s3w1.add(i1+1);
                    s3w2.add(i2+1);
                    s3w3.add(i3+1);
                    s3ia1.add(new Integer[]{i2+1,i3+1});
                    s3ia2.add(new Integer[]{i3+1,i1+1});
                    s3im.add(new Integer[]{i2+1,i1+1});
                }
            }
        }
    }

    /*private void slotTest(int n) {
        for (int i1 = 0; i1 < n-1; i1++) {
            for (int i2= i1+1; i2 < n; i2++) {
                for (int i3 =i1 + 1; i3 < n; i3++) {
                    s3Number += 1;
                    System.out.println(s3Number);
                    s3Numbers.add(s3Number);
                    s3w1.add(i1+1);
                    s3w2.add(i2+1);
                    s3w3.add(i3+1);
                    s3ia1.add(new Integer[]{i2+1,i3+1});
                    s3ia2.add(new Integer[]{i1+1,i3+1});
                    s3im.add(new Integer[]{i2+1,i1+1});
                }
            }
        }
    }*/
    
    /*private void printArray(ArrayList<Integer[]> a) {
        for(Integer[] array : a) {
            System.out.print("[");
            for(int i = 0 ; i < array.length; i++){
                System.out.print(array[i]);
                if(i==0) { 
                    System.out.print(",");
                }
            }
            System.out.print("]");
        }
    }*/

    public int getS1Number() {
        return s1Number;
    }

    public int getS2Number() {
        return s2Number;
    }

    public int getS3Number() {
        return s3Number;
    }

    public ArrayList<Integer> getS1Numbers() {
        return s1Numbers;
    }

    public ArrayList<Integer> getS1w1() {
        return s1w1;
    }

    public ArrayList<Integer> getS1w2() {
        return s1w2;
    }

    public ArrayList<Integer> getS1w3() {
        return s1w3;
    }

    public ArrayList<Integer[]> getS1ia1() {
        return s1ia1;
    }

    public ArrayList<Integer> getS2Numbers() {
        return s2Numbers;
    }

    public ArrayList<Integer> getS2w1() {
        return s2w1;
    }

    public ArrayList<Integer> getS2w2() {
        return s2w2;
    }

    public ArrayList<Integer> getS2w3() {
        return s2w3;
    }

    public ArrayList<Integer[]> getS2ia1() {
        return s2ia1;
    }

    public ArrayList<Integer[]> getS2ia2() {
        return s2ia2;
    }

    public ArrayList<Integer> getS3Numbers() {
        return s3Numbers;
    }

    public ArrayList<Integer> getS3w1() {
        return s3w1;
    }

    public ArrayList<Integer> getS3w2() {
        return s3w2;
    }

    public ArrayList<Integer> getS3w3() {
        return s3w3;
    }

    public ArrayList<Integer[]> getS3ia1() {
        return s3ia1;
    }

    public ArrayList<Integer[]> getS3ia2() {
        return s3ia2;
    }

    public ArrayList<Integer[]> getS3im() {
        return s3im;
    }
}
