package com.campuslands.modules.manufacturers.infrastructure.out;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.manufacturers.application.ManufacturersService;
import com.campuslands.modules.manufacturers.domain.models.Manufacturers;
import com.campuslands.modules.manufacturers.infrastructure.in.ManufacturersAdapter;


public class ManufacturersOutModule {

    protected ManufacturersMySqlRepository manufacturersMySQL;
    protected ManufacturersService manufacturersService;
    protected ManufacturersAdapter manufacturersAdapter;

    public ManufacturersOutModule() {
        manufacturersMySQL = new ManufacturersMySqlRepository(); // Initialize ManufacturersMySQL instance
        manufacturersService = new ManufacturersService(manufacturersMySQL); // Initialize ManufacturersService with
                                                                             // ManufacturersMySQL
        manufacturersAdapter = new ManufacturersAdapter(manufacturersService); // Initialize ManufacturersAdapter with
                                                                               // ManufacturersService
    }

    public ManufacturersAdapter module() {
        return manufacturersAdapter; // Return the ManufacturersAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("Fabricantes");
        option.add(new JMenuItem(new AbstractAction("Registrar Fabricantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturersAdapter.createManufacturer();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar Fabricantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturersAdapter.updateManufacturer();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Fabricantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturersAdapter.deleteManufacturer();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar Fabricantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Fabricantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturersAdapter.findAllManufacturers();
            }
        }));

        return option;
    }

    public List<String> selectOptions() {
        List<String> optionsMap = new ArrayList<String>();

        List<Manufacturers> all = manufacturersService.getAllManufacturers();

        for (Manufacturers item : all) {
            optionsMap.add(item.getId() + " " + item.getName());
        }

        return optionsMap;
    }
}
