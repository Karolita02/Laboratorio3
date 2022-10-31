package metodos;

import java.util.Arrays;

public class Gauss_Jordan extends Resolucion_de_Sistemas_de_Ecuaciones
{

    public Gauss_Jordan(double[][] ecuaciones, int numero_incognitas)
    {
        super(ecuaciones, numero_incognitas);
    }
    public Gauss_Jordan(double[][] coeficientes_variables, double[][] coeficientes_independientes, int numero_incognitas)
    {
        super(coeficientes_variables, coeficientes_independientes, numero_incognitas);
    }
    public void calcular() 
    {
        for (int actual = 0; actual < get_numero_incognitas(); actual++) 
        {
            var fila_dominante = get_sistema_de_ecuaciones().get_fila(actual);
            var denominador = fila_dominante[actual] != 0.0 ? fila_dominante[actual] : 1; //TODO mandar error
            fila_dominante = multiplicar_fila_por(fila_dominante, 1/denominador);
            get_sistema_de_ecuaciones().set_fila(fila_dominante, actual);
            for (int siguiente = actual+1; siguiente < get_numero_incognitas() + actual; siguiente++) 
            {
                var siguiente_ajustado = siguiente % get_numero_incognitas();
                var fila_siguiente = get_sistema_de_ecuaciones().get_fila(siguiente_ajustado);
                var fila_multiplicada = multiplicar_fila_por(fila_dominante, fila_siguiente[actual] * -1);
                var filas_sumadas = sumar_filas(fila_multiplicada, fila_siguiente);
                get_sistema_de_ecuaciones().set_fila(filas_sumadas, siguiente_ajustado);
            }
        }
    }
    private double[] multiplicar_fila_por(double[] fila, double fraccion)
    {
        var fila_resultante = Arrays.copyOf(fila, fila.length);
        for (int i = 0; i < fila.length; i++) {
            fila_resultante[i] *= fraccion;
        }
        return fila_resultante;
    }
    private double[] sumar_filas(double[] fila1, double[] fila2)
    {
        var fila_resultante = Arrays.copyOf(fila1, fila1.length);
        for (int i = 0; i < fila1.length; i++) {
            fila_resultante[i] += fila2[i];
        }
        return fila_resultante;
    }    
}
