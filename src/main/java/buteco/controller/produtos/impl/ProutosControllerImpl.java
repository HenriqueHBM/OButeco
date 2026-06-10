package buteco.controller.produtos.impl;

import buteco.controller.produtos.ProdutosControllerInterface;
import buteco.controller.produtos.dto.CadProdutosResponse;

public class ProutosControllerImpl implements ProdutosControllerInterface {
    public CadProdutosResponse cadastrarProduto(
            String nome,
            double preco_venda,
            int fk_id_categoria,
            int fk_id_grupo,
            String obsevacao){
        return new CadProdutosResponse(nome, preco_venda, fk_id_categoria, fk_id_grupo, obsevacao);
    }
}
