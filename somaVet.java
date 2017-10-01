/* 2. Escreva um método iterativo que recebe um a a de valores inteiros e um valor x, e determina
todos os pares (i, j) de índices tais que a[i] + a[j] = x.*/

class somaVet{
	public static void main(String[] args){
        int[] a = {1,2,3,4,5};
        int x = 4;
        determinaPares(a, x);
    }
    public static void determinaPares(int[] a, int x) {
    	for(int i = 0; i < a.length; i++){
            int elemAtual = a[i]; 
        	
        	for(int n = 0; n < a.length; n++){
        		int numVerifica = a[n] + elemAtual;
        		
        		if(numVerifica == x){
        			System.out.println("elemento 1: "+elemAtual+" elemento 2: "+a[n]+" numero proc: "+x+" soma obtida: "+numVerifica+"\n");
        		}
        	}
        }
    }
}