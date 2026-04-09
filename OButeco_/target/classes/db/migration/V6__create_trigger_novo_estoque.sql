--Trigger que cria um estoque automaticamente para cada produto acionado

CREATE OR REPLACE FUNCTION criar_estoque_produto()
RETURNS TRIGGER AS $$
BEGIN
INSERT INTO estoques (fk_id_produto, qtde_estoque, fk_id_conversao, local)
VALUES (NEW.id, 0, 1, 'A definir...');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_criar_estoque
    AFTER INSERT ON produtos
    FOR EACH ROW EXECUTE FUNCTION criar_estoque_produto();
