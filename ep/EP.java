import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
 

public class EP {
    
    private static int criterio;
    private static int qntLinhas;
    private static int qntColunas;
    private static String[][] mapa;
    private static int qntItens;
    private static int[][] conjuntoDeItens;
    private static int capacidadeMochila;
    private static int xPartida;
    private static int yPartida;
    private static int xChegada;
    private static int yChegada;
    private static List<int[]> coordenadas;
    


    public static void leArquivo(String nome_arquivo){
        // tentativa para leitura do arquivo
        try {
            // abertura do arquivo
            FileReader arq = new FileReader(nome_arquivo);
            BufferedReader lerArq = new BufferedReader(arq);
            // extracao do numero de linhas e colunas do mapa
            String primeiralinha = lerArq.readLine();
            String[] atributosMapa = primeiralinha.split(" ");
            qntLinhas = Integer.parseInt(atributosMapa[0]);
            qntColunas = Integer.parseInt(atributosMapa[1]);
            // definicao da matriz mapa e preenchimento dela de acordo com os valores do txt
            mapa = new String [qntLinhas] [qntColunas];
            for (int i=0; i < qntLinhas; i++){
                String linha = "";
                linha = lerArq.readLine();
                for (int j=0; j < qntColunas; j++){
                    String elementoMapa = linha.substring(j, j+1);
                    mapa [i][j] = elementoMapa;
                }
            }
            // definicao da matriz de itens e preenchimento dela de acorod com os valores do arquivo
            qntItens = Integer.parseInt(lerArq.readLine());
            conjuntoDeItens = new int[qntItens] [4];
            for (int x=0; x < qntItens; x++){
                String linhaItem = lerArq.readLine();
                String[] itens = linhaItem.split(" ");
                conjuntoDeItens[x] [0] =  Integer.parseInt(itens[0]); // linha
                conjuntoDeItens[x] [1] =  Integer.parseInt(itens[1]); // coluna
                conjuntoDeItens[x] [2] =  Integer.parseInt(itens[2]); // valor
                conjuntoDeItens[x] [3] =  Integer.parseInt(itens[3]); // peso
            }
            // capacidade maxima da mochila
            capacidadeMochila = Integer.parseInt(lerArq.readLine());
            // coordenadas de partida
            String cooPt = lerArq.readLine();
            String[] coordenadasPartida = cooPt.split(" ");
            xPartida = Integer.parseInt(coordenadasPartida[0]);
            yPartida = Integer.parseInt(coordenadasPartida[1]);
            // coordenadas de chegada
            String cooCh = lerArq.readLine();
            String[] coordenadasChegada = cooCh.split(" ");
            xChegada = Integer.parseInt(coordenadasChegada[0]);
            yChegada = Integer.parseInt(coordenadasChegada[1]);
            // fechando arquivo
            arq.close();
        } 
        // exception para casos de erro na abertura do arquivo
        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo, tente novamente. \n Erro encontrado: %s.\n", e.getMessage());
        }
    }


    public static void encontraCaminho(int criterio){
        if(criterio == 1){
            System.out.println("caminho minimo");
        }
        else if(criterio == 2){
            System.out.println("caminho maximo valor atributos");
        }
        else if(criterio == 3){
            System.out.println("caminho maximo com limitante da mochila");
        }
        else {
            System.out.println("criterio errado");
        }

        mapa [xPartida] [yPartida] = "P";
        mapa [xChegada][yChegada] = "C";

        coordenadas = new LinkedList<int[]>();
        achaQualquerCaminho(xPartida, yPartida);

        for (int i= 0; i < qntLinhas; i++){
            for (int j=0; j < qntColunas; j++){
                System.out.printf(mapa[i][j]);
            }
            System.out.printf("\n");
        }

    }


    public static boolean achaQualquerCaminho(int x, int y){ 
        if (mapa[x][y] == "C"){
            int[] coord = new int[2];
            coord[0] = x;
            coord[0] = y;
            coordenadas.add(coord);
            return true;
        }
        if (mapa[x][y] == "#" || mapa[x][y] == "*"){
            return false;
        }
        mapa[x][y] = "*";
        boolean resultado; 
        if (y < qntColunas-1){
            resultado = achaQualquerCaminho(x, y+1);
            if (resultado) { 
                return true;
            }
        }
        if (x > 0){
            resultado = achaQualquerCaminho(x-1, y);
            if (resultado) { 
                return true;
            }
        }
        if (y > 0){
            resultado = achaQualquerCaminho(x, y-1);
            if (resultado) { 
                return true;
            }     
        }
        if (x < qntLinhas-1){
            resultado = achaQualquerCaminho(x+1, y);
            if (resultado) { 
                return true;
            }     
        }

        mapa[x][y] = " ";
        
        return false;
    }


    public static void main(String[] args) {
        if (args.length == 2){
            String nome_arquivo = args[0];
            criterio = Integer.parseInt(args[1]); 
            leArquivo(nome_arquivo);
            encontraCaminho(criterio);
        }
        else {
            System.err.println("eh preciso adicionar os parâmetros nome do arquivo e criterio ao rodar o programa.");
        } 
    }
}
