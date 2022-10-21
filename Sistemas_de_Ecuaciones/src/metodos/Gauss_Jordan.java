package metodos;

import java.util.Arrays;

import metodos.datos.Sistema_de_Ecuaciones;

public class Gauss_Jordan {
    private Sistema_de_Ecuaciones sistema_de_ecuaciones;
    private int numero_incognitas;
    public Gauss_Jordan(double[][] ecuaciones, int numero_incognitas){
        this.numero_incognitas = numero_incognitas;
        sistema_de_ecuaciones = new Sistema_de_Ecuaciones(numero_incognitas);
        sistema_de_ecuaciones.set_ecuaciones(ecuaciones);
    }
    public Gauss_Jordan(double[][] coeficientes_variables, double[][] coeficientes_independientes, int numero_incognitas){
        this.numero_incognitas = numero_incognitas;
        sistema_de_ecuaciones = new Sistema_de_Ecuaciones(numero_incognitas);
        sistema_de_ecuaciones.set_ecuaciones(coeficientes_variables, coeficientes_independientes);
    }
    public void calcular() {
        for (int actual = 0; actual < numero_incognitas; actual++) {
            var fila_dominante = sistema_de_ecuaciones.get_fila(actual);
            var denominador = fila_dominante[actual] != 0 ? fila_dominante[actual] : 1; //TODO mandar error
            fila_dominante = multiplicar_fila_por(fila_dominante, 1/denominador);
            sistema_de_ecuaciones.set_fila(fila_dominante, actual);
            for (int siguiente = actual+1; siguiente < numero_incognitas + actual; siguiente++) {
                var siguiente_ajustado = siguiente % numero_incognitas;
                var fila_siguiente = sistema_de_ecuaciones.get_fila(siguiente_ajustado);
                var fila_multiplicada = multiplicar_fila_por(fila_dominante, fila_siguiente[actual] * -1);
                var filas_sumadas = sumar_filas(fila_multiplicada, fila_siguiente);
                sistema_de_ecuaciones.set_fila(filas_sumadas, siguiente_ajustado);
            }
        }
    }
    private double[] multiplicar_fila_por(double[] fila, double fraccion){
        var fila_resultante = Arrays.copyOf(fila, fila.length);
        for (int i = 0; i < fila.length; i++) {
            fila_resultante[i] *= fraccion;
        }
        return fila_resultante;
    }
    private double[] sumar_filas(double[] fila1, double[] fila2){
        var fila_resultante = Arrays.copyOf(fila1, fila1.length);
        for (int i = 0; i < fila1.length; i++) {
            fila_resultante[i] += fila2[i];
        }
        return fila_resultante;
    }
    public Sistema_de_Ecuaciones get_sistema_de_ecuaciones() {
        return sistema_de_ecuaciones;
    }
    public void set_sistema_de_ecuaciones(Sistema_de_Ecuaciones sistema_de_ecuaciones) {
        this.sistema_de_ecuaciones = sistema_de_ecuaciones;
    }
    public int get_numero_incognitas() {
        return numero_incognitas;
    }
    public void set_numero_incognitas(int numero_incognitas) {
        this.numero_incognitas = numero_incognitas;
    }
    
}
