import java.util.ArrayList;
import java.util.Arrays;

public class ApexList {

    private final ArrayList<Apex> apexList;

    public ApexList() {
        apexList = new ArrayList<>();
    }

    public ArrayList<Apex> getApexList() {
        return apexList;
    }

   /* public void setConnections(){
        for (Apex apex: apexList) {
            for (Apex apex2: apexList) {
                if(apex!=apex2 && (apex2.getIa()!=null || apex2.getIa2()!=null || apex2.getIa3()!=null)) {
                    if(apex.getIa3()!=null){
                        if(Arrays.equals(apex.getIa3(), apex2.getIa3())) {
                            apex.addConnection(apex2);
                            apex.setIa3Null();
                        } else if(Arrays.equals(apex.getIa3(), apex2.getIa2())) {
                            apex.addConnection(apex2);
                            apex.setIa3Null();
                        } else if(Arrays.equals(apex.getIa3(), apex2.getIa())) {
                            apex.addConnection(apex2);
                            apex.setIa3Null();
                        }
                    }
                    if(apex.getIa2()!=null){
                        if(Arrays.equals(apex.getIa2(), apex2.getIa3())) {
                            apex.addConnection(apex2);
                            apex.setIa2Null();
                        } else if(Arrays.equals(apex.getIa2(), apex2.getIa2())) {
                            apex.addConnection(apex2);
                            apex.setIa2Null();
                        } else if(Arrays.equals(apex.getIa2(), apex2.getIa())){
                            apex.addConnection(apex2);
                            apex.setIa2Null();
                        }
                    }
                    if(apex.getIa()!=null){
                        if(Arrays.equals(apex.getIa(),apex2.getIa3())) {
                            apex.addConnection(apex2);
                            apex.setIaNull();
                        } else if(Arrays.equals(apex.getIa(),apex2.getIa2())) {
                            apex.addConnection(apex2);
                            apex.setIaNull();
                        } else if(Arrays.equals(apex.getIa(),apex2.getIa())) {
                            apex.addConnection(apex2);
                            apex.setIaNull();
                        }
                    }
                    if(apex.getIa() == null && apex.getIa2() == null && apex.getIa3() == null) {
                        break;
                    }
                }
            }
            System.out.print("Węzeł [" +apex.getX() +"," + apex.getY() + "," + apex.getY() + "] -> ");
            for (int j = 0; j < apex.getConnections().size(); j++) {
                System.out.print("[" + apex.getConnections().get(j).getX() + "," +
                        apex.getConnections().get(j).getY() + "," +
                        apex.getConnections().get(j).getZ() + "]");
            }
            System.out.println();
        }
    }*/

    /*public void setConnections(){
        for (Apex apex: apexList) {
            for (Apex apex2: apexList) {
                if(apex!=apex2 && (apex2.getIa()!=null || apex2.getIa2()!=null || apex2.getIa3()!=null)) {
                    if(apex.getIa3()!=null){
                        if(Arrays.equals(apex.getIa3(), apex2.getIa3())) {
                            apex.addConnection(apex2);
                            apex.setIa3Null();
                        } else if(Arrays.equals(apex.getIa3(), apex2.getIa2())) {
                            apex.addConnection(apex2);
                            apex.setIa3Null();
                        } else if(Arrays.equals(apex.getIa3(), apex2.getIa())) {
                            apex.addConnection(apex2);
                            apex.setIa3Null();
                        }
                    }
                    if(apex.getIa2()!=null){
                        if(Arrays.equals(apex.getIa2(), apex2.getIa3())) {
                            apex.addConnection(apex2);
                            apex.setIa2Null();
                        } else if(Arrays.equals(apex.getIa2(), apex2.getIa2())) {
                            apex.addConnection(apex2);
                            apex.setIa2Null();
                        } else if(Arrays.equals(apex.getIa2(), apex2.getIa())){
                            apex.addConnection(apex2);
                            apex.setIa2Null();
                        }
                    }
                    if(apex.getIa()!=null){
                        if(Arrays.equals(apex.getIa(),apex2.getIa3())) {
                            apex.addConnection(apex2);
                            apex.setIaNull();
                        } else if(Arrays.equals(apex.getIa(),apex2.getIa2())) {
                            apex.addConnection(apex2);
                            apex.setIaNull();
                        } else if(Arrays.equals(apex.getIa(),apex2.getIa())) {
                            apex.addConnection(apex2);
                            apex.setIaNull();
                        }
                    }
                    if(apex.getIa() == null && apex.getIa2() == null && apex.getIa3() == null) {
                       break;
                    }
                }
            }
            System.out.print("Węzeł [" +apex.getX() +"," + apex.getY() + "," + apex.getY() + "] -> ");
            for (int j = 0; j < apex.getConnections().size(); j++) {
                System.out.print("[" + apex.getConnections().get(j).getX() + "," +
                        apex.getConnections().get(j).getY() + "," +
                        apex.getConnections().get(j).getZ() + "]");
            }
            System.out.println();
        }
    }*/

