module com.projetos.agenda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.projetos.agenda to javafx.fxml;
    exports com.projetos.agenda;
}