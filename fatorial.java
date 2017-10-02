/* 4. Escreva um método recursivo que recebe um valor inteiro n e devolve seu fatorial.*/
public class fatorial {
    public static int fatorial(int num) {
        if (num <= 1) {
            return 1;

        } else {
        return num * (fatorial(num - 1));

        }
    }
    public static void main(String[] arg){
        int num = 3;
        System.out.println(num+" fatorial eh: "+fatorial(num));
    }
}
