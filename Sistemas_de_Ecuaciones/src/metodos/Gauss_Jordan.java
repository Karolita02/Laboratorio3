package metodos;

import java.util.Arrays;

import metodos.datos.Sistema_de_Ecuaciones;

public class Gauss_Jordan {
    private Sistema_de_Ecuaciones sistema_de_ecuaciones;
    public Gauss_Jordan(int numero_incognitas){
        sistema_de_ecuaciones = new Sistema_de_Ecuaciones(numero_incognitas);
        sistema_de_ecuaciones.set_fila(new double[]{3,-0.1,-0.2,7.85}, 0);
        sistema_de_ecuaciones.set_fila(new double[]{0.1,7,-0.3,-19.3}, 1);
        sistema_de_ecuaciones.set_fila(new double[]{0.3,-0.2,10,71.4}, 2);
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
    public String get_ecuacion(){
        return sistema_de_ecuaciones.ecuacion_toString();
    }
}
