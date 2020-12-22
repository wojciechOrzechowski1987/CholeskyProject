import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

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



    private AuxiliaryData auxData;
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


        generateMatrixButton.addActionListener(e -> generateInputMatrix());

        generateCholeskyButton.addActionListener(e -> generateCholeskyData());

        graph.addActionListener(e -> graph());

        sortTable.addActionListener(e -> sortBigTable());
    }

    private void generateInputMatrix(){
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

    private void generateCholeskyData() {
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

       auxData = new AuxiliaryData(matrixSize);

        populateSlotTables(auxData, slot1Table, slot2Table, slot3Table);
        populateTable(auxData.getFullApexList(), auxData.getFullApexNumbers(), allSlotTable);
    }

    private void sortBigTable() {
        auxData.sortApexList();
        populateTable(auxData.getFullApexList(), auxData.getFullApexNumbers(), allSlotTable);
        auxData.getFullApexList().setConnections();


       for (int i = 0; i < auxData.getFullApexList().getApexList().size(); i++) {
            System.out.print("Węzeł [" + auxData.getFullApexList().getApexList().get(i).getX() + "," + auxData.getFullApexList().getApexList().get(i).getY() +
                    ","+ auxData.getFullApexList().getApexList().get(i).getZ()  + "] -> ");
            for (int j = 0; j < auxData.getFullApexList().getApexList().get(i).getConnections().size(); j++) {
                System.out.print("[" + auxData.getFullApexList().getApexList().get(i).getConnections().get(j).getX() + "," +
                        auxData.getFullApexList().getApexList().get(i).getConnections().get(j).getY() + "," +
                        auxData.getFullApexList().getApexList().get(i).getConnections().get(j).getZ() + "]");
            }
            System.out.println();
        }
    }

    private void populateTable(Matrix data, JTable table) {
        for(int i = 0; i < matrixSize; i++ ) {
            for(int j = 0; j < matrixSize; j++ ) {
                table.setValueAt(data.getMatrixData()[i][j], i, j);
            }
        }
    }

    private void populateTable(ApexList apexList, ArrayList<Integer> numbers, JTable s1) {
        ((DefaultTableModel) s1.getModel()).getDataVector().removeAllElements();
        for(int i = 0; i < apexList.getApexList().size(); i++ ) {
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
        pictureFrame.add(new JLabel(new ImageIcon("CholeskyGraph.png")));
        pictureFrame.pack();
        pictureFrame.setVisible(true);
    }
}
