public class LivroFisico extends Livro { //subclasse da superClasse Livro
    private int numeroExemplares;
    private String dimensoes;

    //Método Construtor com parâmetros
    public LivroFisico(String titulo, String autor, int anoPublicacao, int numeroPagina, int numeroExemplares, String dimensoes){
        /*this.setTitulo(titulo);
        this.setAutor(autor);
        this.setAnoPublicacao(anoPublicacao);
        this.setNumeroPagina(numeroPaginas);*/

       //Invoca o método construtor da SUPER CLASSE (Livro)
       super(titulo, autor, anoPublicacao, numeroPagina);
        this.numeroExemplares = numeroExemplares;
        this.dimensoes = dimensoes;
    }

    // isso é criado para podermos acessar os valores que são private fora dessa classe (por exemplo, para acessa-los / usá-los no main)
    public int getNumeroExemplares() {
        return numeroExemplares;
    }
    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }
    public String getDimensoes() {
        return dimensoes;
    }
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override
    public String toString() { 
        return super.toString() + " | N. Exemplares: " + this.numeroExemplares
                + " | Dimensões: " + this.dimensoes;
    }
    

    
}
