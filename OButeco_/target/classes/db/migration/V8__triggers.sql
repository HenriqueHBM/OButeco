CREATE TABLE auditoria_produtos (
   id SERIAL PRIMARY KEY,
   fk_id_produto BIGINT NOT NULL,
   campo_alterado VARCHAR(50) NOT NULL,
   valor_anterior VARCHAR(255),
   valor_novo VARCHAR(255),
   data_alteracao TIMESTAMP DEFAULT NOW(),
   FOREIGN KEY (fk_id_produto) REFERENCES produtos(id)
);

CREATE OR REPLACE FUNCTION registrar_auditoria_produto()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.nome <> NEW.nome THEN
        INSERT INTO auditoria_produtos (fk_id_produto, campo_alterado, valor_anterior, valor_novo)
        VALUES (OLD.id, 'nome', OLD.nome, NEW.nome);
END IF;

    IF OLD.preco_venda <> NEW.preco_venda THEN
        INSERT INTO auditoria_produtos (fk_id_produto, campo_alterado, valor_anterior, valor_novo)
        VALUES (OLD.id, 'preco_venda', OLD.preco_venda::TEXT, NEW.preco_venda::TEXT);
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_auditoria_produto
    AFTER UPDATE ON produtos
    FOR EACH ROW EXECUTE FUNCTION registrar_auditoria_produto();

CREATE TABLE auditoria_estoque (
    id SERIAL PRIMARY KEY,
    fk_id_estoque BIGINT NOT NULL,
    qtde_anterior DECIMAL(10,2) NOT NULL,
    qtde_nova DECIMAL(10,2) NOT NULL,
    data_alteracao TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (fk_id_estoque) REFERENCES estoques(id)
);

CREATE OR REPLACE FUNCTION registrar_auditoria_estoque()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.qtde_estoque <> NEW.qtde_estoque THEN
        INSERT INTO auditoria_estoque (fk_id_estoque, qtde_anterior, qtde_nova)
        VALUES (OLD.id, OLD.qtde_estoque, NEW.qtde_estoque);
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_auditoria_estoque
    AFTER UPDATE OF qtde_estoque ON estoques
    FOR EACH ROW EXECUTE FUNCTION registrar_auditoria_estoque();

CREATE TABLE auditoria_inativacao_produtos (
    id SERIAL PRIMARY KEY,
    fk_id_produto BIGINT NOT NULL,
    status_anterior VARCHAR(50) NOT NULL,
    status_novo VARCHAR(50) NOT NULL,
    data_alteracao TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (fk_id_produto) REFERENCES produtos(id)
);


CREATE OR REPLACE FUNCTION registrar_inativacao_produto()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.status <> NEW.status THEN
        INSERT INTO auditoria_inativacao_produtos (fk_id_produto, status_anterior, status_novo)
        VALUES (OLD.id, OLD.status, NEW.status);
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_inativacao_produto
    AFTER UPDATE OF status ON produtos
    FOR EACH ROW EXECUTE FUNCTION registrar_inativacao_produto();

select * from auditoria_inativacao_produtos;