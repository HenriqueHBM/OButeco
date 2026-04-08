package buteco.model.produto;
package buteco.view;


import buteco.model.produto.IngredientesProduto;
import buteco.model.produto.Produto;
import buteco.service.entradas.ErroEntrada;
import org.flywaydb.core.internal.util.JsonUtils;

import java.sql.SQLOutput;
import java.util.List;

import buteco.enums.ETipoProduto;
import java.util.Scanner;
public class Cadastro {

    Scanner scanner_cadastro_prod = new Scanner(System.in);

    System.out.println("SELECIONE A CATEGORIA: 1 - NORMAL 2 - PRODUTOCOMCOMPLEMENTO 3 - INGREDIENTE 4 - SERVICO");
    int categoria = scanner_cadastro_prod.nextInt();

    System.out.print("Digite o status do produto: ");
    varchar nome = scanner_cadastro_prod.nextLine()ine();

    System.out.print("Digite o preco do produto: ");
    float preco = scanner_cadastro_prod.nextFloat();

    System.out.print("Digite o observação do produto: ");
    varchar nome = scanner_cadastro_prod.nextLine()ine();

    scanner.close();
    //converter para enum
    CategoriaProduto categoria;

        try {
        categoria = CategoriaProduto.valueOf(categoria);
    } catch (Exception e) {
        System.out.println("Categoria inválida! Usando NORMAL.");
        categoria = CategoriaProduto.NORMAL;
    }
}
//colocar scanner close