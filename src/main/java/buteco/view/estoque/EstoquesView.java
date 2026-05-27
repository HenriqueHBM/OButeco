/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buteco.view.estoque;

import buteco.controller.estoque.EstoquesController;
import buteco.model.enums.EStatus;
import buteco.model.entity.conversao.Conversoes;
import buteco.model.entity.estoque.Estoque;
import buteco.model.entity.estoque.MovimentacoesEstoque;
import buteco.model.entity.pessoa.Usuario;
import buteco.model.entity.produto.Produto;

import javax.swing.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author henrique
 */
public class EstoquesView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EstoquesView.class.getName());

    private final EstoquesController estoquesController;
    private List<Produto> listaProdutos;
    private List<Conversoes> listaConversoes;
    private final Usuario usuarioLogado;
    private List<Produto> produtosExibidos = new ArrayList<>();


    /**
     * Creates new form EstoquesView
     */
    public EstoquesView(EstoquesController estoquesController, Usuario usuarioLogado) {
        this.estoquesController = estoquesController;
        this.usuarioLogado = usuarioLogado;

        initComponents();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnEntrada.addActionListener(this::btnEntradaAction);
        btnSaida.addActionListener(this::btnSaidaAction);
        btnExcluir.addActionListener(this::btnExcluirAction);
        btnEditar.addActionListener(this::btnEditarAction);

        tbMovimentacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                preencherFormularioComMovimentacao();
            }
        });

        carregarProdutos();
        carregarConversoes();
        carregarTabelaEstoque();
        carregarTabelaMovimentacoes();
    }


    //metodos para carregar o DADOS do DB
    public void carregarProdutos(){
        listaProdutos = estoquesController.getProdutos();
        selectProduto.removeAllItems();
        produtosExibidos.clear();

        for(Produto p : listaProdutos) {
            if (!p.getStatus().equals(EStatus.ATIVO)) {
                continue;
            }

            // ignora serviços
            if (p.getCategoria().getCategoria().equals("SERVICO")) {
                continue;
            }

            String texto = p.getNome() + " - " + p.getCategoria().getCategoria();
            selectProduto.addItem(texto);

            produtosExibidos.add(p);
        }
    }

    public void carregarConversoes(){
        listaConversoes = estoquesController.getConversoes();
        selectUnidade.removeAllItems();
        selectUnEntrada.removeAllItems();

        for(Conversoes c : listaConversoes) {
            selectUnidade.addItem(c.getNomenclatura());
            selectUnEntrada.addItem(c.getNomenclatura());
        }
    }

    public void carregarTabelaEstoque(){
        var listaEstoques = estoquesController.getEstoque();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tbEstoque.getModel();
        model.setRowCount(0); //comeca do primeiro e zera se tiver outra informacao perdida
        for (Estoque e : listaEstoques) {
            model.addRow(new Object[]{
                e.getId(),
                e.getProduto() != null ? e.getProduto().getNome() : "-",
                e.getQntdEstoque(),
                e.getConversoes() != null ? e.getConversoes().getNomenclatura() : "-",
                e.getLocal() != null ? e.getLocal() : "-"
            });
        }
    }

    public void carregarTabelaMovimentacoes(){
        var listaMovimentacoes = estoquesController.getMovimentacoes();
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tbMovimentacoes.getModel();
        model.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.of("UTC"));
        for (MovimentacoesEstoque m : listaMovimentacoes) {
            if (m.getProduto() == null) continue;
            String data = m.getDataMovimentacao() != null ?
                    fmt.format(m.getDataMovimentacao()) : "-";

            model.addRow(new Object[]{
                    m.getId(),
                    m.getProduto().getNome(),
                    m.getConversoes().getNomenclatura(),
                    m.getQuantidade(),
                    data,
                    m.getUsuario() != null ? m.getUsuario().getNome() : "-",
                    m.getTipo()
            });
        }
    }

    public void limparFormulario(){
        txtQuantidade.setText("");
        txtLocal.setText("");
        txtConversao.setText("");
        txtAreaObservacao.setText("");
        selectProduto.setSelectedIndex(0);
        selectUnidade.setSelectedIndex(0);
        selectUnEntrada.setSelectedIndex(0);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnVoltar = new javax.swing.JButton();
        lbEstoque = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        lbProduto = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lbQuantidade = new javax.swing.JLabel();
        lbUnEstoque = new javax.swing.JLabel();
        lbLocal = new javax.swing.JLabel();
        txtLocal = new javax.swing.JTextField();
        selectUnidade = new javax.swing.JComboBox<>();
        lbUnEntrada = new javax.swing.JLabel();
        txtConversao = new javax.swing.JTextField();
        lbConversao = new javax.swing.JLabel();
        selectUnEntrada = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaObservacao = new javax.swing.JTextArea();
        lbObs = new javax.swing.JLabel();
        btnEntrada = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSaida = new javax.swing.JButton();
        lbSubTituloEstoque = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEstoque = new javax.swing.JTable();
        lbSubTituloMovimentacoes = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMovimentacoes = new javax.swing.JTable();
        selectProduto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(28, 28, 30));
        jPanel2.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.setMinimumSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back.png"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.setBorder(null);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVoltar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnVoltar.setDefaultCapable(false);
        btnVoltar.setRolloverEnabled(false);
        btnVoltar.addActionListener(this::btnVoltarActionPerformed);

        lbEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        lbEstoque.setText("Estoque");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lbEstoque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVoltar)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(lbEstoque))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTitulo.setFont(new java.awt.Font("Liberation Sans", 1, 20)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 0));
        lbTitulo.setText("Cadastro de Estoque");

        lbProduto.setBackground(new java.awt.Color(0, 0, 0));
        lbProduto.setForeground(new java.awt.Color(255, 255, 255));
        lbProduto.setText("Produto");

        txtQuantidade.addActionListener(this::txtQuantidadeActionPerformed);

        lbQuantidade.setBackground(new java.awt.Color(0, 0, 0));
        lbQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        lbQuantidade.setText("Quantidade");

        lbUnEstoque.setBackground(new java.awt.Color(0, 0, 0));
        lbUnEstoque.setForeground(new java.awt.Color(255, 255, 255));
        lbUnEstoque.setText("Unidade Estoque");

        lbLocal.setBackground(new java.awt.Color(0, 0, 0));
        lbLocal.setForeground(new java.awt.Color(255, 255, 255));
        lbLocal.setText("Local Estoque");

        txtLocal.addActionListener(this::txtLocalActionPerformed);

        selectUnidade.setMaximumSize(new java.awt.Dimension(64, 24));
        selectUnidade.setMinimumSize(new java.awt.Dimension(64, 24));
        selectUnidade.setPreferredSize(new java.awt.Dimension(64, 24));
        selectUnidade.addActionListener(this::selectUnidadeActionPerformed);

        lbUnEntrada.setBackground(new java.awt.Color(0, 0, 0));
        lbUnEntrada.setForeground(new java.awt.Color(255, 255, 255));
        lbUnEntrada.setText("Unidade Entrada");

        txtConversao.addActionListener(this::txtConversaoActionPerformed);

        lbConversao.setBackground(new java.awt.Color(0, 0, 0));
        lbConversao.setFont(new java.awt.Font("Liberation Sans", 0, 13)); // NOI18N
        lbConversao.setForeground(new java.awt.Color(255, 255, 255));
        lbConversao.setText("Quantas unidades de entrada equivalem a 1 do estoque?");

        txtAreaObservacao.setColumns(20);
        txtAreaObservacao.setRows(5);
        jScrollPane1.setViewportView(txtAreaObservacao);

        lbObs.setBackground(new java.awt.Color(0, 0, 0));
        lbObs.setForeground(new java.awt.Color(255, 255, 255));
        lbObs.setText("Observacao");

        btnEntrada.setBackground(new java.awt.Color(51, 51, 51));
        btnEntrada.setForeground(new java.awt.Color(255, 255, 0));
        btnEntrada.setText("Cadastrar Entrada");
        btnEntrada.setBorderPainted(false);
        btnEntrada.setFocusable(false);

        btnEditar.setBackground(new java.awt.Color(51, 51, 51));
        btnEditar.setForeground(new java.awt.Color(255, 255, 0));
        btnEditar.setText("Editar");
        btnEditar.setBorderPainted(false);
        btnEditar.setFocusPainted(false);

        btnExcluir.setBackground(new java.awt.Color(51, 51, 51));
        btnExcluir.setForeground(new java.awt.Color(255, 255, 0));
        btnExcluir.setText("Excluir");
        btnExcluir.setBorderPainted(false);
        btnExcluir.setFocusPainted(false);

        btnSaida.setBackground(new java.awt.Color(51, 51, 51));
        btnSaida.setForeground(new java.awt.Color(255, 255, 0));
        btnSaida.setText("Cadastrar Saída");
        btnSaida.setBorderPainted(false);
        btnSaida.setFocusable(false);

        lbSubTituloEstoque.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lbSubTituloEstoque.setForeground(new java.awt.Color(255, 255, 255));
        lbSubTituloEstoque.setText("Estoques");

        tbEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null},
                {"", null, null, null, null},
                {"", null, null, null, null},
                {"", null, null, null, null},
                {"", null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Quantidade", "Unidade Medida", "Local"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbEstoque);

        lbSubTituloMovimentacoes.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lbSubTituloMovimentacoes.setForeground(new java.awt.Color(255, 255, 255));
        lbSubTituloMovimentacoes.setText("Movimentacoes");

        tbMovimentacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Unidade", "Quantidade", "Data", "Usuario", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbMovimentacoes);

        selectProduto.addActionListener(this::selectProdutoActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbObs, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbUnEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbUnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectUnEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(selectUnidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(selectProduto, 0, 384, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtQuantidade)
                                            .addComponent(lbQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtConversao, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtLocal)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbConversao))
                                                .addGap(0, 0, Short.MAX_VALUE)))))))
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSubTituloEstoque)
                .addGap(363, 363, 363))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(lbSubTituloMovimentacoes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbProduto)
                        .addGap(2, 2, 2)
                        .addComponent(selectProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbQuantidade)
                        .addGap(2, 2, 2)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUnEstoque)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbLocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbConversao)
                            .addComponent(lbUnEntrada))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtConversao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectUnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(lbObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSubTituloEstoque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSubTituloMovimentacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntradaAction(java.awt.event.ActionEvent evt) {
        try {
            int indexProduto = selectProduto.getSelectedIndex();
            int indexUnEntrada = selectUnEntrada.getSelectedIndex();

            if(indexProduto < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um produto");
                return;
            }

            Produto produto = produtosExibidos.get(indexProduto);
            Conversoes unidadeEntrada = listaConversoes.get(indexUnEntrada);

            String qtdeStr = txtQuantidade.getText().trim();
            if(qtdeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a quantidade");
                return;
            }
            double qtde = Double.parseDouble(qtdeStr);

            String fatorStr = txtConversao.getText().trim();
            boolean mesmaUnidade = unidadeEntrada.getId().equals(
                    listaConversoes.get(selectUnidade.getSelectedIndex()).getId()
            );
            double fator;
            if (mesmaUnidade) {
                fator = 1.0;
            } else {

                if (fatorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Informe o fator de conversao!"
                    );
                    return;
                }
                fator = Double.parseDouble(fatorStr);
            }

            String local = txtLocal.getText().trim();

            String observacao = txtAreaObservacao.getText().trim();

            estoquesController.cadastrarEntrada(produto, qtde, unidadeEntrada.getId(), fator, local, usuarioLogado, observacao);

            JOptionPane.showMessageDialog(this, "Entrada cadastrada com sucesso!");

            carregarTabelaEstoque();
            carregarTabelaMovimentacoes();
            limparFormulario();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade ou fator de conversao invalido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void btnSaidaAction (java.awt.event.ActionEvent evt) {
        try {
            int indexProduto = selectProduto.getSelectedIndex();
            int indexUnEntrada = selectUnEntrada.getSelectedIndex();

            if (indexProduto < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um produto!");
                return;
            }

            Produto produto = produtosExibidos.get(indexProduto);
            Conversoes unidadeSaida = listaConversoes.get(indexUnEntrada);

            String qtdeStr = txtQuantidade.getText().trim();
            if (qtdeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a quantidade!");
                return;
            }
            double qtde = Double.parseDouble(qtdeStr);

            boolean mesmaUnidade = unidadeSaida.getId().equals(
                    listaConversoes.get(selectUnidade.getSelectedIndex()).getId()
            );
            double fator;
            if (mesmaUnidade) {
                fator = 1.0;
            } else {
                String fatorStr = txtConversao.getText().trim();
                if (fatorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o fator de conversão!");
                    return;
                }
                fator = Double.parseDouble(fatorStr);
            }

            String observacao = txtAreaObservacao.getText().trim();

            estoquesController.cadastarSaida(produto, qtde, unidadeSaida.getId(), fator, usuarioLogado, observacao);

            JOptionPane.showMessageDialog(this, "Saída cadastrada com sucesso!");
            carregarTabelaEstoque();
            carregarTabelaMovimentacoes();
            limparFormulario();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade ou fator de conversão inválido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    public void btnExcluirAction(java.awt.event.ActionEvent evt) {
        int linhaSelecionada = tbMovimentacoes.getSelectedRow();
        if(linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma movimentacao para excluir!");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir essa movimentacao? O estoque sera revertido.",
                "Confirmar exclusao.",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao != JOptionPane.YES_OPTION) return;

        try {
            //pega o id na primeira coluna da linha selecionada
            Long idMovimentacao = (Long) tbMovimentacoes.getValueAt(linhaSelecionada, 0);
            estoquesController.excluirMovimentacao(idMovimentacao);

            JOptionPane.showMessageDialog(this, "Movimentacao excluida com sucesso!");
            carregarTabelaEstoque();
            carregarTabelaMovimentacoes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private Long idMovimentacaoSelecionada = null;

    private void preencherFormularioComMovimentacao() {
        int linha = tbMovimentacoes.getSelectedRow();
        if(linha < 0) return;

        idMovimentacaoSelecionada = (Long) tbMovimentacoes.getValueAt(linha, 0);
        String nomeProduto = (String) tbMovimentacoes.getValueAt(linha, 1);
        String nomenclatura = (String) tbMovimentacoes.getValueAt(linha, 2);
        double quantidade = (double) tbMovimentacoes.getValueAt(linha, 3);

        //preenche o produto
        for (int i = 0; i < produtosExibidos.size(); i++) {
            if (produtosExibidos.get(i).getNome().equals(nomeProduto)) {
                selectProduto.setSelectedIndex(i);
                break;
            }
        }

        //preenche unidade do estoque buscando pelo produto selecionado
        Produto produto = listaProdutos.stream()
                .filter(p -> p.getNome().equals(nomeProduto))
                .findFirst().orElse(null);

        if (produto != null) {
            String unidadeEstoque = estoquesController.getUnidadeEstoquePorProduto(produto.getId());
            for (int i = 0; i < listaConversoes.size(); i++) {
                if (listaConversoes.get(i).getNomenclatura().equals(unidadeEstoque)) {
                    selectUnidade.setSelectedIndex(i);
                    break;
                }
            }
        }

        //preenche unidade entrada
        for (int i = 0; i < listaConversoes.size(); i++) {
            if (listaConversoes.get(i).getNomenclatura().equals(nomenclatura)){
                selectUnEntrada.setSelectedIndex(i);
                break;
            }
        }

        txtQuantidade.setText(String.valueOf(quantidade));

        String observacao = estoquesController.getObservacaoMovimentacao(idMovimentacaoSelecionada);
        txtAreaObservacao.setText(observacao != null ? observacao : "");
    }

    private void btnEditarAction (java.awt.event.ActionEvent evt) {
        if (idMovimentacaoSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma movimentacao para editar!");
            return;
        }

        try {
            int indexProduto = selectProduto.getSelectedIndex();
            int indexUnEntrada = selectUnEntrada.getSelectedIndex();

            Produto produto = produtosExibidos.get(indexProduto);
            Conversoes unidade = listaConversoes.get(indexUnEntrada);

            String qtdeStr = txtQuantidade.getText().trim();
            if (qtdeStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a quantidade!");
                return;
            }
            double qtde = Double.parseDouble(qtdeStr);

            boolean mesmaUnidade = unidade.getId().equals(
                    listaConversoes.get(selectUnidade.getSelectedIndex()).getId()
            );
            double fator;
            if(mesmaUnidade) {
                fator = 1.0;
            } else {
                String fatorStr = txtConversao.getText().trim();
                if (fatorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Informe o fator de conversao!");
                    return;
                }
                fator = Double.parseDouble(fatorStr);
            }

            String observacao = txtAreaObservacao.getText().trim();

            estoquesController.editarMovimentacao(idMovimentacaoSelecionada, produto, qtde, unidade.getId(), fator, observacao);

            JOptionPane.showMessageDialog(this, "Movimentacao editada com sucesso!");
            idMovimentacaoSelecionada = null;
            carregarTabelaEstoque();
            carregarTabelaMovimentacoes();
            limparFormulario();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade invalida!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro :" + e.getMessage());
        }
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void txtQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantidadeActionPerformed

    private void txtLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalActionPerformed

    private void txtConversaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConversaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConversaoActionPerformed

    private void selectUnidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectUnidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectUnidadeActionPerformed

    private void selectProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectProdutoActionPerformed
        int index = selectProduto.getSelectedIndex();

        if (index < 0 || listaProdutos == null || listaConversoes == null) {
            return;
        }

        Produto produto = produtosExibidos.get(index);
        String unidadeEstoque = estoquesController.getUnidadeEstoquePorProduto(produto.getId());

        if (unidadeEstoque != null) {
            for (int i = 0; i < listaConversoes.size(); i++) {
                if (listaConversoes.get(i).getNomenclatura().equals(unidadeEstoque)) {
                    selectUnidade.setSelectedIndex(i);
                    break;
                }
            }
        }
    }//GEN-LAST:event_selectProdutoActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new EstoquesView().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSaida;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbConversao;
    private javax.swing.JLabel lbEstoque;
    private javax.swing.JLabel lbLocal;
    private javax.swing.JLabel lbObs;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lbSubTituloEstoque;
    private javax.swing.JLabel lbSubTituloMovimentacoes;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUnEntrada;
    private javax.swing.JLabel lbUnEstoque;
    private javax.swing.JComboBox<String> selectProduto;
    private javax.swing.JComboBox<String> selectUnEntrada;
    private javax.swing.JComboBox<String> selectUnidade;
    private javax.swing.JTable tbEstoque;
    private javax.swing.JTable tbMovimentacoes;
    private javax.swing.JTextArea txtAreaObservacao;
    private javax.swing.JTextField txtConversao;
    private javax.swing.JTextField txtLocal;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
