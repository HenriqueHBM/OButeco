package buteco.controller.produtos.impl;

import buteco.controller.produtos.ProdutosControllerInterface;
import buteco.controller.produtos.dto.*;
import buteco.controller.produtos.dto.categoria.CategoriasResponse;
import buteco.controller.produtos.dto.grupo.GruposResponse;
import buteco.model.entity.produto.CategoriaEntity;
import buteco.model.entity.produto.GrupoEntity;
import buteco.model.entity.produto.InsumosProdutoEntity;
import buteco.model.entity.produto.ProdutoEntity;
import buteco.model.enums.EStatus;
import buteco.model.service.CategoriaService;
import buteco.model.service.GrupoService;
import buteco.model.service.InsumosProdutoService;
import buteco.model.service.ProdutoService;

import java.util.List;

public class ProdutosControllerImpl implements ProdutosControllerInterface{

    private ProdutoService produtoService;
    private CategoriaService categoriaService;
    private GrupoService grupoService;
    private InsumosProdutoService insumoService;

    public ProdutosControllerImpl(
            ProdutoService produtoService,
            CategoriaService categoriaService,
            GrupoService grupoService,
            InsumosProdutoService insumoService
    ){
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.grupoService = grupoService;
        this.insumoService = insumoService;
    }

    public ProdutosResponse produtosResponse(
            Long id,
            String nome,
            Double precoVenda,
            String status,
            String categoria,
            String grupo,
            String observacao
    ){
        return new ProdutosResponse(id, nome, precoVenda, status, categoria, grupo, observacao);
    }
//    public
    public ProdutoSelectResponse selectProduto(Long id, String nome){return new ProdutoSelectResponse(id, nome);};

    public CategoriasResponse listaCategorias(Long id, String categoria){
        return new CategoriasResponse(id, categoria);
    }

    public GruposResponse grupoResponse(Long id, String grupo){
        return new GruposResponse(id, grupo);
    }

    public InsumosProdutoResponse insumosProdutoResponse(
            Long id_produto,
            Long id,
            String nome,
            double qtde
    ){

        return new InsumosProdutoResponse(id_produto, id, nome, qtde);
    }
    //Listagem das Coisas para Usar em select/tabelas-----------------------------------------
    public List<ProdutosResponse> listarProdutos(){
        return produtoService.findAllProdutos()
                .stream()
                .map(produto -> this.produtosResponse(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPrecoVenda(),
                        produto.getStatus().name(),
                        produto.getCategoria().getCategoria(),
                        produto.getGrupo().getGrupo(),
                        produto.getObservacao()
                ))
                .toList();
    }

    public List<CategoriasResponse> listarCategorias(){
       return categoriaService.findAllCategoria()
               .stream()
               .map(cat -> this.listaCategorias(cat.getId(), cat.getCategoria()))
               .toList();
    }

    public List<GruposResponse> listarGrupos(){
        return grupoService.findAllGrupo()
                .stream()
                .map(gp -> this.grupoResponse(gp.getId(), gp.getGrupo()))
                .toList();
    }

    public List<ProdutoSelectResponse> listarInsumos(){
        return produtoService.findAllProdutos()
                .stream()
                .filter(p ->
                    p.getGrupo().getGrupo().equals("INSUMO") ||
                    p.getGrupo().getGrupo().equals("SERVICO") ||
                    p.getGrupo().getGrupo().equals("INGREDIENTE")
                )
                .map(p-> this.selectProduto(p.getId(), p.getNome()))
                .toList();
    }

    public List<InsumosProdutoResponse> listarProdutosInsumos(Long id_produto){

        return insumoService.findAllInsumosProduto(id_produto)
                .stream()
                .map(i -> this.insumosProdutoResponse(
                        i.getProduto().getId(),
                        i.getId(),
                        i.getInsumo().getNome(),
                        i.getQtde()
                ))
                .toList();
    }
    //find -----------------------------------------------------------------------
    public EditProdutoResponse findProduto(Long id){
        ProdutoEntity produto = produtoService.findById(id);
        return new EditProdutoResponse(produto.getId(),
                produto.getNome(),
                produto.getPrecoVenda(),
                produto.getStatus().toString(),
                produto.getCategoria().getId(),
                produto.getGrupo().getId(),
                produto.getObservacao()
        );
    }

    public CategoriasResponse findCategoria(Long id){
        CategoriaEntity cat = categoriaService.findById(id);
        return new CategoriasResponse(cat.getId(), cat.getCategoria());
    }

    public GruposResponse findGrupo(Long id){
        GrupoEntity gp = grupoService.findById(id);
        return new GruposResponse(gp.getId(), gp.getGrupo());
    }

    //Cadastro
    public ProdutoSelectResponse cadastrarProduto(
            String nome,
            double preco_venda,
            Long fk_id_categoria,
            Long fk_id_grupo,
            String obsevacao){

        //busca a categoria
        CategoriaEntity categoriaEntity = categoriaService.findById(fk_id_categoria);

        //busca o grupo
        GrupoEntity grupoEntity = grupoService.findById(fk_id_grupo);

        //de fato comeca a salvar as info do produto
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNome(nome);
        produtoEntity.setPrecoVenda(preco_venda);
        produtoEntity.setObservacao(obsevacao);
        produtoEntity.setCategoria(categoriaEntity);
        produtoEntity.setGrupo(grupoEntity);

        produtoService.salvarProduto(produtoEntity);

        return new ProdutoSelectResponse(produtoEntity.getId(), produtoEntity.getNome());
    }

    public CadInsumosProdutoResponse cadastrarInsumo(Long id_produto, double qtde, Long id_insumo){

        ProdutoEntity produto = produtoService.findById(id_produto);
        ProdutoEntity insumo = produtoService.findById(id_insumo);

        InsumosProdutoEntity insumosProduto = new InsumosProdutoEntity();
        insumosProduto.setProduto(produto);
        insumosProduto.setQtde(qtde);
        insumosProduto.setInsumo(insumo);
        insumoService.salvarInsumo(insumosProduto);

        return new CadInsumosProdutoResponse(id_produto,qtde,id_insumo);
    }

    //editar
    //nele temos nome, preco venda, observacao e status categoria grupo
    public ProdutoSelectResponse editarProduto(
            Long id_produto,
            String nome,
            Double preco_venda,
            String status,
            Long fk_id_categoria,
            Long fk_id_grupo,
            String observacao)
    {
        ProdutoEntity produto = produtoService.findById(id_produto);

        //busca a categoria
        CategoriaEntity categoriaEntity = categoriaService.findById(fk_id_categoria);

        //busca o grupo
        GrupoEntity grupoEntity = grupoService.findById(fk_id_grupo);

        produto.setNome(nome);
        produto.setPrecoVenda(preco_venda);
        produto.setObservacao(observacao);
        produto.setCategoria(categoriaEntity);
        produto.setGrupo(grupoEntity);
        produto.setStatus(
                "Ativo".equals(status)
                        ? EStatus.ATIVO
                        : EStatus.INATIVO
        );
        produtoService.atualizarProduto(produto);

        return new ProdutoSelectResponse(
                produto.getId(),
                produto.getNome()
        );
    }
}
