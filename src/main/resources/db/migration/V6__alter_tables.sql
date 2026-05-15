ALTER TABLE movimentacoes_estoques
    ADD COLUMN fk_id_produto BIGINT;

ALTER TABLE movimentacoes_estoques
    ADD CONSTRAINT fk_mov_produto
    FOREIGN KEY (fk_id_produto)
    REFERENCES produtos(id);