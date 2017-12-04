class InsertionSort {	
	
	public static void insertionSort(int[] vetor){	
		for (int i = 0; i < vetor.length; i++){
			int aux = vetor[i];
			int j = i;
			while ((j > 0) && (vetor[j-1] > aux)){
				vetor[j] = vetor[j-1];
				j -= 1;
			}
			vetor[j] = aux;
			System.out.println(vetor[j]);
		}
	}

	public static void main(String [] args){
		int[] y = { 2, 1, 5, 6, 3};
		insertionSort(y);
	}
}