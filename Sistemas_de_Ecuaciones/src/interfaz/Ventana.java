package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame
{
    private JPanel panelSuperior, panelIzquierdo, panelCentral;
    private JButton botonPrincipal, botonDatos, botonResultados;
    private JButton botonCerrar, botonMinimizar;
    private JPanel panelAccionesVentana;
    private Dimension tamanoVentana;
    private JPanel panelPrincipal, panelDatos, panelResultados;
    private JPanel panelBienvenida;
    private JButton botonCalcular;
    private JScrollPane ScrollGaussJordan, ScrollMatrizInversa;
    private JPanel panelIncognitas;
    private Boton botonConfirmar;
    private Campo campoIncognitas;
    private PanelScroll ScrollVariables, ScrollIndependientes;

    private DecimalFormat formato = new DecimalFormat("#.############");

    public final static Color colorIzquierda = new Color(104,249,205), 
    colorSuperior = new Color(29,248,181), 
    colorBarraDeAcciones = new Color(24,196,145),
    colorCentral = Color.white,
    colorSeleccionarBotonChico = new Color(104,249,205),
    colorBoton = new Color(14,120,88),
    colorSeleccionarBoton = new Color(87,108,102),
    colorTitulo = colorBoton;
    

    private Font letraMenu = new Font("Forte", Font.PLAIN, 40),
    letraMenuChica = new Font("Forte", Font.PLAIN, 33),
    letraTitulo = new Font("Maiandra GD", Font.BOLD, 60), 
    letraSubTitulo = new Font("Maiandra GD", Font.BOLD, 40), 
    letraTexto = new Font("Maiandra GD", Font.PLAIN, 30), 
    letraBoton = new Font("Matura MT Script Capitals", Font.PLAIN, 40),
    letraBotonAccionVentana = new Font("Bauhaus 93", Font.PLAIN, 20);

    public Ventana(){
        inicializarVentana();
        inicializarPanelIzquierdo();
        inicializarPanelSuperior();
        inicializarPanelCentral();
        panelCentral.add(panelPrincipal); // para que sea el primero en aparecer
        establecerFuncionBotonesMenu();
        establacerFuncionBotonConfirmar();
        //establecerFuncionBotonAceptar(funcion);
        setVisible(true);

    }
    private void establacerFuncionBotonConfirmar() {
        botonConfirmar.addActionListener((e) -> {
            try {
                int tamano = Integer.parseInt(campoIncognitas.getText());
                ScrollVariables.set_dimensiones(tamano, tamano);
                ScrollIndependientes.set_dimensiones(tamano, 1);
                ScrollIndependientes.cambiar_identificadores(new String[]{" "});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error, Numero Ingresado Invalido", 
                "ERROR NUMERO INVALIDO", JOptionPane.ERROR_MESSAGE, null);
            }
        });
    }
    private boolean opcionSiNo(String mensaje, String titulo){
        while (true) {
            int valor = JOptionPane.showOptionDialog(null, mensaje, titulo, 0, 1, null, "Si, No".split(", "), null);
            if(valor != -1)
                return valor == 0; // 0 = Si, 1 = No, -1 = Cerrar la ventana
            JOptionPane.showMessageDialog(this, "ERROR, DEBE ELEGIR UNA DE LAS OPCIONES", "ERROR OPCION INVALIDA", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void establecerFuncionBotonesMenu() {
        botonPrincipal.addActionListener((e) -> {
            panelCentral.removeAll();
            panelCentral.add(panelPrincipal);
            panelCentral.updateUI();
        });

        botonDatos.addActionListener((e) -> {
            panelCentral.removeAll();
            panelCentral.add(panelDatos);
            panelCentral.updateUI();
        });

        botonResultados.addActionListener((e) -> {
            panelCentral.removeAll();
            panelCentral.add(panelResultados);
            panelCentral.updateUI();
        });
    }
    private void inicializarPanelCentral() {
        panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelCentral.setLayout(new GridLayout(1,1));
        add(panelCentral, BorderLayout.CENTER);
        inicializarPanelPrincipal();//! Principal
        inicializarPanelDatos();//! Datos
        inicializarPanelResultados();//! Resultados

    }
    private void inicializarPanelResultados() {
        panelResultados = new JPanel();
        panelResultados.setOpaque(false);
        panelResultados.setLayout(null);

        JLabel textoGaussJordan = new JLabel("Gauss-Jordan");
        textoGaussJordan.setFont(letraTexto);
        textoGaussJordan.setHorizontalAlignment(JLabel.CENTER);
        textoGaussJordan.setBounds(220, 20, 300, 50);

        DefaultTableModel modeloTabla = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };

        JTable tablaGaussJordan = new JTable(modeloTabla);
        
        ScrollGaussJordan = new PanelScroll(tablaGaussJordan);
        ScrollGaussJordan.setBounds(20,100,500,200);

        
        panelResultados.add(textoGaussJordan);
        panelResultados.add(ScrollGaussJordan);
    }
    private void inicializarPanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setOpaque(false);
        panelDatos.setLayout(null);
        
        panelIncognitas = new JPanel();
        panelIncognitas.setOpaque(false);
        panelIncognitas.setLayout(new GridLayout(1,3,50,0));
        panelIncognitas.setBounds(10,30,750,50);

        JLabel textoIncognitas = new JLabel("Incognitas");
        textoIncognitas.setFont(letraTexto);
        textoIncognitas.setHorizontalAlignment(JLabel.CENTER);

        campoIncognitas = new Campo(letraTexto);
        campoIncognitas.setText("4");

        botonConfirmar = new Boton(letraBoton);
        botonConfirmar.setText("Confirmar");
        botonConfirmar.setVerticalAlignment(JButton.CENTER);

        panelIncognitas.add(textoIncognitas);
        panelIncognitas.add(campoIncognitas);
        panelIncognitas.add(botonConfirmar);

        JLabel textoCoeficientes = new JLabel("            Coeficientes Variables               Independientes");
        textoCoeficientes.setFont(letraTexto);
        textoCoeficientes.setHorizontalAlignment(JLabel.CENTER);
        textoCoeficientes.setBounds(30,100,800,50);

        JTable tablaVariables = new JTable(new DefaultTableModel());

        ScrollVariables = new PanelScroll(tablaVariables);
        ScrollVariables.setBounds(20, 170, 600, 300);

        JTable tablaIndependientes = new JTable(new DefaultTableModel());

        ScrollIndependientes = new PanelScroll(tablaIndependientes);
        ScrollIndependientes.setBounds(600+20+20, 170, 130, 300);

        botonCalcular = new Boton(letraBoton);
        botonCalcular.setText("Calcular");
        botonCalcular.setVerticalAlignment(JButton.CENTER);
        botonCalcular.setBounds(280,500,200,50);

        panelDatos.add(ScrollVariables);
        panelDatos.add(ScrollIndependientes);
        panelDatos.add(panelIncognitas);
        panelDatos.add(textoCoeficientes);
        panelDatos.add(botonCalcular);
    }
    private void inicializarPanelPrincipal() {
        panelPrincipal = new JPanel();
        panelPrincipal.setOpaque(false);
        panelPrincipal.setLayout(null);

        panelBienvenida = new JPanel();
        panelBienvenida.setOpaque(false);
        panelBienvenida.setLayout(new GridLayout(10,1));
        panelBienvenida.setBounds(50, 150, 
        700, 500);

        JLabel textoMetodos = new JLabel("Usando Gauss-Jordan y");
        textoMetodos.setFont(letraSubTitulo);
        textoMetodos.setHorizontalAlignment(JLabel.CENTER);

        JLabel textoMetodos2 = new JLabel("Matriz Inversa");
        textoMetodos2.setFont(letraSubTitulo);
        textoMetodos2.setHorizontalAlignment(JLabel.CENTER);

        JLabel textoCreadoPor = new JLabel("Creado por:");
        textoCreadoPor.setFont(letraTexto);
        textoCreadoPor.setHorizontalAlignment(JLabel.CENTER);

        JLabel textoIntegrantes = new JLabel("Ricardo Sanjur");
        textoIntegrantes.setFont(letraTexto);
        textoIntegrantes.setHorizontalAlignment(JLabel.CENTER);

        JLabel textoIntegrantes2 = new JLabel("Thaís Samudio");
        textoIntegrantes2.setFont(letraTexto);
        textoIntegrantes2.setHorizontalAlignment(JLabel.CENTER);

        panelBienvenida.add(textoMetodos);
        panelBienvenida.add(textoMetodos2);
        panelBienvenida.add(new JLabel());
        panelBienvenida.add(textoCreadoPor);
        panelBienvenida.add(textoIntegrantes);
        panelBienvenida.add(textoIntegrantes2);

        panelPrincipal.add(panelBienvenida);

        
    }
    private void inicializarPanelSuperior() {
        JLabel textoTitulo = new JLabel("Cálculo de Sistemas de Ecuaciones");
        textoTitulo.setFont(letraTitulo);
        textoTitulo.setHorizontalAlignment(JLabel.CENTER);
        textoTitulo.setForeground(colorTitulo);

        panelAccionesVentana = new JPanel();
        panelAccionesVentana.setBackground(colorBarraDeAcciones);
        panelAccionesVentana.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
        panelAccionesVentana.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        botonCerrar = new BotonChico(letraBotonAccionVentana);
        botonCerrar.setText("X");
        botonCerrar.addActionListener((e) -> dispose());


        botonMinimizar = new BotonChico(letraBotonAccionVentana);
        botonMinimizar.setText("—");
        botonMinimizar.addActionListener((e) -> setState(ICONIFIED));

        panelAccionesVentana.add(botonCerrar);
        panelAccionesVentana.add(botonMinimizar);

        panelSuperior.add(textoTitulo, BorderLayout.CENTER);
        panelSuperior.add(panelAccionesVentana, BorderLayout.NORTH);

    }
    private void inicializarPanelIzquierdo() {
        botonPrincipal = new Boton(letraMenu);
        botonPrincipal.setText("Principal");

        botonDatos = new Boton(letraMenu);
        botonDatos.setText("Datos");

        botonResultados = new Boton(letraMenuChica);
        botonResultados.setText("Resultados");

        panelIzquierdo.add(new JLabel());
        panelIzquierdo.add(new JLabel());
        panelIzquierdo.add(botonPrincipal);
        panelIzquierdo.add(botonDatos);
        panelIzquierdo.add(botonResultados);
        
    }
    private void inicializarVentana() {
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        tamanoVentana = new Dimension(1000,700);
        setSize(tamanoVentana);
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        getContentPane().setBackground(colorCentral);

        panelSuperior = new JPanel();
        panelSuperior.setBackground(colorSuperior);
        panelSuperior.setPreferredSize(new Dimension(getWidth(), 100));
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setMinimumSize(panelSuperior.getSize());
        add(panelSuperior, BorderLayout.NORTH);

        panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(colorIzquierda);
        panelIzquierdo.setPreferredSize(new Dimension(200, getHeight()));
        panelIzquierdo.setLayout(new GridLayout(7,1,0,20));
        panelIzquierdo.setMinimumSize(panelIzquierdo.getSize());
        add(panelIzquierdo, BorderLayout.WEST);
    }
}
