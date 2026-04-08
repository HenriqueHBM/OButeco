alter table movimentacoes_estoques rename column id_estoque to fk_id_estoque;
alter table movimentacoes_estoques add foreign key (fk_id_estoque) references estoques (id);
alter table usuarios drop constraint usuarios_fk_id_cargo_fkey;
alter table usuarios add foreign key (fk_id_cargo) references cargos(id);