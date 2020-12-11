import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

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
    private Matrix matrix;
    private int matrixSize;
    private int counter = 0;

    public GUI(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1280,600);
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
                    new Object [auxTable.getS1Number()][5],
                    new String [] {"Nr.","W1","W2","W3","ia1"}
            ));
            for(int i = 0; i < auxTable.getS1Number(); i++ ) {
                slot1Table.setValueAt(auxTable.getS1Numbers().get(i), i, 0);
                slot1Table.setValueAt(auxTable.getS1w1().get(i), i, 1);
                slot1Table.setValueAt(auxTable.getS1w2().get(i), i, 2);
                slot1Table.setValueAt(auxTable.getS1w3().get(i), i, 3);
                slot1Table.setValueAt(Arrays.toString(auxTable.getS1ia1().get(i)), i, 4);
            }

            slot2Table.setModel(new DefaultTableModel(
                    new Object [auxTable.getS2Number()][6],
                    new String [] {"Nr.","W1","W2","W3","ia2", "ia1"}
            ));
            for(int i = 0; i < auxTable.getS2Number(); i++ ) {
                slot2Table.setValueAt(auxTable.getS2Numbers().get(i), i, 0);
                slot2Table.setValueAt(auxTable.getS2w1().get(i), i, 1);
                slot2Table.setValueAt(auxTable.getS2w2().get(i), i, 2);
                slot2Table.setValueAt(auxTable.getS2w3().get(i), i, 3);
                slot2Table.setValueAt(Arrays.toString(auxTable.getS2ia2().get(i)), i, 4);
                slot2Table.setValueAt(Arrays.toString(auxTable.getS2ia1().get(i)), i, 5);

            }

            slot3Table.setModel(new DefaultTableModel(
                    new Object [auxTable.getS3Number()][7],
                    new String [] {"Nr.","W1","W2","W3","im","ia2", "ia1"}
            ));
            for(int i = 0; i < auxTable.getS3Number(); i++ ) {
                slot3Table.setValueAt(auxTable.getS3Numbers().get(i), i, 0);
                slot3Table.setValueAt(auxTable.getS3w1().get(i), i, 1);
                slot3Table.setValueAt(auxTable.getS3w2().get(i), i, 2);
                slot3Table.setValueAt(auxTable.getS3w3().get(i), i, 3);
                slot3Table.setValueAt(Arrays.toString(auxTable.getS3im().get(i)), i, 4);
                slot3Table.setValueAt(Arrays.toString(auxTable.getS3ia2().get(i)), i, 5);
                slot3Table.setValueAt(Arrays.toString(auxTable.getS3ia1().get(i)), i, 6);

            }



        });


        graph.addActionListener(e -> {
        JFrame pictureFrame = new JFrame();
        pictureFrame.add(new JLabel(new ImageIcon("CholeskyGraph.png")));
        pictureFrame.pack();
        pictureFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GUI("Cholesky Project");
        frame.setVisible(true);
    }

}
