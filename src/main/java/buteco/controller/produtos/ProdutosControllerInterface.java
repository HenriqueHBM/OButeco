package buteco.controller.produtos;

import buteco.controller.produtos.dto.CadProdutosResponse;

public interface ProdutosControllerInterface {

    CadProdutosResponse cadastrarProduto(
            String nome,
            double preco_venda,
            int fk_id_categoria,
            int fk_id_grupo,
            String obsevacao);
}
