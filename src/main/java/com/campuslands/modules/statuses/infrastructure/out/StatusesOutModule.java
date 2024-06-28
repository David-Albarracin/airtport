package com.campuslands.modules.statuses.infrastructure.out;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


import com.campuslands.modules.statuses.application.StatusesService;
import com.campuslands.modules.statuses.domain.models.Statuses;
import com.campuslands.modules.statuses.infrastructure.in.StatusesAdapter;

public class StatusesOutModule {

    protected StatusesMySQL statusesMySQL;
    protected StatusesService statusesService;
    protected StatusesAdapter statusesAdapter;

    public StatusesOutModule() {
        statusesMySQL = new StatusesMySQL(); // Initialize StatusesMySQL instance
        statusesService = new StatusesService(statusesMySQL); // Initialize StatusesService with StatusesMySQL
        statusesAdapter = new StatusesAdapter(statusesService); // Initialize StatusesAdapter with StatusesService
    }

    public StatusesAdapter module() {
        return statusesAdapter; // Return the StatusesAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("Estado");
        option.add(new JMenuItem(new AbstractAction("Registrar Estado") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.createEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Estado") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.updateEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Estado") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Estado") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Estado") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }

    public List<String> selectOptions() {
        List<String> optionsMap = new ArrayList<String>();

        List<Statuses> all = statusesService.getAllStatuses();

        for (Statuses item : all) {
            optionsMap.add(item.getId() + " " + item.getName());
        }

        return optionsMap;
    }
}
