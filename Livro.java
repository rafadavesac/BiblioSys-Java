public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPagina;

    public Livro(){ //método construtor default -> não possui nenhum parametro de entrada

    }

    //botão direito -> Source Action -> Generate Constructors
    public Livro(String titulo, String autor, int anoPublicacao, int numeroPagina) { 
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.numeroPagina = numeroPagina;
    }

    //botão direito -> Source Action -> Generate Getters and Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    @Override
    public String toString() {
        return "Título: " + this.titulo 
                + " | Autor:  " + this.autor 
                + " | Ano: " + this.anoPublicacao 
                + " | N. Pág.: " + this.numeroPagina;
    }

    
    
}
