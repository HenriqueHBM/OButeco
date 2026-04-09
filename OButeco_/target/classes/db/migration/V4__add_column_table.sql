alter table insumos_produtos add column qtde decimal(10,2) not null;

alter table estoques rename column id_produto to fk_id_produto;
alter table estoques add foreign key (fk_id_produto) references produtos(id);