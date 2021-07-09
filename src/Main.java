import ui.Window;
import ui.panels.*;
import db.*;
import javax.swing.*;
import java.util.Objects;

/**
 * Main class to execute the program
 */
public class Main {
    static Window window;
    static Queries queries;
    static Login loginPanel;
    static Insert insertPanel;
    static Update updatePanel;
    static Delete deletePanel;

    public static void main(String[] args) {
        String[] tables = {"Publishers", "Developers"};
        loginPanel = new Login();
        insertPanel = new Insert(tables);
        updatePanel = new Update(tables);
        deletePanel = new Delete(tables);
        JPanel[] panels = {loginPanel, insertPanel, updatePanel, deletePanel};

        String[] buttons = {"Insert", "Update", "Delete"};
        SideMenu menu = new SideMenu(buttons);

        window = new Window(panels, menu);
        setActionListeners();
        window.setVisible(true);
    }

    private static void setActionListeners() {
        final String errorMessage = "Please, insert all the values";
        loginPanel = (Login) window.getCentralPanel().getComponent(0);
        loginPanel.getLoginBtn().addActionListener(e -> { // Login a user
            if (loginPanel.isEmpty())
                JOptionPane.showMessageDialog(window, errorMessage);
            else {
                queries = new Queries(loginPanel.getUser(), loginPanel.getPassword());
                loginPanel.setConnected(true);
            }
            loginPanel.reset();
        });

        insertPanel = (Insert) window.getCentralPanel().getComponent(1);
        insertPanel.getInsertBtn().addActionListener(e -> { // Inserts in the selected table
            String selectedTable = (String)insertPanel.getTableBox().getSelectedItem();
            if (Objects.equals(selectedTable, "Developers"))
                queries.insertIntoDevs(insertPanel.getID(), insertPanel.getName(), insertPanel.getForeignKey());
            else
                queries.insertIntoPubs(insertPanel.getID(), insertPanel.getName());
            insertPanel.reset();
        });

        updatePanel = (Update) window.getCentralPanel().getComponent(2);
        updatePanel.getUpdateBtn().addActionListener(e -> {
            queries.update(updatePanel.getSelectedTable(), updatePanel.getSet(), updatePanel.getValue());
            updatePanel.reset();
        });

        deletePanel = (Delete) window.getCentralPanel().getComponent(3);
        deletePanel.getDeleteBtn().addActionListener(e -> {
            queries.delete(deletePanel.getSelectedTable(), deletePanel.getWhere(), deletePanel.getValue());
            deletePanel.reset();
        });
    }
}
