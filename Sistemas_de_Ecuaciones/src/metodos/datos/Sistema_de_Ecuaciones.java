package metodos.datos;

import java.util.Arrays;

public class Sistema_de_Ecuaciones 
{
    private int numero_incognitas;
    private double[][] coeficientes_variables;
	private double[][] coeficientes_independientes;

    

	public Sistema_de_Ecuaciones(int numero_incognitas) {
        this.numero_incognitas = numero_incognitas;
        coeficientes_variables = new double[numero_incognitas][numero_incognitas];
        coeficientes_independientes = new double[numero_incognitas][1];
    }

    public double[] get_fila(int fila_buscada){
        int fila_actual = 0;
        double[] resultante = new double[numero_incognitas+1];
        for (double[] fila : coeficientes_variables) {
            if(fila_actual++ == fila_buscada){
                System.arraycopy(fila, 0, resultante, 0, numero_incognitas);
                resultante[numero_incognitas] = coeficientes_independientes[fila_buscada][0];
                return resultante;
            }
        }
        return null;
    }
    public void set_fila(double[] fila_obtenida,int fila_buscada){
        for (int columna = 0; columna < numero_incognitas; columna++) {
            coeficientes_variables[fila_buscada][columna] = fila_obtenida[columna];
        }
        coeficientes_independientes[fila_buscada][0] = fila_obtenida[fila_obtenida.length-1];
    }

    public int get_numero_incognitas() {
        return numero_incognitas;
    }

    public double[][] get_coeficientes_variables() {
        return coeficientes_variables;
    }

    public double[][] get_coeficientes_independientes() {
        return coeficientes_independientes;
    }

    @Override
    public String toString(){
        String cadena = "";
        var arreglo_coeficientes_variables = coeficientes_variables_toString().split("\n");
        var arreglo_coeficientes_independientes = coeficientes_independientes_toString().split("\n");
        for (int fila = 0; fila < numero_incognitas; fila++) {
            cadena += arreglo_coeficientes_variables[fila] + " = " + arreglo_coeficientes_independientes[fila] + "\n";
        }
        return cadena;
    }
    public String coeficientes_variables_toString(){
        String cadena = "";
        for (double[] fila : coeficientes_variables) {
            if(fila == null) break;
            cadena += Arrays.toString(fila) + "\n";
        }
        return cadena;
    }
    public String coeficientes_independientes_toString(){
        String cadena = "";
        for (double[] fila : coeficientes_independientes) {
            if(fila == null) break;
            cadena += Arrays.toString(fila) + "\n";
        }
        return cadena;
    }

    public void set_ecuaciones(double[][] ecuaciones){
        int contar = 0;
        for (double[] fila : ecuaciones) {
            set_fila(fila, contar++);
        }
    }

    public void set_ecuaciones(double[][] coeficientes_variables, double[][] coeficientes_independientes){
        for (int i = 0; i < numero_incognitas; i++) 
            for (int j = 0; j < numero_incognitas; j++) 
                this.coeficientes_variables[i][j] = coeficientes_variables[i][j];
        for (int i = 0; i < numero_incognitas; i++) 
            this.coeficientes_independientes[i][0] = coeficientes_independientes[i][0];
    }
    public void set_coeficientes_variables(double[][] coeficientes_variables) {
		this.coeficientes_variables = coeficientes_variables;
	}
    public void set_coeficientes_independientes(double[][] coeficientes_independientes) {
		this.coeficientes_independientes = coeficientes_independientes;
	}
}
