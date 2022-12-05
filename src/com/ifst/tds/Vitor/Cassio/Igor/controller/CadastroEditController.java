package com.ifst.tds.Vitor.Cassio.Igor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.ifsc.tds.Vitor.Cassio.Igor.entity.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroEditController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;
    
    private Stage janelaCadastroEdit;

	private Cliente cadastro;

	private boolean okClick = false;

    @FXML
    void onClickBtnCancelar(ActionEvent event) {
    	this.getJanelaCadastroEdit().close();
    }

    @FXML
    void onClickBtnConfirmar(ActionEvent event) {
    	//if (validarCampos()) {
			this.cadastro.setNome(this.txtNome.getText());
			this.cadastro.setTelefone(this.txtTelefone.getText());
			this.cadastro.setEmail(this.txtEmail.getText());

			this.okClick = true;
			this.getJanelaCadastroEdit().close();
		//}
	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

	}
    
	public Stage getJanelaCadastroEdit() {
		return janelaCadastroEdit;
	}

	public void setJanelaCadastroEdit(Stage janelaCadastroEdit) {
		this.janelaCadastroEdit = janelaCadastroEdit;
	}
	
	public void populaTela(Cliente cadastro) {
		this.cadastro = cadastro;

		this.txtNome.setText(cadastro.getNome());
		this.txtTelefone.setText(cadastro.getTelefone());
		this.txtEmail.setText(cadastro.getEmail());
	}

	public boolean isOkClick() {
		return okClick;
	}
	
	/*private boolean validarCampos() {
		String mensagemErros = new String();

		if (this.txtNome.getText() == null || this.txtNome.getText().trim().length() == 0) {
			mensagemErros += "Informe o nome!\n";
		}

		if (this.txtTelefone.getText() == null || this.txtTelefone.getText().trim().length() == 0) {
			mensagemErros += "Informe o telefone!\n";
		}
		
		if (this.txtEmail.getText() == null || this.txtEmail.getText().trim().length() == 0) {
			mensagemErros += "Informe o e-mail!\n";
		}

		if (mensagemErros.length() == 0) {
			return true;
			
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.initOwner(this.janelaCadastroEdit);
			alerta.setTitle("Dados inv�lidos!");
			alerta.setHeaderText("Favor corrigir as seguintes informa��es:");
			alerta.setContentText(mensagemErros);
			alerta.showAndWait();

			return false;
		}
	}*/

}