    /*public void setConnections(){
        int k;
        for (int i = 0; i < apexList.size(); i++) {
            k=i;
            if(apexList.get(i).getIa3()!=null) {
                for (int j = i+1; j < apexList.size(); j++) {
                    if(Arrays.equals(apexList.get(i).getIa3(),(apexList.get(j).getIa3()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIa3Null();
                        i=j;
                    } else if (Arrays.equals(apexList.get(i).getIa3(),(apexList.get(j).getIa2()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIa3Null();
                        i=j;
                    } else if (Arrays.equals(apexList.get(i).getIa3(),(apexList.get(j).getIa()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIa3Null();
                        i=j;
                    }
                }
            } else if(apexList.get(i).getIa2()!=null) {
                for (int j = i + 1; j < apexList.size(); j++) {
                    if (Arrays.equals(apexList.get(i).getIa2(),(apexList.get(j).getIa3()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIa2Null();
                        i = j;
                    } else if (Arrays.equals(apexList.get(i).getIa2(),(apexList.get(j).getIa2()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIa2Null();
                        i = j;
                    } else if (Arrays.equals(apexList.get(i).getIa2(),(apexList.get(j).getIa()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIa2Null();
                        i = j;
                    }
                }
            } else if(apexList.get(i).getIa()!=null) {
                for (int j = i + 1; j < apexList.size(); j++) {
                    if (Arrays.equals(apexList.get(i).getIa(),(apexList.get(j).getIa3()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIaNull();
                        i=j;
                    } else if (Arrays.equals(apexList.get(i).getIa(),(apexList.get(j).getIa2()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIaNull();
                        i=j;
                    } else if (Arrays.equals(apexList.get(i).getIa(),(apexList.get(j).getIa()))) {
                        apexList.get(i).addConnection(apexList.get(j));
                        apexList.get(i).setIaNull();
                        i=j;
                    }
                }
            }
            i=k;
        }
    }*/

    public void setConnections(){
        int k;
        for (int i = 0; i < apexList.size(); i++) {
            k=i;
            if(apexList.get(i).getIa3()!=null) {
                Integer[] a = Arrays.copyOf(apexList.get(i).getIa3(),apexList.get(i).getIa3().length);
                apexList.get(i).setIa3Null();
                asd(i, a);
            } else if(apexList.get(i).getIa2()!=null) {
                Integer[] a = Arrays.copyOf(apexList.get(i).getIa2(),apexList.get(i).getIa2().length);
                apexList.get(i).setIa2Null();
                asd(i, a);
            } else if(apexList.get(i).getIa()!=null) {
                Integer[] a = Arrays.copyOf(apexList.get(i).getIa(),apexList.get(i).getIa().length);
                apexList.get(i).setIaNull();
                asd(i, a);
            }
            i=k;
        }
    }

    private void asd(int i, Integer[] a) {
        for (int j = i + 1; j < apexList.size(); j++) {
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
