/*Empregando a estratégia de tentativa e erro, implemente um método que encontre um caminho
ligando um ponto de partida a um ponto de chegada em um mapa. O caminho encontrado não
precisa ser necessariamente o mais curto, ou seja, qualquer caminho que ligue a origem ao destino
é válido. Assuma que o mapa é representado por uma matriz de caracteres onde o caractere '.'
indica caminho livre e o caractere 'X' caminho bloqueado. Assuma ainda que os pontos de partida
e destino são dados pelas suas coordenadas na matriz, e que os movimentos podem ser executados
apenas nas seguintes direções: para cima, para baixo, para a esquerda ou para a direita. 

					Exemplo:
				[X][X][.][.][.]
  (inicio) 	 -> [.][.][.][X][X]
				[.][X][.][.][X]
				[.][X][X][.][X] 
				[.][.][.][.][.] ->    (chegada) */
class Cinco {

	public static boolean chegou (int i, int j, int[]fim) {
		if ((i == fim[0]) && (j == fim[1])) return true;
		return false; 
	}

	static String [][] normalMapa = null;

	public static boolean cinco (String[][] mapa, int[] fim, int passo, int i, int j) {
		if (i < mapa.length && j < mapa[0].length && i >= 0 && j >= 0) {
			if (mapa[i][j] == 'X') return false;
			if (mapa[i][j] == '.') {

				if (chegou (i, j, fim)) {
				System.out.println("Caminho encontrado!");
				System.out.println(passo+" passos.");
				normalMapa = mapa;
				return true;	
				}
				passo++;	
				mapa[i][j] = '0';

				if (cinco(mapa, fim, passo, i+1, j)) return true;
				if (cinco(mapa, fim, passo, i-1, j)) return true;
				if (cinco(mapa, fim, passo, i, j+1)) return true;
				if (cinco(mapa, fim, passo, i, j-1)) return true;
				mapa[i][j] = '.';
			}
		}
			return false;
	}

	public static void main(String [] args){
		String[][] matriz = { {" . ", " . ", " . ", " . ", " . "},
							  {" X ", " . ", " X ", " X ", " . "},
							  {" . ", " . ", " X ", " . ", " . "},
							  {" X ", " . ", " X ", " . ", " X "},
							  {" X ", " . ", " . ", " . ", " . "} };
		int[] fim = {2,1};					  
		cinco (matriz,fim, 2, 0, 0);
	}
}