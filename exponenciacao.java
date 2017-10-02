/* Escreva um método recursivo que recebe dois valores inteiros c e n, e devolve o valor de c^n
(obs:
sem utilizar qualquer método da classe Math).*/
class exponenciacao{
	public static int exponencia(int c, int n) {
        if (n == 0) {
            return 1;
        } else {
        	
        	return(exponencia(c,n-1)*c);
        }
    }
    public static void main(String[] arg){
    int c = 2;
    int n = 3;
    System.out.println(exponencia(c, n));
    }
}