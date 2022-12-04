package com.ifst.tds.Vitor.Cassio.Igor.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.ifsc.tds.Vitor.Cassio.Igor.dao.ClienteDAO;
import com.ifsc.tds.Vitor.Cassio.Igor.entity.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastroListaController {

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluir;

    @FXML
    private Label lblData;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private TableColumn<Cliente, Long> tbcCodigo;

    @FXML
    private TableColumn<Cliente, String> tbcNome;

    @FXML
    private TableView<Cliente> tbvCadastro;
    
    private List<Cliente> listaCliente;
	private ObservableList<Cliente> observableListaCliente = FXCollections.observableArrayList();
	private ClienteDAO ClienteDAO;

	public static final String CLIENTE_EDITAR = " - Editar";
	public static final String CLIENTE_INCLUIR = " - Incluir";

    @FXML
    void onClickBtnEditar(ActionEvent event) {
    	Cliente cliente = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (cliente != null) {
			boolean btnConfirmarClic = this.onShowTelaclienteEditar(cliente, CadastroListaController.CLIENTE_EDITAR);

			if (btnConfirmarClic) {
				this.getClienteDAO().update(cliente, null);
				this.carregarTableViewClientes();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma cliente na tabela!");
			alerta.show();
		}
    }

    @FXML
    void onClickBtnExcluir(ActionEvent event) {
    	Cliente cliente = this.tbvCadastro.getSelectionModel().getSelectedItem();

		if (cliente != null) {

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Pergunta");
			alerta.setHeaderText("Confirma a exclus�o do cliente?\n" + cliente.getNome());

			ButtonType botaoNao = ButtonType.NO;
			ButtonType botaoSim = ButtonType.YES;
			alerta.getButtonTypes().setAll(botaoSim, botaoNao);
			Optional<ButtonType> resultado = alerta.showAndWait();

			if (resultado.get() == botaoSim) {
				this.getClienteDAO().delete(cliente);
				this.carregarTableViewClientes();
			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setContentText("Por favor, escolha uma cliente na tabela!");
			alerta.show();
		}
    }

    @FXML
    void onClickBtnIncluir(ActionEvent event) {
    	Cliente cliente = new Cliente();

		boolean btnConfirmarClic = this.onShowTelaClienteEditar(cliente, CadastroListaController.CLIENTE_INCLUIR);

		if (btnConfirmarClic) {
			this.getClienteDAO().save(cliente);
			this.carregarTableViewClientes();
		}
    }
    
    public ClienteDAO getClienteDAO() {
		return ClienteDAO;
	}

	public void setClienteDAO(ClienteDAO ClienteDAO) {
		this.ClienteDAO = ClienteDAO;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public ObservableList<Cliente> getObservableListaCliente() {
		return observableListaCliente;
	}

	public void setObservableListaCliente(ObservableList<Cliente> observableListaCliente) {
		this.observableListaCliente = observableListaCliente;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		this.setClienteDAO(new ClienteDAO());
		this.carregarTableViewClientes();
		this.selecionarItemTableViewClientes(null);

		this.tbvCadastro.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
	}

	public void carregarTableViewClientes() {
		this.tbcCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.tbcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

		this.setListaCliente(this.getClienteDAO().getAll());
		this.setObservableListaCliente(FXCollections.observableArrayList(this.getListaCliente()));
		this.tbvCadastro.setItems(this.getObservableListaCliente());
	}

	public void selecionarItemTableViewClientes(Cliente Cliente) {
		if (Cliente != null) {
			this.lblNome.setText(Cliente.getNome());
			this.lblTelefone.setText(Cliente.getTelefone());
			this.lblEmail.setText(Cliente.getEmail());
			this.lblData.setText(Cliente.getData_cadastro());
		} else {
			this.lblNome.setText("");
			this.lblTelefone.setText("");
			this.lblEmail.setText("");
			this.lblData.setText("");
		}
	}

	public boolean onCloseQuery() {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Pergunta");
		alerta.setHeaderText("Deseja sair do cadastro de Cliente?");
		ButtonType buttonTypeNO = ButtonType.NO;
		ButtonType buttonTypeYES = ButtonType.YES;
		alerta.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
		Optional<ButtonType> result = alerta.showAndWait();
		return result.get() == buttonTypeYES ? true : false;
	}

	public boolean onShowTelaClienteEditar(Cliente Cliente, String operacao) {
		try {
			// carregando o loader
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ifst/tds/Vitor/Cassio/Igor/controller/CadastroEditController.fxml"));
			Parent ClienteEditXML = loader.load();

			// criando uma janela nova
			Stage janelaClienteEditar = new Stage();
			janelaClienteEditar.setTitle("Cadastro de Cliente" + operacao);
			janelaClienteEditar.initModality(Modality.APPLICATION_MODAL);
			janelaClienteEditar.resizableProperty().setValue(Boolean.FALSE);

			Scene ClienteEditLayout = new Scene(ClienteEditXML);
			janelaClienteEditar.setScene(ClienteEditLayout);

			// carregando o controller e a scene
			CadastroEditController ClienteEditController = loader.getController();
			CadastroEditController.setJanelaCadastroEdit(janelaClienteEditar);
			CadastroEditController.populaTela(Cliente);

			// mostrando a tela
			janelaClienteEditar.showAndWait();

			return ClienteEditController.isOkClick();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Cliente> retornaListagemCliente() {
		if (this.getClienteDAO() == null) {
			this.setClienteDAO(new ClienteDAO());
		}
		return this.getClienteDAO().getAll();
	}

}
