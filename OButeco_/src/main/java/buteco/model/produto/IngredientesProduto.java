package buteco.model.produto;

public class IngredientesProduto {
    private Produto produto;
    private Produto ingredienteProduto;
    private double qtde;

    public IngredientesProduto(Produto produto, Produto ingredienteProduto, double qtde) {
        this.produto = produto;
        this.ingredienteProduto = ingredienteProduto;
        this.qtde = qtde;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getIngredienteProduto() {
        return ingredienteProduto;
    }

    public void setIngredienteProduto(Produto ingredienteProduto) {
        this.ingredienteProduto = ingredienteProduto;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }
}
