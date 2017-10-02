/*5. Escreva um método recursivo que calcula o termo ai de uma progressão aritmética de termo inicial
a0 e razão r 
(obs: o método a ser implementado não será a forma mais aficiente de determinar o
valor do termo ai, mas a ideia aqui é exercitar o pensamento recursivo).*/
/* calculo pra termos da pa : ((ai + (n-1))r */
public class pa {
    public static int calcPa(int a0, int razao, int i) {
        if (i == 0) {
            return a0;
        } else {
        	// ai = ((ai-1)+r)
        	return ((calcPa(a0, razao, i-1)) + razao);
        }
    }
    public static void main(String[] arg){
        int a0 = 1; 
        int razao = 2; // razao da PA
        int i = 1; // termo da PA 
        System.out.println("O " + (i+1) +"º termo procurado da PA eh: "+ calcPa(a0, razao, i));
        //  PA: a0 = 1, a1 = 3, a2 = 5, a3 = 7, ...
    }
}
