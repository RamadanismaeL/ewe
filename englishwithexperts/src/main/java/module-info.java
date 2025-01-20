module ram.ewe.englishwithexperts {
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires mysql.connector.j;
    requires org.xerial.sqlitejdbc;
    requires jbcrypt;
    requires itextpdf;
    requires javafx.swing;

    opens ram.ewe.englishwithexperts to javafx.fxml;
    exports ram.ewe.englishwithexperts;

    exports ram.ewe.englishwithexperts.Controllers;
    exports ram.ewe.englishwithexperts.Controllers.Alerts.Aviso;
    exports ram.ewe.englishwithexperts.Controllers.Alerts.Confirmacao;
    exports ram.ewe.englishwithexperts.Controllers.Alerts.Erro;
    exports ram.ewe.englishwithexperts.Controllers.Alerts.Informacao;
    exports ram.ewe.englishwithexperts.Controllers.Alerts.Sucesso;
    exports ram.ewe.englishwithexperts.Controllers.Dashboard;
    exports ram.ewe.englishwithexperts.Controllers.Progress;
    exports ram.ewe.englishwithexperts.Controllers.Ferramentas;
    exports ram.ewe.englishwithexperts.Controllers.Registrations;
    exports ram.ewe.englishwithexperts.Controllers.Users;
    exports ram.ewe.englishwithexperts.Controllers.Receipt;

    exports ram.ewe.englishwithexperts.Data;
    exports ram.ewe.englishwithexperts.Models;
    exports ram.ewe.englishwithexperts.Repositories;
    exports ram.ewe.englishwithexperts.Views;
}