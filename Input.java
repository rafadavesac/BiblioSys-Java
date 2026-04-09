public class Input {
    public static int scanInt(String message) {
        while (true) {
            try {
                int resultado = Integer.parseInt(IO.readln(message));
                return resultado;
            } catch (Exception e) {
                IO.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }

    public static double scanDouble(String message) {
        while (true) {
            try {
                double resultado = Double.parseDouble(IO.readln(message));
                return resultado;
            } catch (Exception e) {
                IO.println("Valor inválido! Digite um número decimal");
            }
        }
    }

    public static String scanString(String message) {
        while (true) { //loop infinito -> só cai fora quando o return é chamado
            String resultado = IO.readln(message);
            if (!resultado.isEmpty())
                return resultado.trim();
            else
                IO.println("Valor inválido! Digite um texto"); //se o valor estiver vazio cai aqui
        }
    }
}