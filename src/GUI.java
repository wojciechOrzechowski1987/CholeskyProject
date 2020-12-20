import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
    private int matrixSize;
    private int counter = 0;

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1280,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        timeMatrixTable.setModel(new DefaultTableModel(
                null,
                new String [] {"Nr Pomiaru","N :" ,"Czas w [ns]"}
        ));
        slot1Table.setModel(new DefaultTableModel(
                null,
                new String [] {"Nr.","W1","W2","W3","Op.","Ia3", "Ia2","Ia1"}
        ));
        slot2Table.setModel(new DefaultTableModel(
                null,
                new String [] {"Nr.","W1","W2","W3","Op.","Ia3", "Ia2","Ia1"}
        ));
        slot3Table.setModel(new DefaultTableModel(
                null,
                new String [] {"Nr.","W1","W2","W3","Op.","Ia3", "Ia2","Ia1"}
        ));
        allSlotTable.setModel(new DefaultTableModel(
                null,
                new String [] {"Nr.","W1","W2","W3","Op.","Ia3", "Ia2","Ia1"}
        ));


        generateMatrixButton.addActionListener(e -> {
            matrixSize = Integer.parseInt(matrixSizeTextField.getText());
            boolean identity;
            identity = matrixType.getSelectedIndex() == 0;
            matrix = new Matrix(matrixSize, identity);
            mainMatrixTable.setModel(new DefaultTableModel(
                    new Object [matrixSize][matrixSize],
                    new String [matrixSize]
            ));
            for(int i = 0; i < matrixSize; i++ ) {
                for(int j = 0; j < matrixSize; j++ ) {
                    mainMatrixTable.setValueAt(matrix.getMatrixData()[i][j], j, i);
                }
            }
        });

        generateCholeskyButton.addActionListener(e -> {

            choleskyMatrixTable.setModel(new DefaultTableModel(
                    new Object [matrixSize][matrixSize],
                    new String [matrixSize]
            ));

            Matrix LMatrix = matrix.cholesky();
            populateTable(LMatrix, choleskyMatrixTable);

            checkInputData(LMatrix);

            DefaultTableModel model = (DefaultTableModel) timeMatrixTable.getModel();
            model.insertRow(model.getRowCount(), new Object[]{counter+1, matrixSize , matrix.getStop() - matrix.getStart()});
            counter++;

            AuxiliaryData auxData = new AuxiliaryData(matrixSize);
            populateSlotTables(auxData, slot1Table, slot2Table, slot3Table);

            AuxiliaryData bigData = new AuxiliaryData(matrixSize);
            bigData.mergeApexLists();
            populateBigTable(bigData, allSlotTable);
        });

        graph.addActionListener(e -> graph());

        sortTable.addActionListener(e -> {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(allSlotTable.getModel());
            allSlotTable.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            int w1 = 1;
            sortKeys.add(new RowSorter.SortKey(w1, SortOrder.ASCENDING));
            int w2 = 2;
            sortKeys.add(new RowSorter.SortKey(w2, SortOrder.ASCENDING));
            int w3 = 3;
            sortKeys.add(new RowSorter.SortKey(w3, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);
            sorter.sort();
            allSlotTable.getTableHeader().setEnabled(false);
            int bigtable = allSlotTable.getRowCount();
            for(int i = 0; i < bigtable; i++ ) {
                allSlotTable.setValueAt(i+1,i,0);
                allSlotTable.convertRowIndexToModel(i);
            }
        });
    }

    private void checkInputData(Matrix data) {
        Matrix LTMatrix = data.transpose();
        Matrix check = matrix.sub((data.multiply(LTMatrix)));
        int sum = 0;
        for(int i = 0; i < matrixSize; i++ ) {
            for(int j = 0; j < matrixSize; j++ ) {
                sum += sum + check.getMatrixData()[i][j];
            }
        }
        if (sum == 0) {
            checkLabel.setText("Weryfikacja (A=L*LT): OK");
        } else {
            checkLabel.setText("Weryfikacja (A=L*LT): NOK");
        }
    }

    private void populateTable(Matrix data, JTable table) {
        for(int i = 0; i < matrixSize; i++ ) {
            for(int j = 0; j < matrixSize; j++ ) {
                table.setValueAt(data.getMatrixData()[i][j], i, j);
            }
        }
    }

    private void populateTable(ApexList apexList, ArrayList<Integer> numbers, int number, String operation, JTable s1) {
        for(int i = 0; i < number; i++ ) {
            ((DefaultTableModel) s1.getModel()).insertRow(s1.getRowCount(), new Object[]{
                    numbers.get(i),
                    apexList.getApexList().get(i).getX(),
                    apexList.getApexList().get(i).getY(),
                    apexList.getApexList().get(i).getZ(),
                    operation,
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
        populateTable(data.getS1ApexList(), data.getS1Numbers(), data.getS1Number(), "sqrt", s1);
        populateTable(data.getS2ApexList(), data.getS2Numbers(), data.getS2Number(), "/", s2);
        populateTable(data.getS3ApexList(), data.getS3Numbers(), data.getS3Number(), "-*", s3);
    }

    private void populateBigTable(AuxiliaryData data, JTable allSlotTable) {
        int bigtable = data.getS1Number()+ data.getS2Number()+ data.getS3Number();
        allSlotTable.setModel(new DefaultTableModel(
                new Object [bigtable][8],
                new String [] {"Nr.","W1","W2","W3","Op","Ia3", "Ia2","Ia1"}
        ));

        for(int i = 0; i < bigtable; i++ ) {
            for(int j = 0; j < 8; j++) {
                if (i < data.getS1Number()) {
                    allSlotTable.setValueAt(slot1Table.getValueAt(i, j), i, j);
                } if (i >= data.getS1Number() && i < data.getS2Number() + data.getS1Number()) {
                    allSlotTable.setValueAt(slot2Table.getValueAt(i-data.getS1Number(), j), i, j);
                } if (i >= data.getS1Number()+ data.getS2Number()) {
                    allSlotTable.setValueAt(slot3Table.getValueAt(i-(data.getS1Number()+data.getS2Number()), j), i , j);
                }
            }
        }
    }

    private void graph() {
        JFrame pictureFrame = new JFrame("Graf zależności informacyjnej");
        pictureFrame.add(new JLabel(new ImageIcon("CholeskyGraph.png")));
        pictureFrame.pack();
        pictureFrame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new GUI("Cholesky Project");
        frame.setResizable(false);
        frame.setVisible(true);
    }



}
