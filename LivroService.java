import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Colocar e descrever a melhoria nova livre no read.me

//Camada de Regra de Negócio

//Não interage com o usuário
//não usa IO.println, IO.readln ...

public class LivroService {

    private List<Livro> acervo = new ArrayList<>(); // Banco de dados em memória (quando desliga, perde os dados)

    public void cadastrar(Livro novoLivro) throws Exception {
        // Validações e formatações do título, autor, ano e pág
        validar(novoLivro);

        // Valida se já existe algum livro igual cadastrado
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())
                    && livro.getAutor().equalsIgnoreCase(novoLivro.getAutor())
                    && livro.getAnoPublicacao() == novoLivro.getAnoPublicacao()) {
                throw new Exception("Já existe livro cadastrado com esse Título, Autor e Ano de Publicação");
            }
        }
        // Nesta parte estaria a camada Repository (Banco de dados)
        // nesse exemplo não usaremos repositórios
        acervo.add(novoLivro);
    }

    public List<Livro> listar() {
        return acervo;
    }

    public List<Livro> pesquisar(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        titulo = titulo.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getTitulo().contains(titulo)) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> pesquisarAutor(String autor) {
        List<Livro> autoresEncontrados = new ArrayList<>();
        autor = autor.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getAutor().contains(autor)) {
                autoresEncontrados.add(livro);
            }
        }
        return autoresEncontrados;
    }

    public List<Livro> pesquisarAno(int anoMin, int anoMax)  throws Exception {
        if (anoMin > anoMax) {
            throw new Exception("Ano mínimo não pode ser maior que o ano máximo!");
        }

        List<Livro> anosCompativeis = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() >= anoMin && livro.getAnoPublicacao() <= anoMax) {
                anosCompativeis.add(livro);
            }
        }
        return anosCompativeis;
    }

    public List<Livro> pesquisarNumPag(int minPag, int maxPag) throws Exception{
        if (minPag > maxPag) {
        throw new Exception("Página mínima não pode ser maior que a página máxima!");
        }

        List<Livro> numPagCompativeis = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getNumeroPagina() >= minPag && livro.getNumeroPagina() <= maxPag) {
                numPagCompativeis.add(livro);
            }
        }
        return numPagCompativeis;
    }

    public List<Livro> ordenarAlfabeticamente(String topico) {
        List<Livro> ordenacaoAlfabetica = new ArrayList<>(acervo);

        int n = acervo.size();

        for (int i = 0; i < n - 1; i++) { // quantia de iteracoes
            for (int j = 1; j < n - 1; j++) { // percorre o proximo indice

                Livro atual = ordenacaoAlfabetica.get(j);
                Livro proximo = ordenacaoAlfabetica.get(j + 1);

                if (topico == "titulo") {
                    if (atual.getTitulo().compareTo(proximo.getTitulo()) > 0) { // atual vem depois do proximo alfabeticamente?
                        ordenacaoAlfabetica.set(j, proximo); // troca de lugar
                        ordenacaoAlfabetica.set(j + 1, atual);
                    }
                }
                if (topico == "autor") {
                    if (atual.getAutor().compareTo(proximo.getAutor()) > 0) {
                        ordenacaoAlfabetica.set(j, proximo);
                        ordenacaoAlfabetica.set(j + 1, atual);
                    }
                }
            }
        }
        return ordenacaoAlfabetica;
    }

    public List<Livro> ordenarAno(String topico) {
    List<Livro> ordenacaoAno = new ArrayList<>(acervo);

    int n = ordenacaoAno.size();

    for (int i = 0; i < n - 1; i++) {
        for (int j = 1; j < n - 1; j++) {

            Livro atual = ordenacaoAno.get(j);
            Livro proximo = ordenacaoAno.get(j + 1);

            if (topico.equals("crescente")){
                if (atual.getAnoPublicacao() > proximo.getAnoPublicacao()) {  //
                ordenacaoAno.set(j, proximo); //o ano 'menor' aparece antes
                ordenacaoAno.set(j + 1, atual);
                }
            }
            if (topico.equals("decrescente")){
                if (atual.getAnoPublicacao() < proximo.getAnoPublicacao()) { 
                ordenacaoAno.set(j, proximo);
                ordenacaoAno.set(j + 1, atual);
                }
            }
        }
    }
    return ordenacaoAno;
}

    public void remover(int indice) throws Exception {
        // Validaçao do indice
        validarIndice(indice);

        acervo.remove(indice - 1);
    }

    public void editar(int indice, Livro livroEditado) throws Exception {
        // Validaçao do indice -> é chamada no Main logo após o input do indice e antes
        // do usuário inserir os novos dados do livro

        // Validações gerais do livro
        validar(livroEditado);

        // encontra o livro pelo indice e faz as edições
        Livro livroIndice = acervo.get(indice - 1);
        livroIndice.setTitulo(livroEditado.getTitulo());
        livroIndice.setAutor(livroEditado.getAutor());
        livroIndice.setAnoPublicacao(livroEditado.getAnoPublicacao());
        livroIndice.setNumeroPagina(livroEditado.getNumeroPagina());

    }

    public void validarIndice(int indice) throws Exception {
        // Valida índice
        if (indice <= 0 || indice > acervo.size()) {
            throw new Exception("Índice inválido!");
        }
    }

    public void validar(Livro livroValidar) throws Exception {
        // int, double (tipos primitivos) -> quando vazios são 0
        // String, Double (objetos) -> quando vazios são null

        if (livroValidar == null) {
            throw new Exception("Objeto nulo");
        }

        // Validação do Título
        if (livroValidar.getTitulo() == null || livroValidar.getTitulo().isEmpty()) {
            throw new Exception("Título inválido!");
        }
        // Formatação do Título
        livroValidar.setTitulo(livroValidar.getTitulo().trim().toUpperCase());

        // Validação Autor
        if (livroValidar.getAutor() == null || livroValidar.getAutor().isEmpty()) {
            throw new Exception("Autor inválido!");
        }
        // Formatação Autor
        livroValidar.setAutor(livroValidar.getAutor().trim().toUpperCase());

        // Validação do Ano de Publicação
        if (livroValidar.getAnoPublicacao() < 1900 || livroValidar.getAnoPublicacao() > LocalDate.now().getYear()) {
            throw new Exception("Ano de publicação inválido");
        }

    }
}
