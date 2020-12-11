import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JTextField matrixSizeTextFied;
    private JButton generateMatrixButton;
    private JButton genereteCholeskyButton;
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


        generateMatrixButton.addActionListener(e -> {
            matrixSize = Integer.parseInt(matrixSizeTextFied.getText());
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

        genereteCholeskyButton.addActionListener(e -> {
            choleskyMatrixTable.setModel(new DefaultTableModel(
                    new Object [matrixSize][matrixSize],
                    new String [matrixSize]
            ));

            Matrix LMatrix = matrix.cholesky2();
            for(int i = 0; i < matrixSize; i++ ) {
                for(int j = 0; j < matrixSize; j++ ) {
                    choleskyMatrixTable.setValueAt(LMatrix.getMatrixData()[i][j], j, i);
                }
            }
            Matrix LTMatrix = LMatrix.transpose();
            Matrix check = matrix.sub((LMatrix.multiply(LTMatrix)));
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

            DefaultTableModel model = (DefaultTableModel) timeMatrixTable.getModel();
            model.insertRow(model.getRowCount(), new Object[]{counter+1, matrixSize , matrix.getStop() - matrix.getStart()});
            counter++;

            AuxiliaryTable auxTable = new AuxiliaryTable(matrixSize);
            slot1Table.setModel(new DefaultTableModel(
                    new Object [auxTable.getS1Number()][8],
                    new String [] {"Nr.","W1","W2","W3","Op.","Ia3", "Ia2","Ia1"}
            ));
            for(int i = 0; i < auxTable.getS1Number(); i++ ) {
                slot1Table.setValueAt(auxTable.getS1Numbers().get(i), i, 0);
                slot1Table.setValueAt(auxTable.getS1w1().get(i), i, 1);
                slot1Table.setValueAt(auxTable.getS1w2().get(i), i, 2);
                slot1Table.setValueAt(auxTable.getS1w3().get(i), i, 3);
                slot1Table.setValueAt("sqrt", i, 4);
                slot1Table.setValueAt(Arrays.toString(auxTable.getS1ia3().get(i)), i, 5);
                slot1Table.setValueAt(Arrays.toString(auxTable.getS1ia2().get(i)), i, 6);
                slot1Table.setValueAt(Arrays.toString(auxTable.getS1ia1().get(i)), i, 7);
            }

            slot2Table.setModel(new DefaultTableModel(
                    new Object [auxTable.getS2Number()][8],
                    new String [] {"Nr.","W1","W2","W3","Op.","Ia3","Ia2", "Ia1"}
            ));
            for(int i = 0; i < auxTable.getS2Number(); i++ ) {
                slot2Table.setValueAt(auxTable.getS2Numbers().get(i), i, 0);
                slot2Table.setValueAt(auxTable.getS2w1().get(i), i, 1);
                slot2Table.setValueAt(auxTable.getS2w2().get(i), i, 2);
                slot2Table.setValueAt(auxTable.getS2w3().get(i), i, 3);
                slot2Table.setValueAt("/", i, 4);
                slot2Table.setValueAt(Arrays.toString(auxTable.getS2ia3().get(i)), i, 5);
                slot2Table.setValueAt(Arrays.toString(auxTable.getS2ia2().get(i)), i, 6);
                slot2Table.setValueAt(Arrays.toString(auxTable.getS2ia1().get(i)), i, 7);
            }

            slot3Table.setModel(new DefaultTableModel(
                    new Object [auxTable.getS3Number()][8],
                    new String [] {"Nr.","W1","W2","W3","Op","Ia3","Ia2", "Ia1"}
            ));
            for(int i = 0; i < auxTable.getS3Number(); i++ ) {
                slot3Table.setValueAt(auxTable.getS3Numbers().get(i), i, 0);
                slot3Table.setValueAt(auxTable.getS3w1().get(i), i, 1);
                slot3Table.setValueAt(auxTable.getS3w2().get(i), i, 2);
                slot3Table.setValueAt(auxTable.getS3w3().get(i), i, 3);
                slot3Table.setValueAt("-*", i, 4);
                slot3Table.setValueAt(Arrays.toString(auxTable.getS3ia3().get(i)), i, 5);
                slot3Table.setValueAt(Arrays.toString(auxTable.getS3ia2().get(i)), i, 6);
                slot3Table.setValueAt(Arrays.toString(auxTable.getS3ia1().get(i)), i, 7);

            }

            int bigtable = auxTable.getS1Number()+ auxTable.getS2Number()+ auxTable.getS3Number();
            allSlotTable.setModel(new DefaultTableModel(
                    new Object [bigtable][8],
                    new String [] {"Nr.","W1","W2","W3","Op","Ia3", "Ia2","Ia1"}
            ));

            for(int i = 0; i < bigtable; i++ ) {
                for(int j = 0; j < 8; j++) {
                    if (i < auxTable.getS1Number()) {
                        allSlotTable.setValueAt(slot1Table.getValueAt(i, j), i, j);
                    } if (i >= auxTable.getS1Number() && i < auxTable.getS2Number() + auxTable.getS1Number()) {
                        allSlotTable.setValueAt(slot2Table.getValueAt(i-auxTable.getS1Number(), j), i, j);
                    } if (i >= auxTable.getS1Number()+ auxTable.getS2Number()) {
                        allSlotTable.setValueAt(slot3Table.getValueAt(i-(auxTable.getS1Number()+auxTable.getS2Number()), j), i , j);
                    }
                }
            }
        });


        graph.addActionListener(e -> {
        JFrame pictureFrame = new JFrame("Graf zależności informacyjnej");
        pictureFrame.add(new JLabel(new ImageIcon("CholeskyGraph.png")));
        pictureFrame.pack();
        pictureFrame.setVisible(true);
        });


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

    public static void main(String[] args) {
        JFrame frame = new GUI("Cholesky Project");
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
