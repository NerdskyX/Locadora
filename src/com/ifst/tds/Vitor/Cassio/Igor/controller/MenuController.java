package com.ifst.tds.Vitor.Cassio.Igor.controller;

import java.util.Optional;

import com.peregrinoti.controller.CaixaListaController;
import com.peregrinoti.controller.TipoColecaoListaController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
    	try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/Vitor/Cassio/Igor/view/CadastroListaController.fxml"));
			Parent CadastroListaXML = loader.load();

			// carregando o controller e a scene
			CadastroListaController CadastroListaController = loader.getController();
			Scene CadastroListaLayout = new Scene(CadastroListaXML);

			this.getStage().setScene(CadastroListaLayout);
			this.getStage().setTitle("Cadastro de Cliente");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (CadastroListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickMnEmprestimos(ActionEvent event) {
    	try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/Vitor/Cassio/Igor/view/EmprestimoListaController.fxml"));
			Parent EmprestimoListaXML = loader.load();

			// carregando o controller e a scene
			EmprestimoListaController EmprestimoListaController = loader.getController();
			Scene tipoColecaoListaLayout = new Scene(EmprestimoListaXML);

			this.getStage().setScene(tipoColecaoListaLayout);
			this.getStage().setTitle("Cadastro de tipo de cole��o");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (EmprestimoListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @FXML
    void onClickMnFilmes(ActionEvent event) {
    	try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifsc/tds/Vitor/Cassio/Igor/view/FilmeListaController.fxml"));
			Parent FilmeListaXML = loader.load();

			// carregando o controller e a scene
			FilmeListaController FilmeListaController = loader.getController();
			Scene FilmeListaLayout = new Scene(FilmeListaXML);

			this.getStage().setScene(FilmeListaLayout);
			this.getStage().setTitle("Cadastro de tipo de cole��o");

			// atribuindo evento para fechar janela
			this.getStage().setOnCloseRequest(e -> {
				if (FilmeListaController.onCloseQuery()) {
					this.getStage().close();
				} else {
					e.consume();
				}
			});

			this.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onClickMnSair(ActionEvent event) {
    	if (this.onCloseQuery()) {
			System.exit(0);
		} else {
			event.consume();
		}
    }

    @FXML
    void onClickMnSobre(ActionEvent event) {
    	Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Sobre");
		alerta.setHeaderText("Trabalho java Vitor Cassio e Igor piggers :)");
		alerta.showAndWait();
    }
    
    public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do sistema?");
		ButtonType botaoNao = ButtonType.NO;
		ButtonType botaoSim = ButtonType.YES;
		alerta.getButtonTypes().setAll(botaoSim, botaoNao);
		Optional<ButtonType> resultado = alerta.showAndWait();
		return resultado.get() == botaoSim ? true : false;
	}

}
