package com.ventaboletosaviones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SysVentApp extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SysVentApp.class);
        builder.application().setWebApplicationType(WebApplicationType.NONE);
        context = builder.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/view_cliente.fxml"));
        loader.setControllerFactory(context::getBean);
        Scene scene = new Scene(loader.load(), 900, 600);
        stage.setScene(scene);
        stage.setTitle("VentaBoletosAviones - Clientes");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (context != null) context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
