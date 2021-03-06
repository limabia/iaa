/*.7 Escreva uma versão iterativa e pelo menos duas recursivas (variando a forma de dividir o problema
original em subproblemas) métodos para, dado um vetor a de valores inteiros, resolver os
seguintes problemas:
a) determinar a soma dos valores contidos em a.
b) determinar o valor mínimo contido em a.
c) determinar se um valor x está presente em a.
d) determinar o número de ocorrências de valores menores ou iguais a x em a.
e) determinar se todos os elementos de a são iguais.
e) imprimir os elementos de a na ordem a[0], a[1], ..., a[n − 1].
f) imprimir os elementos de a na ordem a[n − 1], a[n − 2], ..., a[0].*/


class operaVet {
    /* iterativo: determinar a soma dos valores contidos em a. */
    public static int somaIt(int[] a) {
    	int resultado = 0;
    	for(int i=0; i<a.length; i++){
    		resultado = resultado + a[i];
    	}
    	return resultado;
    }
    /* recursivo 1: determinar a soma dos valores contidos em a. */
    public static int somaRec1(int[] a, int inicio) {
		if (inicio == a.length-1){
    		return a[inicio];
    	}
    	else{
    		return ((somaRec1(a, inicio+1))+a[inicio]);	
    	}
    }
    /* recursivo 2: determinar a soma dos valores contidos em a. */
    public static int somaRec2(int[] a, int inicio, int fim) {
		if (inicio == fim){
    		return a[inicio];
    	}
    	else{
    		int meio = (inicio+fim)/2;
    		return ((somaRec2(a, inicio, meio)) + (somaRec2(a, meio+1, fim)));	
    	}
    }
    /* iterativo: determinar o valor mínimo contido em a. */
    public static int min(int[] a){
    	int min = a[0];
 		int i = 0;
    	while(i < a.length){
    		if (a[i] < min){
    			min = a[i];
    		}
    		i ++;
    	}
    	return min;
    }
    /* recursivo 1: determinar o valor mínimo contido em a. */
    public static int minRec1(int[] a, int inicio){
    	if(inicio == a.length-1){
    		return a[inicio];
    	}
    	else{
    		int proxMin = minRec1(a, inicio+1);
    		if (a[inicio] < proxMin){
	    		return a[inicio] ;
    		}
    		return proxMin;
    	}
    }
   /* recursivo 2: determinar o valor mínimo contido em a. */  
   public static int minRec2(int[] a, int inicio, int fim){
        if(inicio == fim){
            return a[0];
        }
        else {
            int meio = (inicio+fim)/2;

            int primMet = minRec2(a, inicio, meio);
            int segMet = minRec2(a, meio+1, fim);
            if (primMet < segMet){
                return primMet ;
            }
            return segMet; 
        }
    }
    /* recursivo 1: determinar se um valor x está presente em a.*/
    public static boolean estaEmRec1(int[] a, int procurado, int inicio){
        if (inicio == a.length){
            return false;
        }
        else{
            if(a[inicio] == procurado){
                return true;
            }
            else{
                return estaEmRec1(a, procurado, inicio+1);      
            }
        }
    }
    /* recursivo 2: determinar se um valor x está presente em a.*/
    public static boolean estaEmRec2(int[] a, int procurado, int inicio, int fim){
        if(inicio == fim) {
            return false;
        } else {
            int meio = (inicio+fim)/2;
            boolean primMet = estaEmRec2(a, procurado, inicio, meio);
            boolean segMet = estaEmRec2(a, procurado, meio+1, fim);
            if(primMet || segMet){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] arg){
	    int[] a = {1, 2, 3, 4, 5};
	    System.out.println("IT: a soma dos valores de a eh: "+somaIt(a));
	    System.out.println("REC 1: a soma dos valores de a eh: "+somaRec1(a, 0));
	    System.out.println("REC 2: a soma dos valores de a eh: "+somaRec2(a, 0, a.length-1));
	    System.out.println("IT: o minimo dos valores de a eh: "+min(a));
	    System.out.println("REC 1: o minimo dos valores de a eh: "+minRec1(a, 0));
        System.out.println("REC 2: o minimo dos valores de a eh: "+minRec2(a, 0, a.length-1));
        System.out.println("REC 1: o valor 7 esta em a: "+estaEmRec1(a, 7, 0));
        System.out.println("REC 2: o valor 7 esta em a: "+estaEmRec2(a, 7, 0, a.length-1));
    }
}