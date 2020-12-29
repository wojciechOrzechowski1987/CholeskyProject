import java.util.ArrayList;
import java.util.Arrays;

public class ApexList implements Cloneable {

    private final ArrayList<Apex> apexList;

    public ApexList() {
        apexList = new ArrayList<>();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ArrayList<Apex> getApexList() {
        return apexList;
    }

   /* public void printConnections() {
        for (Apex apex : apexList) {
            System.out.print("Węzeł [" + apex.getX() + "," + apex.getY() +
                    "," + apex.getZ() + "] -> ");
            for (int j = 0; j < apex.getConnections().size(); j++) {
                System.out.print("[" + apex.getConnections().get(j).getX() + "," +
                        apex.getConnections().get(j).getY() + "," +
                        apex.getConnections().get(j).getZ() + "]");
            }
            System.out.println();
        }
    }*/

    public void setConnections(){
        int k;
        for (int i = 0; i < apexList.size(); i++) {
            k=i;
            if(apexList.get(i).getIa3()!=null) {
                Integer[] a = Arrays.copyOf(apexList.get(i).getIa3(),apexList.get(i).getIa3().length);
                apexList.get(i).setIa3Null();
                setNullIas(i, a);
            } if(apexList.get(i).getIa2()!=null) {
                Integer[] a = Arrays.copyOf(apexList.get(i).getIa2(),apexList.get(i).getIa2().length);
                apexList.get(i).setIa2Null();
                setNullIas(i, a);
            } if(apexList.get(i).getIa()!=null) {
                Integer[] a = Arrays.copyOf(apexList.get(i).getIa(),apexList.get(i).getIa().length);
                apexList.get(i).setIaNull();
                setNullIas(i, a);
            }
            i=k;
        }
    }

    private void setNullIas(int i, Integer[] a) {
        for (int j = i+1; j < apexList.size(); j++) {
            if (Arrays.equals(a,(apexList.get(j).getIa3()))) {
                apexList.get(i).addConnection(apexList.get(j));
                apexList.get(j).setIa3Null();
                i = j;
            } else if (Arrays.equals(a,(apexList.get(j).getIa2()))) {
                apexList.get(i).addConnection(apexList.get(j));
                apexList.get(j).setIa2Null();
                i = j;
            } else if (Arrays.equals(a,(apexList.get(j).getIa()))) {
                apexList.get(i).addConnection(apexList.get(j));
                apexList.get(j).setIaNull();
                i = j;
            }
        }
    }

    public void addApexList(ApexList apexList) {
        this.apexList.addAll(apexList.getApexList());
    }

}
