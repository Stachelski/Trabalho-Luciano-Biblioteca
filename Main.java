import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    static Biblioteca biblio = new Biblioteca();
    static Scanner input = new Scanner(System.in);

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro. Por favor informe um número inteiro.");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void listar() {
        var livros = biblio.pesquisarTodos();
        livros.sort(Comparator.comparing(Livro::getTitulo));

        System.out.println("======== LISTA DE LIVROS =========");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano: " + livro.getAnoPublicacao());
            System.out.println("N. Páginas: " + livro.getnPaginas());
        }
    }

    private static void adicionar() {
        Livro novoLivro = new Livro();
        System.out.println("======== ADICIONANDO NOVO LIVRO ========");

        System.out.print("Informe o título do livro: ");
        String titulo = input.nextLine();
        novoLivro.setTitulo(titulo);

        System.out.print("Informe o nome do autor: ");
        String autor = input.nextLine();
        if (autor.isEmpty()) {
            System.out.println("Erro: Autor não pode ser nulo ou vazio.");
            return;
        }
        novoLivro.setAutor(autor);

        int anoPublicacao;
        do {
            anoPublicacao = inputNumerico("Informe o ano de publicação (1400 até " + LocalDate.now().getYear() + "): ");
        } while (anoPublicacao < 1400 || anoPublicacao > LocalDate.now().getYear());
        novoLivro.setAnoPublicacao(anoPublicacao);

        int numPaginas;
        do {
            numPaginas = inputNumerico("Informe o número de páginas (maior que 0): ");
        } while (numPaginas <= 0);
        novoLivro.setnPaginas(numPaginas);

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        input.nextLine(); // espera o usuário dar um enter para continuar
    }

    private static void pesquisarPorTitulo() {
        System.out.print("Informe o título do livro a ser pesquisado: ");
        String titulo = input.nextLine();
        List<Livro> livrosEncontrados = biblio.pesquisarPorTitulo(titulo);
        
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título informado.");
        } else {
            System.out.println("======== RESULTADOS DA PESQUISA ========");
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
            }
        }
    }

    private static void removerPorTitulo() {
        System.out.print("Informe o título do livro a ser removido: ");
        String titulo = input.nextLine();
        
        try {
            biblio.removerPorTitulo(titulo);
            System.out.println("Livro removido com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String menu = """
                SISTEMA DE GERENCIAMENTO DE BIBLIOTECA
                Escolha uma das opções:
                1 - Adicionar novo livro;
                2 - Listar todos os livros;
                3 - Pesquisar livro;
                4 - Remover livro;
                0 - Sair;
                """;

        int opcao;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            opcao = inputNumerico(menu);
            
            switch (opcao) {
                case 0:
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pesquisarPorTitulo();
                    break;
                case 4:
                    removerPorTitulo();
                    break;
                default:
                    System.out.println("Opção inválida!!!");
                    input.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}
