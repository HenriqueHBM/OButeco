
create table grupos(
           id serial primary key not null,
           grupo varchar(120) not null
);

create table categorias(
       id serial primary key not null,
       categoria varchar(120) not null
);

create table produtos (
          id serial primary key not null,
          nome varchar(255) not null,
          preco_venda double(10,2),
          status varchar(20) default 'ATIVO' not null,
          fk_id_categoria int not null,
          fk_id_grupo int not null,
          observacao text,
          created_at timestamp default now(),
          updated_at timestamp default now(),
          foreign key (fk_id_categoria) references categorias(id),
          foreign key (fk_id_grupo) references grupos(id)
);

create table insumos_produtos(
         id serial primary key not null,
         fk_id_produto int not null,
         fk_insumos_produto int not null,
         foreign key (fk_id_produto) references produtos(id),
         foreign key (fk_insumos_produto) references produtos(id)
);

create table conversoes(
       id serial primary key not null,
       conversao varchar(150) not null,
       nomenclatura varchar(20)
);

create table estoques(
         id serial primary key not null,
         id_produto int not null,
         qtde_estoque decimal(10,2),
         fk_id_conversao int not null,
         local varchar(255),
         created_at timestamp default now(),
         updated_at timestamp default now(),
         foreign key (fk_id_conversao) references conversoes(id)
);


create table cargos(
       id serial primary key not null,
       cargo varchar(200) not null
);

create table usuarios (
      id serial primary key not null,
      nome varchar(255) not null,
      usuario varchar(150) not null,
      senha varchar(255) not null,
      fk_id_cargo int not null,
      created_at timestamp default now(),
      updated_at timestamp default now(),
      foreign key (fk_id_cargo) references conversoes(id)
);



create table movimentacoes_estoques
(
    id                serial primary key not null,
    id_estoque        int                not null,
    tipo              varchar(30)        not null,
    qtde              decimal(10, 2)     not null,
    valor_unitario    decimal(10, 2),
    valor_total       decimal(10, 2),
    fk_id_usuario     int                not null,
    fk_id_conversao   int                not null,
    data_movimentacao timestamp default now(),
    observacao        text,
    foreign key (fk_id_usuario) references usuarios (id),
    foreign key (fk_id_conversao) references conversoes (id)
);
