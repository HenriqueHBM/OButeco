package buteco.controller.produtos;

import buteco.controller.produtos.dto.*;
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

    InsumosProdutoResponse insumosProdutoResponse(
            Long id_produto,
            Long id,
            String nome,
            double qtde
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

    ProdutoSelectResponse editarProduto(
            Long id_produto,
            String nome,
            Double preco_venda,
            String status,
            Long fk_id_categoria,
            Long fk_id_grupo,
            String observacao
    );

    EditProdutoResponse findProduto(Long id);
    CategoriasResponse findCategoria(Long id);
    GruposResponse findGrupo(Long id);

    List<ProdutosResponse> listarProdutos();
    List<CategoriasResponse> listarCategorias();
    List<GruposResponse> listarGrupos();
    List<ProdutoSelectResponse> listarInsumos();
    List<InsumosProdutoResponse> listarProdutosInsumos(Long id_produto);
}
