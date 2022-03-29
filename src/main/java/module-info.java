module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens ru.leb.java_3_at_24_03_2022.client to javafx.fxml;
    exports ru.leb.java_3_at_24_03_2022.client;
}