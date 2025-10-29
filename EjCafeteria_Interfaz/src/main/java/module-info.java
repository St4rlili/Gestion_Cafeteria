module com.example.ejcafeteria_interfaz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejcafeteria_interfaz to javafx.fxml;
    exports com.example.ejcafeteria_interfaz;
}