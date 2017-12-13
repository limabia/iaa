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
    private static List<int[]> mochilaAtual;
    private static List<int[]> melhorMochila = new LinkedList<int[]>();
    private static int valorMelhorMochila = 0;
    private static int pesoMelhorMochila = 0;


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


    public static void imprimeSaida(){
        // tamanho do Caminho
        System.out.printf("%d\n", melhoresCoordenadas.size()); 
        // coordenadas do caminho
        for (int[] coord : melhoresCoordenadas){
            System.out.printf("%s %s\n", coord[0], coord[1]);
        }
        // quantidade de itens colecionados, valor total e peso arrecadado
        System.out.printf("%d %d %d\n", melhorMochila.size(), valorMelhorMochila, pesoMelhorMochila);
        // coordenadas dos itens selecionados
        for (int[] item : melhorMochila){
            System.out.printf("%d %d\n", item[0], item[1]);
        }    
    }


    public static void encontraCaminho(int criterio){
        mapa [xPartida] [yPartida] = "P";
        mapa [xChegada][yChegada] = "C";

        coordenadasAtuais = new LinkedList<int[]>();
        mochilaAtual = new LinkedList<int[]>(); 
        
        caminha(xPartida, yPartida);

        if(melhoresCoordenadas.size() != 0){
            imprimeSaida();
        }
    }


    public static boolean cabeMochila(int pesoItem){
        int pesoMochila = 0;
        for(int[] item : mochilaAtual){
            pesoMochila += item [3];
        }
        int capacidadeRestante = capacidadeMochila - pesoMochila;
        if (capacidadeRestante >= pesoItem ){
            return true;
        }
        return false;
    }


    public static void caminha(int x, int y){
        int[] coord = new int[2];
        coord[0] = x;
        coord[1] = y;

        if (mapa[x][y].equals("C")){
            coordenadasAtuais.add(coord);

            // encontra somente o melhor caminho, sem levar em consideracao os itens
            if (criterio == 1){

                if (melhoresCoordenadas == null){
                    melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
                }
                else if (coordenadasAtuais.size() < melhoresCoordenadas.size()){
                    melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
                }
            }
            // encontra o melhor caminho levando em consideracao os itens (usado para os creterios 2 e 3)
            else {
                // primeiro caminho percorrido
                if (melhorMochila == null){
                    melhorMochila = new LinkedList<int[]>(mochilaAtual);
                    melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
                    for (int[] item : melhorMochila){
                        pesoMelhorMochila += item[3];
                        valorMelhorMochila += item[2];
                    }
                }
                // demais caminhos
                else {                    
                    int valorMochilaAtual = 0;
                    int pesoMochilaAtual = 0;

                    for (int[] item : mochilaAtual){
                        valorMochilaAtual += item[2];
                        pesoMochilaAtual += item[3];   
                    }
                    
                    if (valorMochilaAtual > valorMelhorMochila) {
                        pesoMelhorMochila = pesoMochilaAtual;
                        valorMelhorMochila = valorMochilaAtual;
                        melhorMochila = new LinkedList<int[]>(mochilaAtual);
                        melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
                    }
                }
            }
            coordenadasAtuais.remove(coord);
            return;
        }
        // verifica se a coordenada eh invalida para passagem ou porque a posicao esta bloqueada ou porque ja foi um caminho percorrido 
        if (mapa[x][y].equals("X") || mapa[x][y].equals("*")){
            return;
        }
        coordenadasAtuais.add(coord);
        mapa[x][y] = "*";
        // add itens a mochila 
        boolean temItem = false;
        for (int i=0; i < qntItens; i++){
            if(conjuntoDeItens[i][0] == x && conjuntoDeItens[i][1] == y){
                if (criterio == 2 || cabeMochila(conjuntoDeItens[i][3])){
                    mochilaAtual.add(conjuntoDeItens[i]);
                    temItem = true;
                }
            }
        }
        // backtracking 
        if (y < qntColunas-1){
            caminha(x, y+1);
        }
        if (x > 0){
            caminha(x-1, y);
        }
        if (y > 0){
            caminha(x, y-1);   
        }
        if (x < qntLinhas-1){
            caminha(x+1, y);    
        }
        
        mapa[x][y] = ".";
        coordenadasAtuais.remove(coord);
        if (temItem){    
            mochilaAtual.remove(mochilaAtual.size()-1);
        }

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
