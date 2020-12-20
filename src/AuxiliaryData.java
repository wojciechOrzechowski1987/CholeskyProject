import java.util.ArrayList;

public class AuxiliaryData {

    private final ApexList s1ApexList = new ApexList();
    private final ApexList s2ApexList = new ApexList();
    private final ApexList s3ApexList = new ApexList();
    private final ApexList fullApexList = new ApexList();
    private int s1Number= 0, s2Number =0, s3Number = 0;
    private final ArrayList<Integer> s1Numbers = new ArrayList<>();
    private final ArrayList<Integer> s2Numbers = new ArrayList<>();
    private final ArrayList<Integer> s3Numbers = new ArrayList<>();


    public AuxiliaryData(int n) {
        slot1(n);
        slot2(n);
        slot3(n);
    }

    public void mergeApexLists(){
        fullApexList.addApexList(s1ApexList);
        fullApexList.addApexList(s2ApexList);
        fullApexList.addApexList(s3ApexList);
    }

    public void setConnections() {
        fullApexList.setConnections();
    }
    
    private void slot1(int n) {
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2 = i1; i2 <= i1; i2++) {
                for (int i3 = i1; i3 <= i1; i3++) {
                    s1Number += 1;
                    s1Numbers.add(s1Number);
                    Apex apex = new Apex((i1+1),(i2+1),(i3+1));
                    apex.setIa(apex.getX(), apex.getX());
                    s1ApexList.getApexList().add(apex);
                }
            }
        }
    }

    private void slot2(int n) {
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2 = i1 + 1; i2 < n; i2++) {
                for (int i3 = i1; i3 <= i1; i3++) {
                    s2Number += 1;
                    s2Numbers.add(s2Number);
                    Apex apex = new Apex((i1+1),(i2+1),(i3+1));
                    apex.setIa(apex.getY(), apex.getX());
                    apex.setIa2(apex.getX(), apex.getX());
                    s2ApexList.getApexList().add(apex);
                }
            }
        }
    }

    private void slot3(int n) {
        for (int i1 = 0; i1 < n; i1++) {
            for (int i2= i1+1; i2 < n; i2++) {
                for (int i3 =i1 + 1; i3 <= i2; i3++) {
                    s3Number += 1;
                    s3Numbers.add(s3Number);
                    Apex apex = new Apex((i1+1),(i2+1),(i3+1));
                    apex.setIa(apex.getY(), apex.getZ());
                    apex.setIa2(apex.getY(), apex.getX());
                    apex.setIa3(apex.getZ(), apex.getX());
                    s3ApexList.getApexList().add(apex);
                }
            }
        }
    }

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


    public ArrayList<Integer> getS2Numbers() {
        return s2Numbers;
    }


    public ArrayList<Integer> getS3Numbers() {
        return s3Numbers;
    }

    public ApexList getS1ApexList() {
        return s1ApexList;
    }

    public ApexList getS2ApexList() {
        return s2ApexList;
    }

    public ApexList getS3ApexList() {
        return s3ApexList;
    }

    public ApexList getFullApexList() {
        return fullApexList;
    }
}
