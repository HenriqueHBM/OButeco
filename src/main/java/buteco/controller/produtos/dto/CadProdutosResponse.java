package buteco.controller.produtos.dto;

public record CadProdutosResponse(
        String nome,
        double preco_venda,
        int fk_id_categoria,
        int fk_id_grupo,
        String obsevacao){
}
