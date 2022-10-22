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


    public double[][] UnirMatrices(double[][]matriz)
    {
        int tamañoNuevaMatriz = get_numero_incognitas()*2;

        for (iterable_type iterable_element : iterable) 
        {
            
        }


    }

    public double[][] HacerMatrizIdentidad()
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
