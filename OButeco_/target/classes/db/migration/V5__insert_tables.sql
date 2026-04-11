insert into categorias (categoria) values
    ('NORMAL'),
    ('PRODUTO COM INSUMOS'),
    ('INSUMO'),
    ('SERVICO');

insert into grupos (grupo) values
   ('BEBIDA'),
   ('SERVICO'),
   ('INGREDIENTE'),
   ('COMIDA'),
   ('INSUMO');

insert into produtos (nome, preco_venda, fk_id_categoria, fk_id_grupo) values
   ('Coca Cola', 14, 1, 1),
   ('Queijo em Gramas', 0, 3, 3),
   ('Hora Servico', 15, 4, 2),
   ('Pizza de Queijo (M)', 60, 2, 4);

insert into insumos_produtos (fk_id_produto, fk_insumos_produto, qtde) values
   (4, 2, 200),
   (4, 3, 1);

insert into cargos (cargo) values
   ('Funcionario'),
   ('Encarregado');

insert into usuarios (nome, usuario, senha, fk_id_cargo) values
    ('Ismael', 'ismael', 'ismael123', 2);

insert into conversoes (conversao, nomenclatura) values
     ('Grama', 'g'),
     ('Unidade', 'Uni'),
     ('Quilograma', 'Kg'),
     ('Hora', 'hr');

alter table movimentacoes_estoques add column qtde_conversao decimal(10,2);

INSERT INTO estoques (fk_id_produto,qtde_estoque,fk_id_conversao,"local",created_at,updated_at) VALUES
    (2,1500.00,1,'prateleira','2026-04-09 20:49:23.79182','2026-04-09 20:49:23.79182');

INSERT INTO movimentacoes_estoques (fk_id_estoque,tipo,qtde,valor_unitario,valor_total,fk_id_usuario,fk_id_conversao,data_movimentacao,observacao,qtde_conversao) VALUES
    (1,'ENTRADA',3.00,50.00,150.00,1,2,'2026-04-09 20:51:48.686739',NULL,1500.00);
