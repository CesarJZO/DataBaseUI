package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SideMenu extends JPanel {
    private final Map<Character, JButton> buttonMap = new HashMap<>();

    public SideMenu(String[] btnLabels) {
        setLayout(new GridLayout(btnLabels.length, 1));
        for (String label : btnLabels) {
            char key = label.toLowerCase().charAt(0);
            buttonMap.put(key, new JButton(label));
            add(buttonMap.get(key));
        }
    }

    public JButton getButton(char key) {
        return buttonMap.get(key);
    }
}