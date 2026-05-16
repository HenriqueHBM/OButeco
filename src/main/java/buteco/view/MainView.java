package buteco.view;


import buteco.view.components.Cards;
import buteco.view.components.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainView extends JFrame {
    private final Colors colorComponente;
    private final Cards cardComponent;
    private JButton btnSair;
    private JButton btnUsuario;
    private JButton btnProduto;
    private JButton btnEstoque;

    public MainView(Colors colorComponente, Cards cardComponent)
    {
        this.colorComponente = colorComponente;
        this.cardComponent = cardComponent;

        setTitle("O BUTECO"); //nome da tela
        setDefaultCloseOperation(EXIT_ON_CLOSE); //ao apernar no "x", ele fecha
        setSize(800,600); //tamanho da tela
        setLocationRelativeTo(null); // deixar centralizado

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(colorComponente.corFundo());

        painel.add(criaHeader(), BorderLayout.NORTH);
        painel.add(criaCards(), BorderLayout.CENTER);

        setContentPane(painel);
        setVisible(true);
    }

    public JPanel criaHeader()
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE); // era Color.WHITE

        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,1,0, new Color(60, 60,62)),
                BorderFactory.createEmptyBorder(14,18,14,18)
        ));

        JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        painelTitulo.setBackground(Color.WHITE);

        URL imgURL = MainView.class.getResource("/icons/home.png");
        ImageIcon icon = new ImageIcon(imgURL, "Menu");
        Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);

        JLabel labelIcon = new JLabel(new ImageIcon(img));
        painelTitulo.add(labelIcon);

        JLabel labelTitulo = new JLabel("Menu Principal");
        labelTitulo.setBackground(colorComponente.corCard());
//        labelTitulo.setForeground(Color.WHITE);

        painelTitulo.add(labelTitulo);
//        header.add(painelTitulo);

        this.btnSair = new JButton("Sair");
//        btnSair.setBackground(colorComponente.corFundo());
//        btnSair.setForeground(Color.WHITE);
        btnSair.setBorderPainted(false);
        btnSair.setFocusPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));

        header.add(painelTitulo, BorderLayout.WEST);
        header.add(btnSair, BorderLayout.EAST);
//        header.add(btnSair);

        return header;
    }

    public JPanel criaCards()
    {
        JPanel cards = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        cards.setBackground(colorComponente.corFundo());

        btnProduto = cardComponent.criarBotaoCard("Produtos", "shopping-cart.png");
        btnEstoque = cardComponent.criarBotaoCard("Estoque", "stock.png");
        btnUsuario = cardComponent.criarBotaoCard("Usuario", "user.png");

        cards.add(btnProduto);
        cards.add(btnEstoque);
        cards.add(btnUsuario);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(colorComponente.corFundo());
        wrapper.add(cards);

        return wrapper;
    }

    public void clicarUsuarioAction(ActionListener action){
        btnUsuario.addActionListener(action);
    }
    public void clicarProdutoAction(ActionListener action){
        btnProduto.addActionListener(action);
    }
    public void clicarEstoqueAction(ActionListener action){
        btnEstoque.addActionListener(action);
    }

    public void clicarSair(ActionListener action){
        btnSair.addActionListener(action);
    }
}
