insert into categorias (categoria) values
    ('NORMAL'),
    ('PRODUTO COM INSUMOS'),
    ('INSUMO');

insert into grupos (grupo) values
   ('BEBIDA'),
   ('SERVICO'),
   ('INGREDIENTE'),
   ('COMIDA'),
   ('INSUMO');

insert into produtos (nome, preco_venda, fk_id_categoria, fk_id_grupo) values
   ('Coca Cola', 14, 1, 1),
   ('Queijo em Gramas', 0, 3, 3),
   ('Hora Servico', 15, 3, 2),
   ('Pizza de Queijo (M)', 60, 2, 4);

insert into insumos_produtos (fk_id_produto, fk_insumos_produto, qtde) values
   (4, 2, 200),
   (4, 3, 1);