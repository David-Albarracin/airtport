package com.campuslands.modules.flightconnections.infrastructure.out;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.flightconnections.application.FlightConnectionsService;
import com.campuslands.modules.flightconnections.domain.models.FlightConnection;
import com.campuslands.modules.flightconnections.infrastructure.in.FlightConnectionsAdapter;


public class FlightConnectionsOutModule {

    protected FlightConnectionsMySqlRespository flightConnectionsMySQL;
    protected FlightConnectionsService flightConnectionsService;
    protected FlightConnectionsAdapter flightConnectionsAdapter;

    public FlightConnectionsOutModule() {
        flightConnectionsMySQL = new FlightConnectionsMySqlRespository();
        flightConnectionsService = new FlightConnectionsService(flightConnectionsMySQL);
        flightConnectionsAdapter = new FlightConnectionsAdapter(flightConnectionsService);

    }

    public FlightConnectionsAdapter module() {
        return flightConnectionsAdapter;
    }

    public JMenu options() {
        JMenu option = new JMenu(" Conexiónes de vuelos ");
        option.add(new JMenuItem(new AbstractAction("Registrar  Conexiónes de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightConnectionsAdapter.createFlightConnection();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar   Conexiónes de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightConnectionsAdapter.updateFlightConnection();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar Conexiónes de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightConnectionsAdapter.deleteFlightConnection();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar  Conexiónes de vuelo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adapter.VFindAirportAll();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los Conexiónes de vuelos ") {
            @Override
            public void actionPerformed(ActionEvent e) {
                flightConnectionsAdapter.findAllFlightConnections();
            }
        }));

        return option;
    }

     public List<String> selectOptions() {
        List<String> optionsMap = new ArrayList<String>();

        List<FlightConnection> all = flightConnectionsService.getAllFlightConnections();

        for (FlightConnection item : all) {
            optionsMap.add(item.getId() + " " + item.getConnection_number()+ " " + item.getLast_Scale());
        }

        return optionsMap;
    }
}
