import java.util.ArrayList;

public class Apex {

    private int x;
    private int y;
    private int z;
    private String operation;
    private Integer[] ia;
    private Integer[] ia2;
    private Integer[] ia3;
    private ArrayList<Apex> connections;

    public Apex() {
        connections = new ArrayList<>();
    }

    public Apex(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        connections = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer[] getIa() {
        return ia;
    }

    public void setIa(int i1, int i2) {
        this.ia = new Integer[]{i1, i2};
    }

    public void setIaNull(){
        this.ia = null;
    }

    public Integer[] getIa2() {
        return ia2;
    }

    public void setIa2(int i1, int i2) {
        this.ia = new Integer[]{i1, i2};
    }

    public void setIa2Null(){
        this.ia2 = null;
    }

    public Integer[] getIa3() {
        return ia3;
    }

    public void setIa3(int i1, int i2) {
        this.ia = new Integer[]{i1, i2};
    }

    public void setIa3Null(){
        this.ia3 = null;
    }

    /*Dodawanie połączeń*/
    public void addConnection(Apex apex){
        connections.add(apex);
    }
    /*Generowanie łuku*/
    public Arc createArc(Apex apex){
        return new Arc(this, apex);
    }

}
