package buteco.controller.estoque.dto;

public record EstoquesResponse (
        Long id,
        String nomeProduto,
        double quantidade,
        String unidade,
        String local
){

}
