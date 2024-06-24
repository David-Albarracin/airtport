package com.campuslands.modules.employees.infrastructure.in;

import com.campuslands.modules.employees.domain.models.Employee;
import com.campuslands.modules.employees.application.EmployeesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class EmployeesAdapter {
    private ViewOut v;
    private final EmployeesService employeesService;

    public EmployeesAdapter(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    public void createEmployee() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el id del Empleado", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Empleado", 30);
        ViewOut.VInput idRolInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VDate ingressDateInput = v.new VDate("Ingresa la Fecha de Ingreso del Empleado (YYYY-MM-DD)", ("Date"));
        ViewOut.VInput idAirlineInput = v.new VInput("Ingresa el ID de la Aerolínea del Empleado", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto del Empleado", 30);

        JButton addButton = new JButton("Agregar Nuevo Empleado");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int idRol = idRolInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();
                    Date ingressDate = ingressDateInput.getValue();
                    int idAirline = idAirlineInput.getInt();
                    int idAirport = idAirportInput.getInt();

                    Employee employee = new Employee(id, name, email, ingressDate, idAirline, idAirport, password,
                            idRol);
                    employeesService.createEmployee(employee);
                    JOptionPane.showMessageDialog(v.container, "Empleado agregado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el empleado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(ingressDateInput.getDiv());
        v.container.add(idAirlineInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idRolInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateEmployee() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Empleado", 30);
        ViewOut.VInput nameInput = v.new VInput("Ingresa el Nombre del Empleado", 30);
        ViewOut.VInput emailInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput passwordInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput idRolInput = v.new VInput("Ingresa el ID del Rol del Empleado", 10);
        ViewOut.VInput ingressDateInput = v.new VInput("Ingresa la Fecha de Ingreso del Empleado (YYYY-MM-DD)", 20);
        ViewOut.VInput idAirlineInput = v.new VInput("Ingresa el ID de la Aerolínea del Empleado", 10);
        ViewOut.VInput idAirportInput = v.new VInput("Ingresa el ID del Aeropuerto del Empleado", 30);

        JButton updateButton = new JButton("Actualizar Empleado");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String name = nameInput.getText();
                    int idRol = idRolInput.getInt();
                    String email = emailInput.getText();
                    String password = passwordInput.getText();
                    Date ingressDate = Date.valueOf(ingressDateInput.getText());
                    int idAirline = idAirlineInput.getInt();
                    int idAirport = idAirportInput.getInt();

                    Employee employee = new Employee(id, name, email, ingressDate, idAirline, idAirport, password,
                            idRol);
                    employeesService.updateEmployee(employee);
                    JOptionPane.showMessageDialog(v.container, "Empleado actualizado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el empleado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(nameInput.getDiv());
        v.container.add(emailInput.getDiv());
        v.container.add(ingressDateInput.getDiv());
        v.container.add(idAirlineInput.getDiv());
        v.container.add(idAirportInput.getDiv());
        v.container.add(passwordInput.getDiv());
        v.container.add(idRolInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteEmployee() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Empleado", 30);

        JButton deleteButton = new JButton("Eliminar Empleado");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    employeesService.deleteEmployee(id);
                    JOptionPane.showMessageDialog(v.container, "Empleado eliminado exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el empleado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllEmployees() {
        v = new ViewOut();
        JButton findButton = new JButton("Buscar Todos los Empleados");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.List<Employee> employees = employeesService.getAllEmployees();
                    StringBuilder employeesList = new StringBuilder("Lista de Empleados:\n");
                    for (Employee employee : employees) {
                        employeesList.append("ID: ").append(employee.getId())
                                .append(", Nombre: ").append(employee.getName())
                                .append(", ID Rol: ").append(employee.getIdrol())
                                .append(", Fecha de Ingreso: ").append(employee.getIngressdate())
                                .append(", ID Aerolínea: ").append(employee.getIdairline())
                                .append(", ID Aeropuerto: ").append(employee.getIdairport())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(v.container, employeesList.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al buscar los empleados: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.printBody(findButton, v.BackButton());
    }
}