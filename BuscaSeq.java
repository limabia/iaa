class BuscaSeq {	
	
	public static int buscaSeq(int[] a, int chave, int i){ 
		if (i >= 0 && i < a.length){
			if( a[i] == chave){
				return i;
			}
			else{
				return buscaSeq(a, chave, i+1);
			}
		}
		return -1;
	}

	public static int buscaSeq(int[] a, int chave, int ini, int fim) {
		  if (ini == fim) {
		   if (chave == a[ini]) {
		    return ini;
		   } else {
		    return -1;
		   }
		  } else {
		   int med = (ini + fim)/2;
		   int buscaEsquerda = buscaSeq(a, chave, ini, med);
		   int buscaDireita = buscaSeq(a, chave, med+1, fim);
		   if (buscaEsquerda != -1) return buscaEsquerda;
		   if (buscaDireita != -1) return buscaDireita;
		   return -1;
		  }
	}
	  
	public static void main(String [] args){
		int[] y = { 2, 1, 5, 6, 3};
		System.out.println(buscaSeq(y, 3, 0));
		System.out.println(buscaSeq(y, 3, 0, y.length - 1));
	}
}