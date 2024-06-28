package com.campuslands.modules.employees.infrastructure.out;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.employees.application.EmployeesService;
import com.campuslands.modules.employees.domain.models.Employee;
import com.campuslands.modules.employees.infrastructure.in.EmployeesAdapter;

public class EmployeesOutModule {

    protected EmployeesMySQL employeesMySQL;
    protected EmployeesService employeesService;
    protected EmployeesAdapter employeesAdapter;

    public EmployeesOutModule() {
        employeesMySQL = new EmployeesMySQL(); // Initialize EmployeesMySQL instance
        employeesService = new EmployeesService(employeesMySQL); // Initialize EmployeesService with EmployeesMySQL
        employeesAdapter = new EmployeesAdapter(employeesService); // Initialize EmployeesAdapter with EmployeesService
    }

    public EmployeesAdapter module() {
        return employeesAdapter; // Return the EmployeesAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("Tipo de Documentos");
        option.add(new JMenuItem(new AbstractAction("Registrar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeesAdapter.createEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeesAdapter.updateEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeesAdapter.deleteEmployee();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Tipo de Documentos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeesAdapter.findAllEmployees();
            }
        }));

        return option;
    }

    public List<String> selectOptions() {
        List<String> optionsMap = new ArrayList<String>();

        List<Employee> all = employeesService.getAllEmployees();

        for (Employee item : all) {
            optionsMap.add(item.getId() + " " + item.getName());
        }

        return optionsMap;
    }

}
