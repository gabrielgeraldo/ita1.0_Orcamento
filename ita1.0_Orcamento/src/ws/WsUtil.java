package ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.config.Config;
import javafx.scene.control.Alert;
import model.Cliente;
import model.Orcamento;
import model.Produto;
import model.Usuario;
import util.Util;

public class WsUtil {

	final static String URL = Config.propertiesLoader().getProperty("enderecoServidor");

	public static List<Cliente> getClientes() {
		ArrayList<Cliente> lista = null;
		try {
			URL url = new URL("http://" + URL + "rest/cliente/getClientes");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200) {
				System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output, json = "";
			while ((output = br.readLine()) != null) {
				json += output;
			}

			Gson gson = new Gson();
			Type tipoLista = new TypeToken<ArrayList<Cliente>>() {
			}.getType();
			lista = gson.fromJson(json, tipoLista);

			conn.disconnect();

		} catch (java.net.ConnectException e) {
			Util.showExceptionDialog(e);
		} catch (Exception e) {
			e.printStackTrace();
			Util.showExceptionDialog(e);
		}
		return lista;
	}

	public static List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = null;
		try {
			URL url = new URL("http://" + URL + "rest/usuario/getUsuarios");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200) {
				System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output, json = "";
			while ((output = br.readLine()) != null) {
				json += output;
			}

			// System.out.print(json);

			Gson gson = new Gson();
			Type tipoLista = new TypeToken<ArrayList<Usuario>>() {
			}.getType();
			lista = gson.fromJson(json, tipoLista);

			conn.disconnect();

		} catch (java.net.ConnectException e) {
			Util.showExceptionDialog(e);

		} catch (Exception e) {
			e.printStackTrace();
			Util.showExceptionDialog(e);
		}
		return lista;
	}

	public static List<Cliente> completeCliente(String cliente) {

		ArrayList<Cliente> lista = null;

		if (!cliente.isEmpty()) {

			try {
				URL url = new URL("http://" + URL + "rest/orcamento/completeCliente/" + cliente);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				if (conn.getResponseCode() != 200) {
					System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output, json = "";
				while ((output = br.readLine()) != null) {
					json += output;
				}

				Gson gson = new Gson();
				Type tipoLista = new TypeToken<ArrayList<Cliente>>() {
				}.getType();
				lista = gson.fromJson(json, tipoLista);

				conn.disconnect();

			} catch (java.net.ConnectException e) {
				Util.showExceptionDialog(e);

			} catch (Exception e) {
				e.printStackTrace();
				Util.showExceptionDialog(e);
			}

		}
		return lista;
	}

	public static List<Produto> completeProduto(int tipoPesquisaProduto, String produto) {

		ArrayList<Produto> lista = null;

		if (!produto.isEmpty()) {

			try {
				URL url = new URL(
						"http://" + URL + "rest/orcamento/completeProduto/" + tipoPesquisaProduto + "/" + produto);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				if (conn.getResponseCode() != 200) {
					System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output, json = "";
				while ((output = br.readLine()) != null) {
					json += output;
				}

				Gson gson = new Gson();
				Type tipoLista = new TypeToken<ArrayList<Produto>>() {
				}.getType();
				lista = gson.fromJson(json, tipoLista);

				conn.disconnect();

			} catch (java.net.ConnectException e) {
				Util.showExceptionDialog(e);

			} catch (Exception e) {
				e.printStackTrace();
				Util.showExceptionDialog(e);
			}

		}
		return lista;
	}

	public static String finalizarPeloRest(Orcamento orcamento) {

		// --- transformando em JSON --- //
		Gson gson = new Gson(); // conversor
		String jsonOrcamento = gson.toJson(orcamento);

		// exibindo o JSON //
		// System.out.println(jsonOrcamento);

		try {

			URL url = new URL("http://" + URL + "rest/orcamento/finalizarPeloRest");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = jsonOrcamento;
			// System.out.println(input);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			// if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			// throw new RuntimeException("Failed : HTTP error code : " +
			// conn.getResponseCode());
			// }

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// System.out.println(output);

				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
				dialogoInfo.setTitle("Orcamento");
				dialogoInfo.setHeaderText("Mensagem!");
				dialogoInfo.setContentText("Orcamento finalizado!");
				dialogoInfo.showAndWait();

				return output;

			}

			conn.disconnect();

		} catch (java.net.ConnectException e) {
			Util.showExceptionDialog(e);

		} catch (Exception e) {
			e.getMessage();
			Util.showExceptionDialog(e);
		}

		return null;

	}

	public static String getRazaoSocial() {
		String razaoSocial = null;

		try {
			URL url = new URL("http://" + URL + "rest/orcamento/getRazaoSocial");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200) {
				System.out.print("deu erro... HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output, json = "";
			while ((output = br.readLine()) != null) {
				json += output;
			}

			// System.out.println(json);

			razaoSocial = json;

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return razaoSocial;
	}

}
