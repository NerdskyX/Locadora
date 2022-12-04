package com.ifst.tds.Vitor.Cassio.Igor.controller;


import com.ifsc.tds.Vitor.Cassio.Igor.entity.Filmes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FilmeAddController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtLancamento;

    @FXML
    private TextField txtNome;
    
    private Stage janelaFilmeAdd;

	private Filmes filme;

	private boolean okClick = false;

    @FXML
    void onClickBtnCancelar(ActionEvent event) {
    	this.getJanelaFilmeAdd().close();
    	
    }

    @FXML
    void onClickBtnConfirmar(ActionEvent event) {
    	if (validarCampos()) {
			this.filme.setNome(this.txtNome.getText());
			this.filme.setData_lancamento(this.txtLancamento.getText());

			this.okClick = true;
			this.getJanelaFilmeAdd().close();
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
    
    public Stage getJanelaFilmeAdd() {
		return janelaFilmeAdd;
    }
    
    public void setJanelafilmeEdit(Stage janelaFilmeAdd) {
		this.janelaFilmeAdd = janelaFilmeAdd;
	}

	public void populaTela(Filmes filme) {
		this.filme = filme;

		this.txtNome.setText(this.filme.getNome());
		this.txtLancamento.setText(this.filme.getData_lancamento());
	}

	public boolean isOkClick() {
		return okClick;
	}

    private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
			mensagemErros += "Informe o nome!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaFilmeAdd);
			alerta.setTitle("Dados inv�lidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informa��es:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}
}
