package com.campuslands.modules.planes.infrastructure.in;

import com.campuslands.modules.planes.domain.models.Planes;
import com.campuslands.modules.statuses.infrastructure.out.StatusesOutModule;
import com.campuslands.modules.manufacturers.infrastructure.out.ManufacturersOutModule;
import com.campuslands.modules.planes.application.PlanesService;
import com.campuslands.views.infrastructure.out.ViewOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PlanesAdapter {
    private ViewOut v;
    private final PlanesService planesService;

    StatusesOutModule statusesOutModule;
    ManufacturersOutModule manufacturersOutModule;

    public PlanesAdapter(PlanesService planesService) {
        this.planesService = planesService;
        this.statusesOutModule = new StatusesOutModule();
        this.manufacturersOutModule = new ManufacturersOutModule();
    }

    public void createPlane() {
        v = new ViewOut();
        ViewOut.VInput platesInput = v.new VInput("Ingresa las placas del avión", 30);
        ViewOut.VInput capacityInput = v.new VInput("Ingresa la capacidad del avión", 30);
        ViewOut.VDate fabricationDateInput = v.new VDate("Ingresa la fecha de fabricación del avión (YYYY-MM-DD)",
                "date");
        ViewOut.VSelect idStatusInput = v.new VSelect("Ingresa el ID de estado del avión",
                statusesOutModule.selectOptions());
        ViewOut.VSelect idModelInput = v.new VSelect("Ingresa el ID del modelo del avión",
                manufacturersOutModule.selectOptions());

        JButton addButton = new JButton("Agregar Nuevo Avión");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String plates = platesInput.getText();
                    int capacity = capacityInput.getInt();
                    Date fabricationDate = fabricationDateInput.getValue();
                    int idStatus = Integer.parseInt(idStatusInput.getValue());
                    int idModel = Integer.parseInt(idModelInput.getValue());
                    Planes plane = new Planes(plates, capacity, fabricationDate, idStatus, idModel);
                    planesService.createPlane(plane);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al agregar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(platesInput.getDiv());
        v.container.add(capacityInput.getDiv());
        v.container.add(fabricationDateInput.getDiv());
        v.container.add(idStatusInput.getDiv());
        v.container.add(idModelInput.getDiv());
        v.printBody(addButton, v.BackButton());
    }

    public void updatePlane() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Avión", 30);
        ViewOut.VInput platesInput = v.new VInput("Ingresa las placas del avión", 30);
        ViewOut.VInput capacityInput = v.new VInput("Ingresa la capacidad del avión", 30);
        ViewOut.VDate fabricationDateInput = v.new VDate("Ingresa la fecha de fabricación del avión (YYYY-MM-DD)",
                "date");
        ViewOut.VSelect idStatusInput = v.new VSelect("Ingresa el ID de estado del avión",
                statusesOutModule.selectOptions());
        ViewOut.VSelect idModelInput = v.new VSelect("Ingresa el ID del modelo del avión",
                manufacturersOutModule.selectOptions());

        JButton updateButton = new JButton("Actualizar Avión");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    String plates = platesInput.getText();
                    int capacity = capacityInput.getInt();
                    Date fabricationDate = fabricationDateInput.getValue();
                    int idStatus = Integer.parseInt(idStatusInput.getValue());
                    int idModel = Integer.parseInt(idModelInput.getValue());

                    Planes plane = new Planes(id, plates, capacity, fabricationDate, idStatus, idModel);
                    planesService.updatePlane(plane);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al actualizar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.container.add(platesInput.getDiv());
        v.container.add(capacityInput.getDiv());
        v.container.add(fabricationDateInput.getDiv());
        v.container.add(idStatusInput.getDiv());
        v.container.add(idModelInput.getDiv());
        v.printBody(updateButton, v.BackButton());
    }

    public void deletePlane() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Avión a Eliminar", 30);

        JButton deleteButton = new JButton("Eliminar Avión");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = idInput.getInt();
                    planesService.deletePlane(id);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al eliminar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

    public void findAllPlanes() {
        v = new ViewOut();
        List<Planes> planes = planesService.getAllPlanes();
        String[] columnNames = { "ID", "Placas", "Capacidad", "Fecha de Fabricación", "ID de Estado", "ID de Modelo" };
        Object[][] data = new Object[planes.size()][6];

        for (int i = 0; i < planes.size(); i++) {
            Planes plane = planes.get(i);
            data[i][0] = plane.getId();
            data[i][1] = plane.getPlateNumber();
            data[i][2] = plane.getCapacity();
            data[i][3] = plane.getFabrication_date();
            data[i][4] = plane.getId_status();
            data[i][5] = plane.getId_model();
        }

        v.container.add(v.new VTable(columnNames, data).getDiv());
        v.printBody(v.BackButton());
    }

    public void findById() {
        v = new ViewOut();
        ViewOut.VInput idInput = v.new VInput("Ingresa el ID del Avión a Buscar", 30);

        JButton deleteButton = new JButton("Buscar Avion");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel lastWindow = v.body;
                    v = new ViewOut();
                    int id = idInput.getInt();
                    Optional<Planes> planeOptional = planesService.getPlaneById(id);
                    if (planeOptional.isPresent()) {
                        Planes plane = planeOptional.get();
                        String[] columnNames = { "ID", "Placas", "Capacidad", "Fecha de Fabricación", "ID de Estado",
                                "ID de Modelo" };
                        Object[][] data = new Object[1][6];

                        data[0][0] = plane.getId();
                        data[0][1] = plane.getPlateNumber();
                        data[0][2] = plane.getCapacity();
                        data[0][3] = plane.getFabrication_date();
                        data[0][4] = plane.getId_status();
                        data[0][5] = plane.getId_model();

                        v.container.add(v.new VTable(columnNames, data).getDiv());
                        v.printBody(v.BackButton("findByIdPlane", lastWindow));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se Encontro el id", null, id);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(v.container,
                            "Error al Buscar el avión: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        v.container.add(idInput.getDiv());
        v.printBody(deleteButton, v.BackButton());
    }

}
