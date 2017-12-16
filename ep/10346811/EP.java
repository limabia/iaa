/* Bianca Lima Santos 10346811 */
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
    private static List<int[]> itensDoCaminho;
    private static List<int[]> mochilaAtual;
    private static List<int[]> melhorMochila = new LinkedList<int[]>();
    private static int valorMelhorMochila = 0;
    private static int pesoMelhorMochila = 0;


    /* funcao responsavel pela leitura do arquivo */
    public static void leArquivo(String nome_arquivo) throws IOException {
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

    /* funcao responsavel pela impressao da saida */
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


    /* responsavel por marcar a partida e a chegada no mapa e a partir disso chamar a funcao que caminha no mapa */
    public static void encontraCaminho(int criterio){
        mapa [xPartida][yPartida] = "P";
        mapa [xChegada][yChegada] = "C";

        coordenadasAtuais = new LinkedList<int[]>();
        itensDoCaminho = new LinkedList<int[]>(); 
        
        caminha(xPartida, yPartida);

        if(melhoresCoordenadas != null && melhoresCoordenadas.size() > 0){
            imprimeSaida();
        }
        else {
           System.out.println("Caminho nao foi encontrado."); 
        }
    }


    /* verifica se o tem cabe na mochila dada sua capacidade e o peso do item */
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


    /* acha a melhor mochila dada sua capacidade e os itens disponiveis no mapa */
    public static LinkedList<int[]> melhorMochilaComOsItens(LinkedList<int[]> itens, int capacidade) {
        if (itens.size() == 0) {
            return itens;
        }
        int[] primeiroItem = itens.removeFirst();

        LinkedList<int[]> mochila1 = melhorMochilaComOsItens(new LinkedList<int[]>(itens), capacidade);

        if (capacidade < primeiroItem[3]) {
            return mochila1;
        }

        LinkedList<int[]> mochila2 = melhorMochilaComOsItens(new LinkedList<int[]>(itens), capacidade - primeiroItem[3]);

        int valorMochila1 = 0;
        int valorMochila2 = 0;

        for (int[] item : mochila1) {
            valorMochila1 += item[2];
        }
        for (int[] item : mochila2) {
            valorMochila2 += item[2];
        }

        if (valorMochila1 > valorMochila2 + primeiroItem[2]) {
            return mochila1;
        } else {
            mochila2.addFirst(primeiroItem);
            return mochila2;
        }
    }


    /* atualiza qual o melhor caminho a se fazer no mapa de acordo com o criterio escolhido pelo usuario */
    public static void atualizaMelhorCaminho() {
        if (criterio == 3){
            mochilaAtual = melhorMochilaComOsItens(new LinkedList<int[]>(itensDoCaminho), capacidadeMochila);
        } else {
            mochilaAtual = new LinkedList<int[]>(itensDoCaminho);
        }
        // encontra somente o melhor caminho, sem levar em consideracao os itens
        if (criterio == 1){
            if (melhoresCoordenadas == null){
                melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
            }
            else if (coordenadasAtuais.size() < melhoresCoordenadas.size()){
                melhoresCoordenadas = new LinkedList<int[]>(coordenadasAtuais);
            }
        }
        // encontra o melhor caminho levando em consideracao os itens (usado para os criterios 2 e 3)
        else {
            // primeiro caminho percorrido
            if (melhoresCoordenadas == null){
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
    }


    /* caminha pelo mapa procurando os caminhos possiveis */
    public static void caminha(int x, int y){
        int[] coord = new int[2];
        coord[0] = x;
        coord[1] = y;

        if (mapa[x][y].equals("C")){
            coordenadasAtuais.add(coord);

            atualizaMelhorCaminho();
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
                itensDoCaminho.add(conjuntoDeItens[i]);
                temItem = true;
            }
        }
        // backtracking no mapa
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
            itensDoCaminho.remove(itensDoCaminho.size()-1);
        }

        return;
    }


    public static void main(String[] args) {
        if (args.length == 2){
            String nome_arquivo = args[0];
            criterio = Integer.parseInt(args[1]); 
            if (criterio <= 3){
                // tentativa para leitura do arquivo
                try {
                    leArquivo(nome_arquivo);
                    encontraCaminho(criterio);
                }
                // exception para casos de erro na abertura do arquivo
                catch (IOException e) {
                    System.err.printf("Erro encontrado na abertura do arquivo: %s.\n", e.getMessage());
                }
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
