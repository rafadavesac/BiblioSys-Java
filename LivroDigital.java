public class LivroDigital extends Livro{
    private double tamanhoArquivo;
    private String formatoArquivo;

    //Método Construtor
    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPagina, double tamanhoArquivo,
            String formatoArquivo) {
        super(titulo, autor, anoPublicacao, numeroPagina);
        this.tamanhoArquivo = tamanhoArquivo;
        this.formatoArquivo = formatoArquivo;
    }

    //Getters and Setters
    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    @Override // "este método está substituindo um método herdado de uma superclasse ou interface".
    public String toString() { 
        return super.toString()
                + " | Tamanho (MB): " + this.tamanhoArquivo
                + " | Formato : " + this.formatoArquivo;
    }
}
