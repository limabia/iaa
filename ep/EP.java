import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
public class EP {
 
    public static void main(String[] args) {
        try {    
            String nome_arquivo = args[0];
            int criterio = args[1];    
            System.out.printf("\nConteúdo :\n");
            
            try {
                FileReader arq = new FileReader(nome_arquivo);
                BufferedReader lerArq = new BufferedReader(arq);
     
                String linha = lerArq.readLine();
                System.out.printf("primeira %s\n", linha);

                while (linha != null) {
                    System.out.printf("%s\n", linha);
     
                    linha = lerArq.readLine();
                }
     
                arq.close();
            } 
            catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            }
            System.out.println();
        }

        catch(IOException e) {
            System.err.println("eh preciso adicionar os parâmetros nome do arquivo e criterio ao rodar o programa.");
        } 
    }
}
