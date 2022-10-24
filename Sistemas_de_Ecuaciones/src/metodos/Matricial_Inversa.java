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


    public double[][] unirMatricesIdentidadYVariables()
    {
        int tamañoNuevaMatriz = get_numero_incognitas()*2;
        double[][] matrizResultante = new double[tamañoNuevaMatriz][tamañoNuevaMatriz];
        double[][] matrizIdentidad = hacerMatrizIdentidad();
       
        for (int fila = 0; fila < get_numero_incognitas(); fila++) 
        {
            //ahora pedimos la primera fila de cada matriz
            double[] filaIdentidad = extraerFila(matrizIdentidad, fila);
            double[] filaCoeficientesVariables = extraerFila(
                get_sistema_de_ecuaciones().get_coeficientes_variables(),fila);
            double[] filasUnidas = unirFilas(filaCoeficientesVariables, filaIdentidad);
            colocarFilasUnidas(filasUnidas, matrizResultante, fila);
        }
        return matrizResultante;
    }

    public void calcularMatrizInversa()
    {
        double[][] matrizParaGauss = unirMatricesIdentidadYVariables();
        // creamos un objeto gauss jordan
        // ingresamos las matrices
        Gauss_Jordan gaussJordan = new Gauss_Jordan(matrizParaGauss,new double[get_numero_incognitas()*2][1], get_numero_incognitas()*2); 
        //calculamos usando gauss jordan 
        gaussJordan.calcular();
        //obtenemos la matriz de gauss jordan ahora tamos aki
        double[][] matrizResultante = gaussJordan.get_sistema_de_ecuaciones().get_coeficientes_variables();
        // extraemos la matriz inversa
        get_sistema_de_ecuaciones().set_coeficientes_variables(extraerMatrizInversa(matrizResultante));
    }
    
    private double[][] extraerMatrizInversa(double[][] matriz)
    {
        double[][] matrizInversa = new double[get_numero_incognitas()][get_numero_incognitas()];
        for (int fila = 0; fila < get_numero_incognitas(); fila++) {
            double[] filaExtraida = extraerFila(matriz, fila);
            insertarFila(matrizInversa, filaExtraida, fila);
        }         
        return matrizInversa;
    }
   
    private void insertarFila(double[][] matriz, double[] fila, int posicion){  
        for (int columna = 0; columna < matriz[0].length; columna++) 
            matriz[posicion][columna] = fila[columna+matriz[0].length];
    }  

    private void colocarFilasUnidas(double[] filasUnidas, double[][] matriz, int filaAInsertar){
        for (int columna = 0; columna < matriz[0].length; columna++) 
            matriz[filaAInsertar][columna] = filasUnidas[columna];
    }

    private double[] unirFilas(double[] fila1, double[] fila2){
        double[] resultante = new double[fila1.length*2];
        System.arraycopy(fila1, 0, resultante, 0, fila1.length);
        System.arraycopy(fila2, 0, resultante, fila1.length, fila2.length);
        return resultante;
    }

    /**
        arrayCopy(la que quieres copiar, desde donde, destino, desde donde empezar a pegar, 
        la cantidad de elementos q vas a copiar)
        
     */

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
        calcularMatrizInversa();
        double[][] resultado = multiplicarMatrices(
            get_sistema_de_ecuaciones().get_coeficientes_variables(), 
            get_sistema_de_ecuaciones().get_coeficientes_independientes());
        get_sistema_de_ecuaciones().set_coeficientes_independientes(resultado); 
    }

    private double[][] multiplicarMatrices(double[][] matriz1, double[][] matriz2){
        var matrizResultante = new double[matriz1.length][matriz2[0].length];
        for (int fila = 0; fila < matriz1.length; fila++) { 
            for (int columna = 0; columna < matriz2[0].length; columna++) {
                for (int iterador = 0; iterador < matriz2.length; iterador++) {
                    matrizResultante[fila][columna] += matriz1[fila][iterador] * matriz2[iterador][columna];// ten yo :pena: es q solo tiene 1 columna el 2  y si dejamos q le de hasta las columnas del 1 va a pedir disq la columna 2 y 3 q no existen xd ah weno tonche shi tiene sentido lito ps hay que hacer la funcion cslcular alla  bien chevere xd 
                }
            }
        }
        return matrizResultante;
    }

}
