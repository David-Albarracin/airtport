package com.campuslands.modules.tripcrews.infrastructure.in;

import com.campuslands.modules.tripcrews.domain.models.Tripcrews;
import com.campuslands.modules.employees.infrastructure.out.EmployeesOutModule;
import com.campuslands.modules.flightconnections.infrastructure.out.FlightConnectionsOutModule;
import com.campuslands.modules.tripcrews.application.TripcrewsService; // Asegúrate de importar el servicio correcto
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class TripcrewsAdapter {
    private ViewOut v;
    private final TripcrewsService tripcrewsService;

    EmployeesOutModule employeesOutModule;
    FlightConnectionsOutModule flightConnectionsOutModule;

    public TripcrewsAdapter(TripcrewsService tripcrewsService) {
        this.tripcrewsService = tripcrewsService;
        this.employeesOutModule = new EmployeesOutModule();
        this.flightConnectionsOutModule = new FlightConnectionsOutModule();

    }

    public void createTripcrew() {
        v = new ViewOut();
        ViewOut.VSelect idEmployeesInput = v.new VSelect("Ingresa el ID del Empleado", employeesOutModule.selectOptions());
        ViewOut.VSelect idConnectionInput = v.new VSelect("Ingresa el ID de la Conexión", flightConnectionsOutModule.selectOptions());

        JButton addButton = new JButton("Agregar Tripcrew");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEmployees = Integer.parseInt(idEmployeesInput.getValue());
                    int idConnection = Integer.parseInt(idConnectionInput.getValue());
                    Tripcrews tripcrew = new Tripcrews(idEmployees, idConnection);
                    tripcrewsService.createTripcrew(tripcrew);

                    // JOptionPane.showMessageDialog(v.container, "Tripcrew agregado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al agregar el Tripcrew: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeesInput.getDiv());
        v.container.add(idConnectionInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updateTripcrew() {
        v = new ViewOut();
        ViewOut.VSelect idEmployeesInput = v.new VSelect("Ingresa el ID del Empleado", employeesOutModule.selectOptions());
        ViewOut.VSelect idConnectionInput = v.new VSelect("Ingresa el ID de la Conexión", flightConnectionsOutModule.selectOptions());

        JButton updateButton = new JButton("Actualizar Tripcrew");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEmployees = Integer.parseInt(idEmployeesInput.getValue());
                    int idConnection = Integer.parseInt(idConnectionInput.getValue());

                    Tripcrews tripcrew = new Tripcrews(idEmployees, idConnection);
                    tripcrewsService.updateTripcrew(tripcrew);
                    // JOptionPane.showMessageDialog(v.container, "Tripcrew actualizado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al actualizar el Tripcrew: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeesInput.getDiv());
        v.container.add(idConnectionInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deleteTripcrew() {
        v = new ViewOut();
        ViewOut.VInput idEmployeesInput = v.new VInput("Ingresa el ID del Empleado", 30);

        JButton deleteButton = new JButton("Eliminar Tripcrew");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    tripcrewsService.deleteTripcrew(idEmployeesInput.getInt());
                    // JOptionPane.showMessageDialog(v.container, "Tripcrew eliminado
                    // exitosamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container, "Error al eliminar el Tripcrew: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idEmployeesInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllTripcrews() {
        v = new ViewOut();
        List<Tripcrews> tripCrew = tripcrewsService.getAllTripcrews();
        String[] columnNames = { "id empleado", "id conexión" };
        Object[][] data = new Object[tripCrew.size()][6];

        for (int i = 0; i < tripCrew.size(); i++) {
            Tripcrews tripCrews = tripCrew.get(i);
            data[i][0] = tripCrews.getIdemployees();
            data[i][1] = tripCrews.getIdconection();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findByIdTripCrews() {
        try {
            v = new ViewOut();
            ViewOut.VInput idInput = v.new VInput("Ingresa el ID de la Tripulación de Viaje a Buscar", 30);

            JButton searchButton = new JButton("Buscar Tripulación de Viaje");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JPanel lastWindow = v.body; // Store the previous window panel
                        v = new ViewOut(); // Create a new instance of ViewOut
                        int id = idInput.getInt();
                        Optional<Tripcrews> tripCrewOptional = tripcrewsService.getTripcrewById(id);
                        if (tripCrewOptional.isPresent()) {
                            Tripcrews tripCrew = tripCrewOptional.get();
                            String[] columnNames = { "ID Empleado", "ID Conexión" };
                            Object[][] data = new Object[1][2];
                            data[0][0] = tripCrew.getIdemployees();
                            data[0][1] = tripCrew.getIdconection();

                            v.container.add(v.new VTable(columnNames, data).getDiv());
                            v.printBody(v.BackButton("findByIdTripCrews", lastWindow));
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "No se encontró la tripulación de viaje con el ID especificado", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Ingrese un valor numérico para el ID de la tripulación de viaje", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(v.container,
                                "Error al buscar la tripulación de viaje: " + ex.getMessage(), "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            v.container.add(idInput.getDiv());
            v.printBody(searchButton, v.BackButton());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error al iniciar la búsqueda de la tripulación de viaje: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
