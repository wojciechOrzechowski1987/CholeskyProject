import java.util.ArrayList;

public class ApexList {

    private ArrayList<Apex> apexList;

    public ApexList() {
        apexList = new ArrayList<>();
    }

    public ArrayList<Apex> getApexList() {
        return apexList;
    }

    public void setApexList(ArrayList<Apex> apexList) {
        this.apexList = apexList;
    }


    public void setConnections(){
        for (Apex apex: apexList) {
            for (Apex apexIa: apexList) {
                if(apex!=apexIa){
                    if(apex.getIa()!=null){
                        if(apex.getIa() == apexIa.getIa()) {
                            apex.addConnection(apexIa);
                            apexIa.setIaNull();
                        }
                        if(apex.getIa() == apexIa.getIa2()) {
                            apex.addConnection(apexIa);
                            apexIa.setIa2Null();
                        }
                        if(apex.getIa() == apexIa.getIa3()) {
                            apex.addConnection(apexIa);
                            apexIa.setIa3Null();
                        }
                        apex.setIaNull();
                    }
                    if(apex.getIa2()!=null){
                        if(apex.getIa2() == apexIa.getIa()) {
                            apex.addConnection(apexIa);
                            apexIa.setIaNull();
                        }
                        if(apex.getIa2() == apexIa.getIa2()) {
                            apex.addConnection(apexIa);
                            apexIa.setIa2Null();
                        }
                        if(apex.getIa2() == apexIa.getIa3()){
                            apex.addConnection(apexIa);
                            apexIa.setIa3Null();
                        }
                        apex.setIa2Null();
                    }
                    if(apex.getIa3()!=null){
                        if(apex.getIa3() == apexIa.getIa()) {
                            apex.addConnection(apexIa);
                            apexIa.setIaNull();
                        }
                        if(apex.getIa3() == apexIa.getIa2()) {
                            apex.addConnection(apexIa);
                            apexIa.setIa2Null();
                        }
                        if(apex.getIa3() == apexIa.getIa3()) {
                            apex.addConnection(apexIa);
                            apexIa.setIa3Null();
                        }
                        apex.setIa3Null();
                    }
                }
            }
        }
    }

}
