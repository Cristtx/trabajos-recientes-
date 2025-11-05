package com.ventaboletosaviones.controller;

import com.ventaboletosaviones.model.Cliente;
import com.ventaboletosaviones.service.ClienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteController {

    @FXML private TextField txtDni, txtNombres, txtRepLegal, txtEmail, txtBuscar;
    @FXML private TableView<Cliente> tableClientes;
    @FXML private TableColumn<Cliente, String> colDni, colNombres;
    @FXML private Label lblMsg;

    private final ClienteService clienteService;
    private ObservableList<Cliente> lista = FXCollections.observableArrayList();

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @FXML
    public void initialize() {
        colDni.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDniruc()));
        colNombres.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombres()));
        tableClientes.setItems(lista);
        tableClientes.setRowFactory(tv -> {
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(ev -> {
                if (ev.getClickCount() == 2 && !row.isEmpty()) {
                    Cliente c = row.getItem();
                    cargarEnFormulario(c);
                }
            });
            return row;
        });
        listar();
    }

    private void listar() {
        lista.setAll(clienteService.findAll());
    }

    @FXML
    private void onBuscar() {
        String f = txtBuscar.getText();
        List<Cliente> res = clienteService.search(f);
        lista.setAll(res);
        lblMsg.setText("");
    }

    @FXML
    private void onNuevo() {
        limpiar();
    }

    @FXML
    private void onGuardar() {
        String dni = txtDni.getText()==null?"":txtDni.getText().trim();
        String nombres = txtNombres.getText()==null?"":txtNombres.getText().trim();
        if (dni.isEmpty() || nombres.isEmpty()) { lblMsg.setText("DNI y Nombres son obligatorios"); return; }
        if (dni.length() < 6) { lblMsg.setText("DNI demasiado corto"); return; }
        Cliente c = new Cliente(dni, nombres, txtRepLegal.getText(), txtEmail.getText());
        if (clienteService.findById(dni) != null) {
            clienteService.update(dni, c);
            lblMsg.setText("Cliente actualizado"); 
        } else {
            clienteService.save(c);
            lblMsg.setText("Cliente guardado");
        }
        listar();
        limpiar();
    }

    @FXML
    private void onEliminar() {
        Cliente sel = tableClientes.getSelectionModel().getSelectedItem();
        if (sel==null) { lblMsg.setText("Seleccione cliente"); return; }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Eliminar cliente " + sel.getNombres() + "?", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> res = a.showAndWait();
        if (res.isPresent() && res.get()==ButtonType.OK) {
            clienteService.delete(sel.getDniruc()); listar(); limpiar(); lblMsg.setText("Cliente eliminado"); 
        }
    }

    private void limpiar() {
        txtDni.clear(); txtNombres.clear(); txtRepLegal.clear(); txtEmail.clear(); txtBuscar.clear(); lblMsg.setText(""); 
    }

    private void cargarEnFormulario(Cliente c) {
        txtDni.setText(c.getDniruc()); txtNombres.setText(c.getNombres()); txtRepLegal.setText(c.getRepLegal()); txtEmail.setText(c.getEmail()); lblMsg.setText(""); 
    }
}
