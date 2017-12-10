import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
public class EP {
 
    public static void main(String[] args) {
        if (args.length == 2){
            String nome_arquivo = args[0];
            int criterio = Integer.parseInt(args[1]); 
            
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
                String[] conjuntoDeItens = new String[qntItens];
                
                for (int x=0; x < qntItens; x++){
                    linhaItem = lerArq.readLine();
                    String[] item = new String [4]; 
                    item[0] = linhaItem.substring(0,1);
                    item[1] = linhaItem.substring(1,2);
                    item[2] = linhaItem.substring(2,3);
                    item[3] = linhaItem.substring(3,4);
                    conjuntoDeItens[x] = item;
                }
                arq.close();
            } 
            catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo, tente novamente. \n Erro encontrado: %s.\n", e.getMessage());
            }
            System.out.println();
        }

        else {
            System.err.println("eh preciso adicionar os parâmetros nome do arquivo e criterio ao rodar o programa.");
        } 
    }
}
