package ui;
import ui.panels.*;
import javax.swing.*;
import java.awt.*;

/**
 * The window that shows the user interface
 */
public class Window extends JFrame {
    private final Window reference;
    private final SideMenu menu;
    private final Login loginPanel;
    private final Insert insertPanel;
    private final Update updatePanel;
    private final Delete deletePanel;
    private final JPanel centralPanel;

    public Window(JPanel[] panels, SideMenu menu) {
        super("Data Base Project: LibGames");
        setSize(350,220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        reference = this;

        this.menu = menu;
        add(menu, BorderLayout.WEST);

        centralPanel = new JPanel();
        add(centralPanel, BorderLayout.CENTER);
        for (JPanel p : panels)
            centralPanel.add(p).setVisible(false);
        centralPanel.getComponent(0).setVisible(true);

        loginPanel = (Login) panels[0];
        insertPanel = (Insert) panels[1];
        updatePanel = (Update) panels[2];
        deletePanel = (Delete) panels[3];

        setMenuListeners();
    }

    public JPanel getCentralPanel() {
        return centralPanel;
    }

    private void setMenuListeners() {
        final String errorMessage = "You are not connected";
        menu.getButton('i').addActionListener(e -> {
            if (loginPanel.isConnected()) {
                loginPanel.setVisible(false);
                insertPanel.setVisible(true);
                updatePanel.setVisible(false);
                deletePanel.setVisible(false);
            } else
                JOptionPane.showMessageDialog(reference, errorMessage);
        });

        menu.getButton('u').addActionListener(e -> {
            if (loginPanel.isConnected()) {
                loginPanel.setVisible(false);
                insertPanel.setVisible(false);
                updatePanel.setVisible(true);
                deletePanel.setVisible(false);
            } else
                JOptionPane.showMessageDialog(reference, errorMessage);
        });

        menu.getButton('d').addActionListener(e -> {
            if (loginPanel.isConnected()) {
                loginPanel.setVisible(false);
                insertPanel.setVisible(false);
                updatePanel.setVisible(false);
                deletePanel.setVisible(true);
            } else
                JOptionPane.showMessageDialog(reference, errorMessage);
        });
    }
}
