package buteco.view.components;

import buteco.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Cards {
    private final Colors colorComponente;

    public Cards(Colors colorComponente) {
        this.colorComponente = colorComponente;
    }

    public JButton criarBotaoCard(String texto, String icon) {
        URL imgURL = MainView.class.getResource("/icons/" + icon);
        Image img = new ImageIcon(imgURL, texto).getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH);

        JButton btn = new JButton(texto, new ImageIcon(img));

        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);

        btn.setBackground(colorComponente.corCard());
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(180,180));
        btn.setMaximumSize(new Dimension(180,180));
        btn.setBorder(BorderFactory.createLineBorder(new Color(200,200, 200), 1, true));
        btn.setFocusPainted(false);

        return btn;
    }
}
