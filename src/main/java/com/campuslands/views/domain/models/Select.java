package com.campuslands.views.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Select {
    protected JPanel div;
    protected JComboBox<String> select;
    protected ArrayList<String> options;
    protected String labelText;

    public Select(String labelText, List<String> list) {
        this.labelText = labelText;
        select = new JComboBox<>();
        options = new ArrayList<>();
        div = new JPanel();
        div.add(new JLabel(labelText));
        div.add(select);
        addOptions(list);
    }

    public void addOptions(List<String> list) {
        for (String label : list) {
            options.add(label);
        }
        for (String option : options) {
            select.addItem(option);
        }
    }

    public String getValue() {
        String selectedItem = (String) select.getSelectedItem();
        if (selectedItem != null) {
            return selectedItem.split(" ")[0];
        } else {
            return null;
        }
    }

    

    public JPanel getDiv() {
        return div;
    }

}
