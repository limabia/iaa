import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 
public class EP {
 
    public static void main(String[] args) {
        if (args.length == 2){
            //  dados fornecidos pelo usuario: nome do arquivo com o mapa e qual criterio de percurso sera usado
            String nome_arquivo = args[0];
            int criterio = Integer.parseInt(args[1]); 
            // tentativa para leitura do arquivo
            try {
                FileReader arq = new FileReader(nome_arquivo);
                BufferedReader lerArq = new BufferedReader(arq);
     
                String primeiralinha = lerArq.readLine();

                String[] atributosMapa = primeiralinha.split(" ");
                int qntLinhas = Integer.parseInt(atributosMapa[0]);
                int qntColunas = Integer.parseInt(atributosMapa[1]);

                System.out.printf("%s\n", qntLinhas);
                System.out.printf("%s\n", qntColunas);

                String[][] mapa = new String [qntLinhas] [qntColunas];
                
                for (int i=0; i < qntLinhas; i++){
                    String linha = "";
                    linha = lerArq.readLine();
                    for (int j=0; j < qntColunas; j++){
                        String elementoMapa = linha.substring(j, j+1);
                        mapa [i][j] = elementoMapa;
                    }
                }

                int qntItens = Integer.parseInt(lerArq.readLine());
                int[][] conjuntoDeItens = new int[qntItens] [4];
                
                for (int x=0; x < qntItens; x++){
                    String linhaItem = lerArq.readLine();
                    //System.out.printf(linhaItem);
                    String[] itens = linhaItem.split(" ");
                    conjuntoDeItens[x] [0] =  Integer.parseInt(itens[0]); // linha
                    conjuntoDeItens[x] [1] =  Integer.parseInt(itens[1]); // coluna
                    conjuntoDeItens[x] [2] =  Integer.parseInt(itens[2]); // valor
                    conjuntoDeItens[x] [3] =  Integer.parseInt(itens[3]); // peso
                }

                // capacidade da mochila
                int capacidadeMochila = Integer.parseInt(lerArq.readLine());

                // coordenadas de partida

                // coordenadas de chegada
                arq.close();
            } 
            // exception para casos de erro na abertura do arquivo
            catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo, tente novamente. \n Erro encontrado: %s.\n", e.getMessage());
            }
        }
        // mensagem para quando o usuario nao informar os 2 parametros necessarios
        else {
            System.err.println("eh preciso adicionar os parâmetros nome do arquivo e criterio ao rodar o programa.");
        } 
    }
}
