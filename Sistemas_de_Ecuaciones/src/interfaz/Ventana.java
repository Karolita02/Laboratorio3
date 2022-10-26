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
    private JButton botonPrincipal, botonDatos, botonTabla;
    private JLabel textoTitulo;
    private JButton botonCerrar, botonMinimizar;
    private JPanel panelAccionesVentana;
    private Dimension tamanoVentana;
    private JPanel panelPrincipal, panelDatos, panelTabla;
    private JLabel textoMetodos, textoMetodos2, textoCreadoPor, textoIntegrantes, textoIntegrantes2;
    private JPanel panelBienvenida;
    private JButton botonAceptar;
    private JTable tabla;
    private JScrollPane panelScroll;
    private JPanel panelEtiquetas;
    private JLabel biseccion, reglaFalsa, secante;
    private JLabel valorA, valorB, valorError, textoFuncion;
    private JPanel panelInformacion;
    private JLabel textoIntroducirDatos, textoCoeficientesVariables, textoCoeficientesIndependientes;

    private DecimalFormat formato = new DecimalFormat("#.############");

    public final static Color colorIzquierda = new Color(104,249,205), 
    colorSuperior = new Color(29,248,181), 
    colorBarraDeAcciones = new Color(24,196,145),
    colorCentral = Color.white,
    colorSeleccionarBotonChico = new Color(104,249,205),
    colorBoton = new Color(14,120,88),
    colorSeleccionarBoton = new Color(87,108,102);
    

    private Font letraMenu = new Font("Forte", Font.PLAIN, 40),
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
        this.textoFuncion.setText("f(x) = " + textoFuncion);
        establecerFuncionBotonesMenu();
        //establecerFuncionBotonAceptar(funcion);
        setVisible(true);

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

        botonTabla.addActionListener((e) -> {
            panelCentral.removeAll();
            panelCentral.add(panelTabla);
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
        inicializarPanelTabla();//! Tabla

    }
    private void inicializarPanelTabla() {
        panelTabla = new JPanel();
        panelTabla.setOpaque(false);
        panelTabla.setLayout(new BorderLayout());

        DefaultTableModel modeloTabla = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        modeloTabla.setColumnIdentifiers("Iteracion, Xr, er%, Xr, er%, Xr, er%".split(", "));
        tabla = new JTable(modeloTabla);
        tabla.getTableHeader().setReorderingAllowed(false); 
        
        panelScroll = new JScrollPane(tabla);
        panelScroll.setBounds(0,100,800,600);

        panelEtiquetas = new JPanel();
        panelEtiquetas.setOpaque(false);
        panelEtiquetas.setLayout(null);
        panelEtiquetas.setPreferredSize(new Dimension(0,150));

        int altura = 3 + 100;

        biseccion = new JLabel("Bisección");
        biseccion.setFont(letraTexto);
        biseccion.setBounds(165,altura,200,50);
        
        reglaFalsa = new JLabel("Regla Falsa");
        reglaFalsa.setFont(letraTexto);
        reglaFalsa.setBounds(165*2+50,altura,200,50);
        
        secante = new JLabel("Secante");
        secante.setFont(letraTexto);
        secante.setBounds(165*4-30,altura,200,50);
        
        panelEtiquetas.add(biseccion);
        panelEtiquetas.add(reglaFalsa);
        panelEtiquetas.add(secante);

        Color colorInfo = new Color(31,105,165);

        valorA = new JLabel("Intervalo (A = ? ,");
        valorA.setFont(letraTexto);
        valorA.setForeground(colorInfo);
   
        valorB = new JLabel("B = ?)");
        valorB.setFont(letraTexto);
        valorB.setForeground(colorInfo);
   
        valorError = new JLabel("Error = ?");
        valorError.setFont(letraTexto);
        valorError.setForeground(colorInfo);
        
        textoFuncion = new JLabel();
        textoFuncion.setFont(letraTexto);
        textoFuncion.setForeground(colorInfo);

        panelInformacion = new JPanel();
        panelInformacion.setOpaque(false);
        panelInformacion.setBounds(0,15,800,100);
        var estilo = new FlowLayout(FlowLayout.CENTER);
        estilo.setHgap(20);
        panelInformacion.setLayout(estilo);

        panelInformacion.add(valorA);
        panelInformacion.add(valorB);
        panelInformacion.add(valorError);
        panelInformacion.add(textoFuncion);

        panelEtiquetas.add(panelInformacion);

        panelTabla.add(panelScroll, BorderLayout.CENTER);
        panelTabla.add(panelEtiquetas, BorderLayout.NORTH);
    }
    private void inicializarPanelDatos() {
        panelDatos = new JPanel();
        panelDatos.setOpaque(false);
        panelDatos.setLayout(null);
        
        textoIntroducirDatos = new JLabel("Introduzca los Siguientes Datos:");
        textoIntroducirDatos.setFont(letraTexto);
        textoIntroducirDatos.setBounds(100,50,500,50);

        botonAceptar = new Boton(letraBoton);
        botonAceptar.setText("Aceptar");
        botonAceptar.setVerticalAlignment(JButton.CENTER);
        botonAceptar.setBounds(280,400,200,50);

        panelDatos.add(textoIntroducirDatos);
        panelDatos.add(botonAceptar);
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

        textoMetodos = new JLabel("Usando Gauss-Jordan y");
        textoMetodos.setFont(letraSubTitulo);
        textoMetodos.setHorizontalAlignment(JLabel.CENTER);

        textoMetodos2 = new JLabel("Matriz Inversa");
        textoMetodos2.setFont(letraSubTitulo);
        textoMetodos2.setHorizontalAlignment(JLabel.CENTER);

        textoCreadoPor = new JLabel("Creado por:");
        textoCreadoPor.setFont(letraTexto);
        textoCreadoPor.setHorizontalAlignment(JLabel.CENTER);

        textoIntegrantes = new JLabel("Ricardo Sanjur");
        textoIntegrantes.setFont(letraTexto);
        textoIntegrantes.setHorizontalAlignment(JLabel.CENTER);

        textoIntegrantes2 = new JLabel("Thaís Samudio");
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
        textoTitulo = new JLabel("Cálculo de Sistemas de Ecuaciones");
        textoTitulo.setFont(letraTitulo);
        textoTitulo.setHorizontalAlignment(JLabel.CENTER);
        textoTitulo.setForeground(new Color(0,69,126));

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

        botonTabla = new Boton(letraMenu);
        botonTabla.setText("Tabla");

        panelIzquierdo.add(new JLabel());
        panelIzquierdo.add(new JLabel());
        panelIzquierdo.add(botonPrincipal);
        panelIzquierdo.add(botonDatos);
        panelIzquierdo.add(botonTabla);
        
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
