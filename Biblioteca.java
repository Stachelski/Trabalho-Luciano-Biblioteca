
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class Biblioteca {
    private List<Livro> acervo = new ArrayList<>();

    public void adicionar(Livro livro) throws Exception{
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Não é permitido cadastrar livro sem título");
        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(livro.getTitulo()))
                throw new Exception("Já existe livro cadastrado com este título");
        }
        acervo.add(livro);
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }

        return livrosEncontrados;
    }

    public void removerPorTitulo(String titulo) {
        Iterator<Livro> iterator = acervo.iterator();
        while (iterator.hasNext()) {
            Livro livro = iterator.next();
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                iterator.remove();
                return;
            }
        }
    }

    public List<Livro> pesquisarTodos(){
        return this.acervo;
    }
}