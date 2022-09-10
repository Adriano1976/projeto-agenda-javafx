module com.projetos.agenda {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;//
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires hibernate.entitymanager;
    requires hibernate.java8;
    requires java.naming;
    requires java.sql;
    requires java.desktop;

    opens com.projetos.agenda to javafx.graphics ,javafx.fxml, javafx.base;
    opens com.projetos.agenda.icons to javafx.fxml;
    opens com.projetos.agenda.dao to javafx.fxml;
    opens com.projetos.agenda.controller to javafx.fxml;
    opens com.projetos.agenda.model to jakarta.persistence, org.hibernate.orm.core, javafx.base;

    exports com.projetos.agenda;
    exports com.projetos.agenda.controller;
    exports com.projetos.agenda.model;
    exports com.projetos.agenda.dao;
    exports com.projetos.agenda.util;
}
