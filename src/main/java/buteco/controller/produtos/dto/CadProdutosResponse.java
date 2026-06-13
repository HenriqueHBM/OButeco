package buteco.controller.produtos.dto;

public record CadProdutosResponse(
        String nome,
        double preco_venda,
        Long fk_id_categoria,
        Long fk_id_grupo,
        String obsevacao){
}
