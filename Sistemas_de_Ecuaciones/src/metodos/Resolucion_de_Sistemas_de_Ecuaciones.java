package metodos;
import metodos.datos.Sistema_de_Ecuaciones;

/**
 * Resolucion_de_Sistemas_de_Ecuaciones
 */
public abstract class Resolucion_de_Sistemas_de_Ecuaciones 
{
    private Sistema_de_Ecuaciones sistema_de_ecuaciones;
    private int numero_incognitas;
    
    public Resolucion_de_Sistemas_de_Ecuaciones(double[][] ecuaciones, int numero_incognitas){
        this.numero_incognitas = numero_incognitas;
        sistema_de_ecuaciones = new Sistema_de_Ecuaciones(numero_incognitas);
        sistema_de_ecuaciones.set_ecuaciones(ecuaciones);
    }
    public Resolucion_de_Sistemas_de_Ecuaciones(double[][] coeficientes_variables, double[][] coeficientes_independientes, int numero_incognitas){
        this.numero_incognitas = numero_incognitas;
        sistema_de_ecuaciones = new Sistema_de_Ecuaciones(numero_incognitas);
        sistema_de_ecuaciones.set_ecuaciones(coeficientes_variables, coeficientes_independientes);
    }

    public abstract void calcular();

    public int get_numero_incognitas() {
        return numero_incognitas;
    }
    public void set_numero_incognitas(int numero_incognitas) {
        this.numero_incognitas = numero_incognitas;
    }
    public Sistema_de_Ecuaciones get_sistema_de_ecuaciones() {
        return sistema_de_ecuaciones;
    }
    public void set_sistema_de_ecuaciones(Sistema_de_Ecuaciones sistema_de_ecuaciones) {
        this.sistema_de_ecuaciones = sistema_de_ecuaciones;
    }
}