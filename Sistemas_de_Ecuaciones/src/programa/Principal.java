package programa;

import interfaz.Ventana;

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
        // var ecuaciones = new double[][]{
        //     {3,-0.1,-0.2,7.85},
        //     {0.1,7,-0.3,-19.3},
        //     {0.3,-0.2,10,71.4}
        // };
        // var ecuaciones = new double[][]{
        //     {2,-1,4,1,-1,7},
        //     {-1,3,-2,-1,2,1},
        //     {5,1,3,-4,1,33},
        //     {3,-2,-2,-2,3,24},
        //     {-4,-1,-5,3,-4,-49}
        // };
        
        // var gauss = new Gauss_Jordan(ecuaciones,ecuaciones.length);
        // gauss.calcular();
        // System.out.println(gauss.get_sistema_de_ecuaciones().toString()); // funciona :D

        // var inversa = new Matricial_Inversa(ecuaciones, ecuaciones.length);
        // inversa.calcular();
        // System.out.println(inversa.get_sistema_de_ecuaciones().toString());
        new Ventana();
    }
}
