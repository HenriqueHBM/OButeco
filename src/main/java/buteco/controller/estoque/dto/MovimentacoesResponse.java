package buteco.controller.estoque.dto;

public record MovimentacoesResponse(
        Long id,
        String nomeProduto,
        String unidade,
        double quantidade,
        String data,
        String usuario,
        String tipo
){
}
