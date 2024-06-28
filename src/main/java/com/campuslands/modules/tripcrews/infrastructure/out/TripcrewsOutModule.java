package com.campuslands.modules.tripcrews.infrastructure.out;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.campuslands.modules.tripcrews.application.TripcrewsService;
import com.campuslands.modules.tripcrews.infrastructure.in.TripcrewsAdapter;

public class TripcrewsOutModule {

    protected TripcrewsMySQL tripcrewsMySQL;
    protected TripcrewsService tripcrewsService;
    protected TripcrewsAdapter tripcrewsAdapter;

    public TripcrewsOutModule() {
        tripcrewsMySQL = new TripcrewsMySQL();
        tripcrewsService = new TripcrewsService(tripcrewsMySQL);
        tripcrewsAdapter = new TripcrewsAdapter(tripcrewsService);
    }

    public TripcrewsAdapter module() {
        return tripcrewsAdapter; // Return the TripcrewsAdapter instance
    }

    public JMenu options() {
        JMenu option = new JMenu("tripulantes");
        option.add(new JMenuItem(new AbstractAction("Registrar tripulante") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripcrewsAdapter.createTripcrew();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Actualizar tripulante") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripcrewsAdapter.updateTripcrew();

            }
        }));

        option.add(new JMenuItem(new AbstractAction("Eliminar tripulante") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripcrewsAdapter.deleteTripcrew();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Buscar  tripulante") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripcrewsAdapter.findByIdTripCrews();
            }
        }));

        option.add(new JMenuItem(new AbstractAction("Mostrar todos los tripulantes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripcrewsAdapter.findAllTripcrews();
            }
        }));

        return option;
    }

    
}
