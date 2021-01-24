import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GUI extends JFrame {

    private JPanel mainPanel;
    private JTextField matrixSizeTextField;
    private JButton generateMatrixButton;
    private JButton generateCholeskyButton;
    private JTable mainMatrixTable;
    private JTable choleskyMatrixTable;
    private JTable timeMatrixTable;
    private JLabel checkLabel;
    private JTable slot2Table;
    private JTable slot3Table;
    private JTable slot1Table;
    private JComboBox<String> matrixType;
    private JButton graph;
    private JTable allSlotTable;
    private JButton sortTable;
    private Matrix matrix;
    private JTable apexTable;
    private JTable s1EpTable;
    private JLabel s1takt;
    private JLabel s2takt;
    private JLabel s3takt;
    private final Matrix F1;
    private final Matrix F2;
    private final Matrix F3;
    private JTable s1Fmatrix;
    private JTable s2EpTable;
    private JTable s3EpTable;
    private JTable s2Fmatrix;
    private JTable s3Fmatrix;
    private JLabel s1pic;
    private JLabel s2pic;
    private JLabel s3pic;
    private JLabel s1Mpath;
    private JLabel s2Mpath;
    private JLabel s3Mpath;
    private JLabel s3Time;
    private JLabel s2Time;
    private JLabel s1Time;

    private AuxiliaryData auxData;
    private int matrixSize;
    private int counter = 0;

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1280, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        timeMatrixTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Nr Pomiaru", "N :", "Czas w [ns]"}
        ));
        slot1Table.setModel(new DefaultTableModel(
                null,
                new String[]{"Nr.", "W1", "W2", "W3", "Op.", "Ia3", "Ia2", "Ia1"}
        ));
        slot2Table.setModel(new DefaultTableModel(
                null,
                new String[]{"Nr.", "W1", "W2", "W3", "Op.", "Ia3", "Ia2", "Ia1"}
        ));
        slot3Table.setModel(new DefaultTableModel(
                null,
                new String[]{"Nr.", "W1", "W2", "W3", "Op.", "Ia3", "Ia2", "Ia1"}
        ));
        allSlotTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Nr.", "W1", "W2", "W3", "Op.", "Ia3", "Ia2", "Ia1"}
        ));

        apexTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Węzeł", "Połączenie 1", "Połączenie 2", "Połączenie 3"}
        ));


        String[] epColumns = new String[]{"EP1", "EP2", "Takt", "Operacja", "Wezły"};

       s1EpTable.setModel(new DefaultTableModel(null,epColumns) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        });

        s2EpTable.setModel(new DefaultTableModel(null,epColumns) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        });

        s3EpTable.setModel(new DefaultTableModel(null,epColumns) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        });

        F1 = new Matrix(2,3,new double[][] {{1, 0, 0}, {0, 1, 3}});
        F2 = new Matrix(3,3,new double[][] {{1, 0, 1}, {-1, 0, 0}, {1, 1, 1}});
        F3 = new Matrix(3,3,new double[][] {{1, 1, -1}, {-1, 1, -1}, {0, 1, 0}});



        generateMatrixButton.addActionListener(e -> generateInputMatrix());

        generateCholeskyButton.addActionListener(e -> generateCholeskyData());

        graph.addActionListener(e -> graph());

        sortTable.addActionListener(e -> sortBigTable());

        /*Matrix D = new Matrix(3, new double [][]{{1,0,0},{0,1,0},{0,0,1}});
        Matrix D1 = new Matrix (3,1, new double [][] {{1},{0},{0}});
        Matrix D2 = new Matrix (3,1, new double [][] {{0},{1},{0}});
        Matrix D3 = new Matrix (3,1, new double [][] {{0},{0},{1}});
        Matrix Fs11 = new Matrix (1,3, new double [][] {{1,1,1}});
        Matrix Fs21 = new Matrix(2,3, new double[][] {{1,1,-1},{-1,0,-1}});
        Matrix Fs22 = new Matrix(2,3, new double[][] {{1,1,1},{1,1,1}});
        Matrix Ft = new Matrix (1,3, new double[][] {{1,1,1}});*/

        /*System.out.println("Matrix D");
        D.printMatrix();
        System.out.println("Matrix Fs11");
        Fs11.multiply(D1).printMatrix();
        System.out.println("Marix Fs11");
        Fs11.multiply(D2).printMatrix();
        System.out.println("Marix Fs11");
        Fs11.multiply(D3).printMatrix();

        System.out.println("Marix Fs21");
        Fs21.multiply(D1).printMatrix();
        System.out.println("Marix Fs21");
        Fs21.multiply(D2).printMatrix();
        System.out.println("Marix Fs21");
        Fs21.multiply(D3).printMatrix();
        System.out.println("Marix FS");
        Fs21.multiply(D).printMatrix();
        System.out.println("Marix FT");
        Ft.multiply(D).printMatrix();

        System.out.println("Matrix F");
        F.multiply(D).printMatrix();


        System.out.println("Marix Fs22");
        Fs22.multiply(D1).printMatrix();
        System.out.println("Marix Fs22");
        Fs22.multiply(D2).printMatrix();
        System.out.println("Marix Fs22");
        Fs22.multiply(D2).printMatrix();

        System.out.println("Marix Ft");
        Ft.multiply(D1).printMatrix();
        System.out.println("Marix Ft");
        Ft.multiply(D2).printMatrix();
        System.out.println("Marix Ft");
        Ft.multiply(D3).printMatrix();*/

    }

    private void generateInputMatrix() {
        sortTable.setEnabled(true);
        matrixSize = Integer.parseInt(matrixSizeTextField.getText());
        boolean identity;
        identity = matrixType.getSelectedIndex() == 0;
        matrix = new Matrix(matrixSize, identity);
        mainMatrixTable.setModel(new DefaultTableModel(
                new Object[matrixSize][matrixSize],
                new String[matrixSize]
        ));
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                mainMatrixTable.setValueAt(matrix.getMatrixData()[i][j], i, j);
            }
        }
    }

    private void checkInputData(Matrix data) {
        Matrix LTMatrix = data.transpose();
        Matrix check = matrix.sub((data.multiply(LTMatrix)));
        int sum = 0;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                sum += sum + check.getMatrixData()[i][j];
            }
        }
        if (sum == 0) {
            checkLabel.setText("Weryfikacja (A=L*LT): OK");
        } else {
            checkLabel.setText("Weryfikacja (A=L*LT): NOK");
        }
    }

    private void generateCholeskyData() {
        choleskyMatrixTable.setModel(new DefaultTableModel(
                new Object[matrixSize][matrixSize],
                new String[matrixSize]
        ));

        Matrix LMatrix = matrix.cholesky();
        populateTable(LMatrix, choleskyMatrixTable);

        checkInputData(LMatrix);

        DefaultTableModel model = (DefaultTableModel) timeMatrixTable.getModel();
        model.insertRow(model.getRowCount(), new Object[]{counter + 1, matrixSize, matrix.getStop() - matrix.getStart()});
        counter++;

        auxData = new AuxiliaryData(matrixSize);

        populateSlotTables(auxData, slot1Table, slot2Table, slot3Table);
        populateTable(auxData.getFullApexList(), auxData.getFullApexNumbers(), allSlotTable);
    }

    private void sortBigTable() {
        sortTable.setEnabled(false);
        auxData.sortApexList();
        populateTable(auxData.getFullApexList(), auxData.getFullApexNumbers(), allSlotTable);
        auxData.getFullApexList().setConnections();
        populateApexConnections(auxData.getFullApexList(), apexTable);
        populateEp(auxData.getFullApexList(), s1EpTable, s1Fmatrix, F1, s1takt, s1Mpath, s1Time, s1pic, "structure1D.png");
        populateEp(auxData.getFullApexList(), s2EpTable, s2Fmatrix, F2, s2takt, s2Mpath, s2Time, s2pic,  "structure2D1.png");
        populateEp(auxData.getFullApexList(), s3EpTable, s3Fmatrix, F3, s3takt, s3Mpath, s3Time, s3pic,  "structure2D2.jpeg");

    }

    private void populateTable(Matrix data, JTable table) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                table.setValueAt(data.getMatrixData()[i][j], i, j);
            }
        }
    }

    private void populateTable(ApexList apexList, ArrayList<Integer> numbers, JTable s1) {
        ((DefaultTableModel) s1.getModel()).getDataVector().removeAllElements();
        for (int i = 0; i < apexList.getApexList().size(); i++) {
            ((DefaultTableModel) s1.getModel()).insertRow(s1.getRowCount(), new Object[]{
                    numbers.get(i),
                    apexList.getApexList().get(i).getX(),
                    apexList.getApexList().get(i).getY(),
                    apexList.getApexList().get(i).getZ(),
                    apexList.getApexList().get(i).getOperation(),
                    Arrays.toString(apexList.getApexList().get(i).getIa3()),
                    Arrays.toString(apexList.getApexList().get(i).getIa2()),
                    Arrays.toString(apexList.getApexList().get(i).getIa())
            });
        }
    }

    private void populateSlotTables(AuxiliaryData data, JTable s1, JTable s2, JTable s3) {
        ((DefaultTableModel) s1.getModel()).getDataVector().removeAllElements();
        ((DefaultTableModel) s2.getModel()).getDataVector().removeAllElements();
        ((DefaultTableModel) s3.getModel()).getDataVector().removeAllElements();
        populateTable(data.getS1ApexList(), data.getS1Numbers(), s1);
        populateTable(data.getS2ApexList(), data.getS2Numbers(), s2);
        populateTable(data.getS3ApexList(), data.getS3Numbers(), s3);
    }

    private void graph() {
        JFrame pictureFrame = new JFrame("Graf zależności informacyjnej");
        pictureFrame.add(new JLabel(new ImageIcon(this.getClass().getResource("CholeskyGraph.png"))));
        pictureFrame.pack();
        pictureFrame.setVisible(true);
    }

    private void populateApexConnections(ApexList apexList, JTable table) {
        ((DefaultTableModel) table.getModel()).getDataVector().removeAllElements();
        for (int i = 0; i < apexList.getApexList().size(); i++) {
            ((DefaultTableModel) table.getModel()).insertRow(table.getRowCount(), new Object[]{
                    "[" + apexList.getApexList().get(i).getX() + "," + apexList.getApexList().get(i).getY() + "," + apexList.getApexList().get(i).getZ() + "]",
            });
        }
        for (int i = 0; i < apexList.getApexList().size(); i++) {
            for (int j = 0; j < apexList.getApexList().get(i).getConnections().size(); j++) {
                table.setValueAt(Arrays.toString(apexList.getApexList().get(i).getConnections().get(j).getCord()),i,j+1);
            }
        }
    }

    private void populateEp(ApexList apexList, JTable table, JTable table2, Matrix matrix, JLabel taktLabel, JLabel mpath, JLabel time, JLabel picLabel, String pic) {
        ((DefaultTableModel) table.getModel()).getDataVector().removeAllElements();
        double min=0, max=0;
        for (int i = 0; i < apexList.getApexList().size(); i++) {
            apexList.getApexList().get(i).setK(matrix);
            Integer[] eps = apexList.getApexList().get(i).getEp();
            ((DefaultTableModel) table.getModel()).insertRow(table.getRowCount(), new Object[]{
                    eps[0],
                    eps[1],
                    apexList.getApexList().get(i).getTakt(),
                    apexList.getApexList().get(i).getOperation(),
                    "[" + apexList.getApexList().get(i).getX() + "," + apexList.getApexList().get(i).getY() + "," + apexList.getApexList().get(i).getZ() + "]"
            });

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
            table.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
            sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();
            table.getTableHeader().setEnabled(false);



            min = apexList.getApexList().get(0).getTakt();
            max = apexList.getApexList().get(0).getTakt();
            for (Apex apex : apexList.getApexList()) {
                if (min > apex.getTakt()) {
                    min = apex.getTakt();
                }
                if (max < apex.getTakt()) {
                    max = apex.getTakt();
                }
            }




        }
        taktLabel.setText("F[takty] = Tmax - Tmin + 1 = " + (int)max + " - " + (int)min + " + 1 = " + (int)(max-min+1));
        if (matrix == F1) {
            mpath.setText("Mpath = N = " + this.matrix.getRow());
            double value = ((max-min+1)+(24*this.matrix.getRow()))/354000000;
            System.out.println(value);
            time.setText( "T[s]=(Takt+MPlatency)/Fmax = " + (((max-min+1)+(24*this.matrix.getRow()))/354000000) + "[s]");
            //time.setText(""+(8+(24*this.matrix.getRow())/354));

        } else {
            mpath.setText("Mpath = 2N - 1 = " + (-1+2*this.matrix.getRow()));
            double value = ((max-min+1)+(24*(-1+2*this.matrix.getRow())))/354000000;
            System.out.println(value);
            time.setText( "T[s]=(Takt+MPlatency)/Fmax = " + (((max-min+1)+(24*(-1+2*this.matrix.getRow())))/354000000)+ "[s]");
            //time.setText( ""+(8+(24*(-1+2*this.matrix.getRow()))/354000000));
        }


            table2.setModel(new DefaultTableModel(
                    new Object[3][3],
                    new String[3]
            ));

            for (int i = 0; i < matrix.getRow(); i++) {
                for (int j = 0; j < matrix.getColumn(); j++) {
                    table2.setValueAt(matrix.getMatrixData()[i][j], i, j);
                }
            }

            picLabel.setIcon(new ImageIcon(this.getClass().getResource(pic)));
    }


}
