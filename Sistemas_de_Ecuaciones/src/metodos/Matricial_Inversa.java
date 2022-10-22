package metodos;

public class Matricial_Inversa extends Resolucion_de_Sistemas_de_Ecuaciones
{

    public Matricial_Inversa(double[][] ecuaciones, int numero_incognitas)
    {
       super(ecuaciones, numero_incognitas);
    }

    public Matricial_Inversa(double[][] coeficientes_variables, double[][] coeficientes_independientes, int numero_incognitas)
    {
        super(coeficientes_variables, coeficientes_independientes, numero_incognitas);
    }


    public double[][] unirMatrices(double[][]matriz)
    {
        int tamañoNuevaMatriz = get_numero_incognitas()*2;

        
        
         
        
    }
    // las funciones en minuscula xd
    private double[] extraerFila(double[][] matriz, int filaBuscada)
    {
        int filaActual = 0;
        for (double[] fila : matriz) 
        { 
            if(filaActual++ == filaBuscada)
                return fila;
        }
        return null;
    }

    public double[][] hacerMatrizIdentidad()
    {
        double[][] matrizIdentidad = new double[get_numero_incognitas()][get_numero_incognitas()];
        for (int i = 0; i < matrizIdentidad.length; i++) 
                matrizIdentidad[i][i] = 1;
        return matrizIdentidad;
    }


    @Override
    public void calcular() 
    {
        //TODO hacer el proceso de calcular aki
    }

}
