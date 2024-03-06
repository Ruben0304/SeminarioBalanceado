import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame{
    private JPanel panelContenedor;
    private JButton insertarButton;
    private JButton eliminarButton;
    private JSpinner spinner1;
    private JButton mostrarButton;

    private AvlTree<Integer> arbol = new AvlTree<>();


    public Interfaz() {


        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             arbol.insert(Integer.parseInt(spinner1.getValue().toString()));
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbol.deleteValue(Integer.parseInt(spinner1.getValue().toString()));
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePrinter.print((PrintableNode) arbol.getNodeRoot());
            }
        });
    }

    public static void main(String[] args) {
        Interfaz i = new Interfaz();
        i.setContentPane(i.panelContenedor);
        i.setTitle("vista");
        i.setSize(300, 200);
        i.setVisible(true);
        i.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
