import java.util.List;

//Dependencias -> o main precisa disso para funcionar
LivroService service = new LivroService();

//Main mostra as coisas pro usuário -> Camada de Interface

void main() {
    String menu = """
            ===== SysBiblio =====
            1- Cadastrar livro
            2- Listar livros
            3- Pesquisar
            4- Ordenar livros
            5- Remover livro
            6- Editar livro
            0- Sair
            """;

    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite uma opção: ");
        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> {
                    String menuPesquisa = """
                            ===== PESQUISAR POR =====
                            1- Título
                            2- Autor
                            3- Ano
                            4- Número de páginas
                            """;
                    IO.println(menuPesquisa);
                    int opcaoPesquisa = Input.scanInt("Digite uma opção: ");
        
                        switch (opcaoPesquisa) {
                        case 1 -> pesquisar();
                        case 2 -> pesquisarAutor();
                        case 3 -> pesquisarAno();
                        case 4 -> pesquisarNumPag();
                        default -> IO.println("Opção inválida");
                        }
                    
                }
                case 4 -> { String menuOrdenacao = """
                            ===== ORDENAR POR =====
                            1- Títulos (alfabeticamente)
                            2- Autor (alfabeticamente)
                            3- Ano de publicação (mais recente)
                            4- Ano de publicação (mais antigo)
                            """;
                    IO.println(menuOrdenacao);
                    int opcaoOrdenacao = Input.scanInt("Digite uma opção: ");
                    
                        switch(opcaoOrdenacao){
                            case 1 -> ordenarTitulos();
                            case 2 -> ordenarAutores();
                            case 3 -> ordenarAno("decrescente");
                            case 4 -> ordenarAno("crescente");
                            default -> IO.println("Opção inválida");
                        }
                    
                    }
                case 5 -> remover();
                case 6 -> editar();
                case 0 -> IO.println("Até breve!");
                default -> IO.println("Opção inválida");

            }
        } catch (Exception e) {
            IO.println("ERRO: " + e.getMessage());
        }
        IO.readln("Pressione Enter para continuar");
    } while (opcao != 0);
}

void cadastrar() throws Exception {
    String titulo = Input.scanString("Digite o título do livro: ");
    String autor = Input.scanString("Digite o autor do livro: ");
    int anoPublicacao = Input.scanInt("Digite o ano de publicação do livro: ");
    int numeroPagina = Input.scanInt("Digite o número de páginas do livro: ");

    Livro novoLivro = new Livro(titulo, autor, anoPublicacao, numeroPagina);

    //invocar metodo cadastrar da service
    service.cadastrar(novoLivro);

    IO.println("Livro cadastrado com sucesso!");
}

void listar() {

    List<Livro> livros = service.listar();

    imprimirLista(livros);
}

void pesquisar() {

    String pesquisa = Input.scanString("Digite parte do título: ");
    
    List<Livro> livros = service.pesquisar(pesquisa);//retorna a lista dos livros compativeis com a pesquisa

    imprimirLista(livros);
}

void pesquisarAutor(){
    String pesquisa = Input.scanString("Digite parte do nome do autor: ");

    List<Livro> autores = service.pesquisarAutor(pesquisa);

    imprimirLista(autores);
}

void pesquisarAno()throws Exception{
    int ano1 = Input.scanInt("Digite a ano mínimo: ");
    int ano2 = Input.scanInt("Digite a ano máximo: ");

    List<Livro> anosCompativeis = service.pesquisarAno(ano1, ano2);

    imprimirLista(anosCompativeis);
}

void pesquisarNumPag()throws Exception{
    int minPag = Input.scanInt("Digite o número mínimo de páginas: ");
    int maxPag = Input.scanInt("Digite o número máximo de páginas: ");

    List<Livro> numPagCompativeis = service.pesquisarNumPag(minPag, maxPag);

    imprimirLista(numPagCompativeis);
}

void ordenarTitulos(){
    List<Livro> livrosAlfebeticamenteOrdenados = service.ordenarAlfabeticamente("titulo");

    imprimirLista(livrosAlfebeticamenteOrdenados);
}

void ordenarAutores(){
    List<Livro> autoresAlfebeticamenteOrdenados = service.ordenarAlfabeticamente("autor");

    imprimirLista(autoresAlfebeticamenteOrdenados);
}

void ordenarAno(String topico){
    List<Livro> ordenacaoAnos = service.ordenarAno(topico);

    imprimirLista(ordenacaoAnos);
}

void remover() throws Exception{
    List<Livro> livros = service.listar();//retorna o acervo inteiro
    imprimirLista(livros);

    int indice = Input.scanInt("Digite o índice do livro que você deseja remover: ");

    service.remover(indice);
    IO.println("Livro removido com sucesso!");
}

void imprimirLista(List<Livro> livros){

    if (livros.isEmpty()){
        IO.println("Nenhum livro cadastrado");
        return;
    }

    IO.println("===== ACERVO =====");
    int i = 1;
    for (Livro livro : livros) {
        IO.println(i++ + "- " + livro); //i++ -> incrementa 1 só depois da primeira iteração; != ++i
    }
}

void editar()throws Exception{
    List<Livro> livros = service.listar();//retorna o acervo inteiro
    imprimirLista(livros);

    int indice = Input.scanInt("Digite o índice do livro que você deseja editar: ");

    service.validarIndice(indice);

    String novoTitulo = Input.scanString("Digite o novo nome do livro: ");
    String novoAutor = Input.scanString("Digite o novo autor do livro: ");
    int novoAno = Input.scanInt("Digite o novo ano do livro: ");
    int novoPag = Input.scanInt("Digite a nova quantia de páginas do livro: ");

    Livro livroEditado = new Livro(novoTitulo, novoAutor, novoAno, novoPag);

    service.editar(indice, livroEditado);
    IO.println("Livro editado com sucesso!");
}