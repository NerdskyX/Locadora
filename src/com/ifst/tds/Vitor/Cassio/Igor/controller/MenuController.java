package com.ifst.tds.Vitor.Cassio.Igor.controller;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Menu menuAjuda;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuCadastro;

    @FXML
    private MenuItem mnClientes;

    @FXML
    private MenuItem mnEmprestimos;

    @FXML
    private MenuItem mnFilmes;

    @FXML
    private MenuItem mnFilmes1;

    @FXML
    private MenuItem mnSair;

    @FXML
    private MenuItem mnSobre;

    @FXML
    private VBox pnlPrincipal;
    
    private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

    @FXML
    void onClickMnClientes(ActionEvent event) {
    	
    }

    @FXML
    void onClickMnEmprestimos(ActionEvent event) {

    }

    @FXML
    void onClickMnF(ActionEvent event) {

    }

    @FXML
    void onClickMnFilmes(ActionEvent event) {

    }

    @FXML
    void onClickMnSair(ActionEvent event) {

    }

    @FXML
    void onClickMnSobre(ActionEvent event) {

    }

}
