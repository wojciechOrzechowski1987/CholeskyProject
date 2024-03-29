import java.util.ArrayList;

public class Apex {

    private final int x;
    private final int y;
    private final int z;
    private final Integer[] cord;
    private String operation;
    private Integer[] ia;
    private Integer[] ia2;
    private Integer[] ia3;
    private Integer[] ep;
    private int takt;
    private final ArrayList<Apex> connections;

    public Apex(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.cord = new Integer[]{x, y, z};
        this.connections = new ArrayList<>();
    }

    public Integer[] getEp() {
        return ep;
    }

    public void setK(Matrix A) {
        Matrix data = A.multiply(new Matrix(3, 1, new double[][]{{this.getX()}, {this.getY()}, {this.getZ()}}));
        if(A.getRow()==2) {
            this.ep = new Integer[]{(int) data.getMatrixData(0, 0), (int) data.getMatrixData(0, 0)};
        } else {
            this.ep = new Integer[]{(int) data.getMatrixData(0, 0), (int) data.getMatrixData(1, 0)};
        }
        this.takt = (int)data.getMatrixData(data.getMatrixData().length-1,0);

    }

    public int getTakt() {
        return takt;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
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
        this.ia2 = new Integer[]{i1, i2};
    }

    public void setIa2Null(){
        this.ia2 = null;
    }

    public Integer[] getIa3() {
        return ia3;
    }

    public void setIa3(int i1, int i2) {
        this.ia3 = new Integer[]{i1, i2};
    }

    public void setIa3Null(){
        this.ia3 = null;
    }

    public void addConnection(Apex apex){
        connections.add(apex);
    }

    public ArrayList<Apex> getConnections() {
        return connections;
    }

    public Integer[] getCord() {
        return cord;
    }

}
