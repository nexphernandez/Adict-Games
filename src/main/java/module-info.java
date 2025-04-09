module backend.es.nexphernandez.adict.games {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires java.xml;
    requires java.sql;

    opens backend.es.nexphernandez.adict.games to javafx.fxml;
    exports backend.es.nexphernandez.adict.games;
    exports backend.es.nexphernandez.adict.games.controller;
    exports backend.es.nexphernandez.adict.games.controller.abstractas;
    exports backend.es.nexphernandez.adict.games.model;
    exports backend.es.nexphernandez.adict.games.model.servicios;
    exports backend.es.nexphernandez.adict.games.model.abstractas;

    opens backend.es.nexphernandez.adict.games.controller to javafx.fxml;
}