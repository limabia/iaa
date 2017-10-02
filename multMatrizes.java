/* Escreva um método iterativo que recebe duas matrizes A (de dimensão n×m) e B (de dimensão
m × p) de valores inteiros, e devolve o produto de A por B.*/
class multMatrizes{

    public static void main(String[] arg){
		int [][] a = 	{{2, 5},
						{3, 8}};

		int [][] b = 	{{2, 5, 9, 4},
						{3, 8, 1, 5}};

		multiplica(a, b);
	}

    static void multiplica(int[][] a, int[][] b){
		int [][] resultado = new int [6][6];
		
		for(int i=0; i<a.length; i++){
			
			for(int j=0; j<a.length; j++){
			
				resultado[i][j] = a[i][j] * b[i][j];
				System.out.println(resultado[i][j]+"\n");
			}
		}
	}

}



for (int i=0; i<n; i++) {
    for (int j=0; j<p; j++) {
        C[i][j] = 0;
        for (int k=0; k<m; k++) {
            C[i][j] += A[i][k]*B[k][j];
        }
    }
}
