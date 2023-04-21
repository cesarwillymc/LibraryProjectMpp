module com.cesarwillymc.libraryprojectmpp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.cesarwillymc.libraryprojectmpp to javafx.fxml;
    exports com.cesarwillymc.libraryprojectmpp;
}