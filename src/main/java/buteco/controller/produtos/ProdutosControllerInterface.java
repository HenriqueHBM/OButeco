package buteco.controller.produtos;

import buteco.controller.produtos.dto.CadInsumosProdutoResponse;
import buteco.controller.produtos.dto.CadProdutosResponse;
import buteco.controller.produtos.dto.ProdutoSelectResponse;
import buteco.controller.produtos.dto.ProdutosResponse;
import buteco.controller.produtos.dto.categoria.CategoriasResponse;
import buteco.controller.produtos.dto.grupo.GruposResponse;

import java.util.List;

public interface ProdutosControllerInterface {

    ProdutoSelectResponse cadastrarProduto(
        String nome,
        double preco_venda,
        Long fk_id_categoria,
        Long fk_id_grupo,
        String obsevacao
    );

    CadInsumosProdutoResponse cadastrarInsumo(
            Long id_produto,
            double qtde,
            Long id_insumo
    );

    ProdutosResponse produtosResponse(
        Long id,
        String nome,
        Double precoVenda,
        String status,
        String categoria,
        String grupo,
        String observacao
    );

    ProdutoSelectResponse selectProduto(Long id, String nome);

    CategoriasResponse listaCategorias(
        Long id,
        String categoria
    );

    GruposResponse grupoResponse(
        Long id,
        String grupo
    );

    List<ProdutosResponse> listarProdutos();
    List<CategoriasResponse> listarCategorias();
    List<GruposResponse> listarGrupos();
    List<ProdutoSelectResponse> listarProdutosInsumos();
}
