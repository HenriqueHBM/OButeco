package buteco.service.entradas;

import buteco.model.estoque.MovimentacoesEstoque;
import buteco.repositories.MovimentacoesEstoqueRepository;

public class MovimentacoesEstoqueService {
    MovimentacoesEstoqueRepository movimentacoesEstoqueRepository;

    public void salvarMovimentacao(MovimentacoesEstoque movimentacoesEstoque) {
        movimentacoesEstoqueRepository.create(movimentacoesEstoque);
    }

    public void apagarMovimentacao() {
    }
}