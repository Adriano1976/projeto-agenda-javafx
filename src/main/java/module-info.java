module com.projetos.agenda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires org.hibernate.orm.core;

    opens com.projetos.agenda to javafx.fxml;
    opens com.projetos.agenda.icons to javafx.fxml;
    opens com.projetos.agenda.controller to javafx.fxml;

    exports com.projetos.agenda;
    exports com.projetos.agenda.controller;
}