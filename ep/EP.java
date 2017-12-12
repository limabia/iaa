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
    private static List<int[]> coordenadasAtuais;
    private static List<int[]> melhoresCoordenadas;
    


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
        mapa [xPartida] [yPartida] = "P";
        mapa [xChegada][yChegada] = "C";

        coordenadasAtuais = new LinkedList<int[]>();

        
        achaQualquerCaminho(xPartida, yPartida);
             
        // TODO verificar se existe um caminho 

        System.out.printf("Caminho de tamanho: %d\n", melhoresCoordenadas.size());
        for (int[] coord : melhoresCoordenadas){
            System.out.printf("%s %s\n", coord[0], coord[1]);
        }

    }


    public static void achaQualquerCaminho(int x, int y){ 
        for (int i= 0; i < qntLinhas; i++){
            for (int j=0; j < qntColunas; j++){
                System.out.printf(mapa[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("\n");

        int[] coord = new int[2];
        coord[0] = x;
        coord[1] = y;

        if (mapa[x][y].equals("C")){
            coordenadasAtuais.add(coord);

            if (melhoresCoordenadas == null){
                melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
            }
            else if (coordenadasAtuais.size() < melhoresCoordenadas.size()){
                melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
            }
            coordenadasAtuais.remove(coord);
            return;
        }

        if (mapa[x][y].equals("X") || mapa[x][y].equals("*")){
            return;
        }

        coordenadasAtuais.add(coord);
        mapa[x][y] = "*";
        

        if (y < qntColunas-1){
            achaQualquerCaminho(x, y+1);
        }
        if (x > 0){
            achaQualquerCaminho(x-1, y);
        }
        if (y > 0){
            achaQualquerCaminho(x, y-1);   
        }
        if (x < qntLinhas-1){
            achaQualquerCaminho(x+1, y);    
        }
        mapa[x][y] = ".";
        coordenadasAtuais.remove(coord);
        return;
    }


    public static void main(String[] args) {
        if (args.length == 2){
            String nome_arquivo = args[0];
            criterio = Integer.parseInt(args[1]); 
            if (criterio <= 3){
                leArquivo(nome_arquivo);
                encontraCaminho(criterio);
            }
            else{
                System.err.println("Informe um criterio valido para percorrer o labirinto.");
            }
        }
        else {
            System.err.println("Adicione os parametros nome do arquivo e criterio ao rodar o programa.");
        } 
    }
}
