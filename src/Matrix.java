public class Matrix {
  
    private final int row;
    private final int column;
    private final double[][] matrixData;
    private long start;
    private long stop;

    public void setStart(long start) {
        this.start = start;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }

    public long getStart() {
        return start;
    }

    public long getStop() {
        return stop;
    }

    /*public void setMatrixData(double[][] matrixData) {
        this.matrixData = matrixData;
    }*/

    /*public int getRow() {
        return row;
    }*/

    /*public void setRow(int row) {
        this.row = row;
    }*/

    /*public int getColumn() {
        return column;
    }*/

   /* public void setColumn(int column) {
        this.column = column;
    }*/

    public Matrix(int n, int m) {
        this.row = n;
        this.column = m;
        this.matrixData = new double[n][m];
    }

  /*  public Matrix(int n, int m, double[][] data) {
        this.row = n;
        this.column = m;
        this.matrixData = data;
    }*/

    public double[][] getMatrixData() {
        return matrixData;
    }

/*    public Matrix(int n){ //n - max 29 - przy 30 się nanuje ;)
        this.row = n;
        this.column = n;
        this.matrixData = new double[n][n];


        for (int i = 0; i < n; i ++) {
            this.matrixData[0][i] = 1;
            this.matrixData[i][0] = 1;

        }

        for (int i = 1; i < n; i ++){
            for (int j = 1; j < n; j ++){
                this.matrixData[i][j] = this.matrixData[i-1][j] + this.matrixData[i][j-1];
            }
        }
    }*/

    /*public Matrix(Matrix matrix) {
        this(matrix.getRow(), matrix.getColumn(), matrix.getMatrixData());
    }*/

    public Matrix (int n, boolean x) {
        this.row = n;
        this.column = n;
        this.matrixData = new double[n][n];
        if (x){
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < n; j ++){
                if(i == j) {
                    this.matrixData[i][j] = 1;
                } else
                    this.matrixData[i][j] = 0;
            }
        }
        } else {
            for (int i = 0; i < n; i ++) {
                this.matrixData[0][i] = 1;
                this.matrixData[i][0] = 1;
            }
                for (int i = 1; i < n; i ++){
                for (int j = 1; j < n; j ++){
                    this.matrixData[i][j] = this.matrixData[i-1][j] + this.matrixData[i][j-1];
                }
            }
        }
    }

   /* public Matrix(double[][] matrix) {
        this.row = matrix.length;
        this.column = matrix[0].length;
        this.matrixData = new double[this.row][this.column];
        for (int i = 0; i < this.row; i++) {
            System.arraycopy(matrix[i], 0, this.matrixData[i], 0, this.column);
        }
    }*/

   /* public Matrix add(Matrix B) {
        Matrix A = this;
        Matrix C = new Matrix(A.column, B.row);
        for (int i = 0; i < C.column; i++) {
            for (int j = 0; j < C.row; j++) {
                C.matrixData[i][j] = (A.matrixData[i][j] + B.matrixData[i][j]);
            }
        }
        return C;
    }*/

    public Matrix sub(Matrix B) {
        Matrix A = this;
        Matrix C = new Matrix(A.column, B.row);
        for (int i = 0; i < C.column; i++) {
            for (int j = 0; j < C.row; j++) {
                C.matrixData[i][j] = (A.matrixData[i][j] - B.matrixData[i][j]);
            }
        }
        return C;
    }

    public Matrix multiply(Matrix B) {
        Matrix A = this;
        Matrix C = new Matrix(A.column, B.row);
        for (int i = 0; i < C.column; i++) {
            for (int j = 0; j < C.row; j++) {
                for (int k = 0; k < A.column; k++) {
                    C.matrixData[i][j] += (A.matrixData[i][k] * B.matrixData[k][j]);
                }
            }
        }
        return C;
    }

    public Matrix transpose() {
        Matrix A = new Matrix(this.row, this.column);
        for (int i = 0; i < this.column; i++) {
            for (int j = 0; j < this.row; j++) {
                A.matrixData[j][i] = this.matrixData[i][j];
            }
        }
        //A.print();
        return A;
    }

/*    public Matrix cholesky() {
        Matrix A = new Matrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                if (j == i) {
                    for (int k = 0; k < j; k++)
                        sum += Math.pow(A.matrixData[j][k], 2);
                    A.matrixData[j][j] = Math.sqrt(this.matrixData[j][j] - sum);
                } else {
                    for (int k = 0; k < j; k++) {
                        sum += (A.matrixData[i][k] * A.matrixData[j][k]);
                    }
                    A.matrixData[i][j] = (this.matrixData[i][j] - sum) / A.matrixData[j][j];
                }
            }
        }
        return A;
    }*/

    public Matrix cholesky2() {
        Matrix A = new Matrix(this.row, this.column);
        for (int i = 0; i < this.row; i++) {
            System.arraycopy(this.matrixData[i], 0, A.matrixData[i], 0, this.row);
        }               
        Matrix L = new Matrix(this.row, this.column);
        int i,j,k;
        this.setStart(System.nanoTime());
            for (i = 0; i < this.row; i++) {
            A.matrixData[i][i] = Math.sqrt(A.matrixData[i][i]);
            for (j = i+1; j < this.row; j++) {
                A.matrixData[j][i] = A.matrixData[j][i]/A.matrixData[i][i];
            }
            for (j = i+1; j < this.row; j++) {
                for (k = i+1; k <= j; k++){
                A.matrixData[j][k] = A.matrixData[j][k] - (A.matrixData[j][i]*A.matrixData[k][i]);
                }
            }
        }
        for (i = 0; i < this.row; i++) {
            for (j = 0; j < this.row; j++) {
                if (i>=j) {
                    L.matrixData[i][j]  = A.matrixData[i][j];
                } else {
                    L.matrixData[i][j] = 0;
                }
            }  
        }
        this.setStop(System.nanoTime());
        return L;
    }

   /* public void print() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++)
                System.out.print(this.matrixData[i][j] +" ");
            System.out.println();
        }
    }*/

}
