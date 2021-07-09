package ui.panels;
import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    private JPanel userPanel, pwdPanel, btnPanel;
    private JLabel userLabel, pwdLabel;
    private JTextField userField, pwdField;
    private JButton loginBtn;
    private boolean connected;

    public Login() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userLabel = new JLabel("Username");
        userLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userField = new JTextField(10);
        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(userField, BorderLayout.CENTER);

        pwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pwdLabel = new JLabel("Password");
        pwdField = new JPasswordField(10);
        pwdPanel.add(pwdLabel, BorderLayout.WEST);
        pwdPanel.add(pwdField, BorderLayout.CENTER);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginBtn = new JButton("Login");
        btnPanel.add(loginBtn);

        add(userPanel);
        add(pwdPanel);
        add(btnPanel);
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public String getUser() {
        return userField.getText();
    }

    public String getPassword() {
        return pwdField.getText();
    }

    public void setConnected(boolean status) {
        connected = status;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isEmpty() {
        return getUser().equals("") || getPassword().equals("");
    }

    public void reset() {
        userField.setText("");
        pwdField.setText("");
    }

}
