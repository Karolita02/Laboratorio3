package programa;

import metodos.Gauss_Jordan;

/*
 * Desarrollar en Java un programa que determine la solución de un sistema de ecuaciones 
 * mediante el método de Gauss-Jordan y el método matricial Inv(A)*b   El ingreso de los datos 
 * debe ser interativo, solicitando el número de ecuaciones e incógnitas (se asume que el 
 * número de incógnitas es el mismo que las ecuaciones.   De allí ir ingresando los 
 * coeficientes de las variables y luego los coeficientes independientes.   El resultado de 
 * ambos métodos se desplegará a continuación.

 * Pueden presentar el proyecto de forma individual o en grupo de 2.

 * En cuanto a la documentación, deben presentarla en formato de Word siguiendo los 
 * lineamientos del proyecto anterior.
 */

public class Principal {
    public static void main(String[] args) {
        var gauss = new Gauss_Jordan(3);
        System.out.println(gauss.get_ecuacion()); // funciona :D
    }
}
