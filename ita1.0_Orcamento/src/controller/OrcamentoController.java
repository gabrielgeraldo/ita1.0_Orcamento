package controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Cliente;
import model.ItemOrcamento;
import model.Orcamento;
import model.Produto;
import model.Usuario;
import ws.WsUtil;

public class OrcamentoController implements Initializable {

	@FXML
	private Label labelCliente;

	@FXML
	private TableView<Produto> tabelaProdutos;

	@FXML
	private TableColumn<Produto, String> codigoTabelaProdutos;

	@FXML
	private TableColumn<Produto, String> descricaoTabelaProdutos;

	@FXML
	private TableColumn<Produto, String> precoUnitarioTabelaProdutos;

	@FXML
	private TableView<ItemOrcamento> tabelaItensOrcamento;

	@FXML
	private TableColumn<ItemOrcamento, String> itemTabelaItensOrcamento;

	@FXML
	private TableColumn<ItemOrcamento, String> produtoTabelaItensOrcamento;

	@FXML
	private TableColumn<ItemOrcamento, String> qtdTabelaItensOrcamento;

	@FXML
	private TableColumn<ItemOrcamento, String> precoVendaTabelaItensOrcamento;

	@FXML
	private TextField textFieldPesquisaProduto;

	@FXML
	private ComboBox<Cliente> comboClientes;

	@FXML
	private RadioButton radioCodigo;

	@FXML
	private RadioButton radioDescricao;

	@FXML
	private Spinner<Integer> spinnerQdt;

	@FXML
	private ToggleGroup tipoPesquisaProduto;

	private List<ItemOrcamento> itensOrcamento = new ArrayList<ItemOrcamento>();

	private ItemOrcamento itemOrcamento = new ItemOrcamento();

	private Orcamento orcamento = new Orcamento();

	@FXML
	private Label labelTotal;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		orcamento.setTotal(new BigDecimal("0.00"));
		labelTotal.setText(orcamento.getTotal().toString());

		this.preencherComboBoxCliente();

	}

	public void preencherComboBoxCliente() {
		ObservableList<Cliente> lista = FXCollections.observableArrayList(WsUtil.getClientes());
		comboClientes.setItems(lista);

		// WsUtil.getRazaoSocial();

	}

	@FXML
	void selecionaCliente(ActionEvent event) {
		Cliente cliente = comboClientes.getValue();
		orcamento.setCliente(cliente);
	}

	@FXML
	public void pesquisaProduto(ActionEvent event) {

		int tipoPesquisaProduto = 0;

		if (radioCodigo.isSelected())
			tipoPesquisaProduto = 1;
		if (radioDescricao.isSelected())
			tipoPesquisaProduto = 2;

		String promptext = textFieldPesquisaProduto.getText();

		if (WsUtil.completeCliente(promptext) != null) {

			codigoTabelaProdutos.setCellValueFactory(new PropertyValueFactory<>("codigo"));

			descricaoTabelaProdutos.setCellValueFactory(new PropertyValueFactory<>("descricao"));

			precoUnitarioTabelaProdutos.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));

			ObservableList<Produto> lista = FXCollections
					.observableArrayList(WsUtil.completeProduto(tipoPesquisaProduto, promptext));

			tabelaProdutos.setItems(lista);

			if (!tabelaProdutos.getItems().isEmpty()) {
				tabelaProdutos.getSelectionModel().select(0);
				tabelaProdutos.requestFocus();
			}

		}

	}

	@FXML
	public void selecionaLinhaTabelaProdutos(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {

			Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();

			textFieldPesquisaProduto.setText(null);
			spinnerQdt.requestFocus();

			this.adicionarProduto(produto);

		}
	}

	@FXML
	public void deletaLinhaTabelaItensOrcamento(KeyEvent event) {
		if (event.getCode() == KeyCode.DELETE) {

			ItemOrcamento produto = tabelaItensOrcamento.getSelectionModel().getSelectedItem();

			textFieldPesquisaProduto.setText(null);
			spinnerQdt.requestFocus();

			removeProduto(produto);

		}
	}

	public void adicionarProduto(Produto produto) {

		itemOrcamento.setQuantidade(spinnerQdt.getValue());
		itemOrcamento.setOrcamento(orcamento);

		// -------------------- Metodo adicionar.
		int posicaoEncntrada = -1;

		for (int i = 0; i < itensOrcamento.size() && posicaoEncntrada < 0; i++) {
			ItemOrcamento itemTemp = itensOrcamento.get(i);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncntrada = i;
			}
		}

		itemOrcamento.setProduto(produto);

		if (posicaoEncntrada < 0) {
			itemOrcamento.setPrecoCusto(produto.getPrecoCusto());
			itemOrcamento.setPrecoVenda(produto.getPrecoUnitario());
			itensOrcamento.add(itemOrcamento);
		} else {
			ItemOrcamento itemTemp = itensOrcamento.get(posicaoEncntrada);
			itemOrcamento.setQuantidade(itemTemp.getQuantidade() + itemOrcamento.getQuantidade());
			itemOrcamento.setPrecoCusto(produto.getPrecoCusto());
			itemOrcamento.setPrecoVenda(produto.getPrecoUnitario());
			itensOrcamento.set(posicaoEncntrada, itemOrcamento);
		}

		orcamento.setTotal(new BigDecimal("0.00"));
		for (int j = 0; j < itensOrcamento.size(); j++) {
			orcamento.setTotal(orcamento.getTotal().add(itensOrcamento.get(j).getPrecoVenda()
					.multiply(new BigDecimal(itensOrcamento.get(j).getQuantidade()))));

		}
		// -------------------- Metodo adicionar.

		itemTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		produtoTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("produto"));

		qtdTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		precoVendaTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));

		ObservableList<ItemOrcamento> lista = FXCollections.observableArrayList(itensOrcamento);

		tabelaItensOrcamento.setItems(lista);
		labelTotal.setText(orcamento.getTotal().toString());

		this.setItemOrcamento(new ItemOrcamento());

	}

	public void removeProduto(ItemOrcamento itemOrcamento) {

		// -------------------- Metodo remover.
		int posicaoEncntrada = -1;

		for (int i = 0; i < itensOrcamento.size() && posicaoEncntrada < 0; i++) {
			ItemOrcamento itemTemp = itensOrcamento.get(i);

			if (itemTemp.getProduto().equals(itemOrcamento.getProduto())) {
				posicaoEncntrada = i;
			}
		}

		if (posicaoEncntrada > -1) {
			itensOrcamento.remove(posicaoEncntrada);
		}

		orcamento.setTotal(new BigDecimal("0.00"));
		for (int j = 0; j < itensOrcamento.size(); j++) {
			orcamento.setTotal(orcamento.getTotal().add(itensOrcamento.get(j).getPrecoVenda()
					.multiply(new BigDecimal(itensOrcamento.get(j).getQuantidade()))));

		}
		// -------------------- Metodo remover.

		itemTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		produtoTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("produto"));

		qtdTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		precoVendaTabelaItensOrcamento.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));

		ObservableList<ItemOrcamento> lista = FXCollections.observableArrayList(itensOrcamento);

		tabelaItensOrcamento.setItems(lista);
		labelTotal.setText(orcamento.getTotal().toString());

	}

	@FXML
	public void clickBotaoFinalizar(ActionEvent event) {
		this.finalizar();
	}

	@FXML
	public void enterBotaoFinalizar(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			this.finalizar();
	}

	public void finalizar() {

		ObservableList<Usuario> lista = FXCollections.observableArrayList(WsUtil.getUsuarios());

		ChoiceDialog<Usuario> dialogoRegiao = new ChoiceDialog<Usuario>(null, lista);
		dialogoRegiao.setTitle("Entrada de Usuário");
		dialogoRegiao.setHeaderText("Informe seu usuário");
		dialogoRegiao.setContentText("Usuário:");

		Usuario usuario = null;
		Optional<Usuario> resultUsuario = dialogoRegiao.showAndWait();
		if (resultUsuario.isPresent()) {
			usuario = resultUsuario.get();
		}

		TextInputDialog dialogoNome = new TextInputDialog();
		dialogoNome.setTitle("Entrada de senha");
		dialogoNome.setHeaderText("Entre com sua senha");
		dialogoNome.setContentText("Senha:");

		String senha = null;
		Optional<String> resultSenha = dialogoNome.showAndWait();
		if (resultSenha.isPresent()) {
			senha = resultSenha.get();
		}

		orcamento.setUsuario(usuario);

		orcamento.setItens(itensOrcamento);

		if (usuario.getSenha().equals(senha)) {

			/*
			 * System.out.println(orcamento.getCodigo());
			 * System.out.println(orcamento.getCliente());
			 * System.out.println(orcamento.getData());
			 * System.out.println(orcamento.getTotal());
			 * System.out.println(orcamento.getUsuario());
			 * 
			 * for (int i = 0; i < orcamento.getItens().size(); i++) {
			 * System.out.println(orcamento.getItens().get(i));
			 * System.out.println(orcamento.getItens().get(i).getProduto());
			 * System.out.println(orcamento.getItens().get(i).getQuantidade());
			 * System.out.println(orcamento.getItens().get(i).getPrecoCusto());
			 * System.out.println(orcamento.getItens().get(i).getPrecoVenda());
			 * }
			 */

			WsUtil.finalizarPeloRest(orcamento);

			this.setItemOrcamento(new ItemOrcamento());
			this.setOrcamento(new Orcamento());
			comboClientes.setItems(null);
			this.preencherComboBoxCliente();
			tabelaItensOrcamento.setItems(null);
			orcamento.setTotal(new BigDecimal("0.00"));
			labelTotal.setText(orcamento.getTotal().toString());
			tabelaProdutos.setItems(null);

		} else {
			Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
			dialogoAviso.setTitle("Atenção");
			dialogoAviso.setHeaderText("Atenção");
			dialogoAviso.setContentText("Senha inválida!");
			dialogoAviso.showAndWait();
		}
	}

	@FXML
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.F10) {
			this.finalizar();
		}
	}

	public List<ItemOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}

	public void setItensOrcamento(List<ItemOrcamento> itensOrcamento) {
		this.itensOrcamento = itensOrcamento;
	}

	public ItemOrcamento getItemOrcamento() {
		return itemOrcamento;
	}

	public void setItemOrcamento(ItemOrcamento itemOrcamento) {
		this.itemOrcamento = itemOrcamento;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

}
