package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DanfeUtil {

	/**
	 * @param args the command line arguments Fonte:
	 *             http://www.javac.com.br/jc/posts/list/2471.page
	 */
	@SuppressWarnings({ "unchecked" }) // VERIFICAR.
	public static void imprimirOrcamento(String Xml) throws FileNotFoundException, IOException {
		String stringComEstruturaDoXML = Xml;
		try {

			InputStream stream = new ByteArrayInputStream(stringComEstruturaDoXML.getBytes("utf-8"));

			JRXmlDataSource xml = new JRXmlDataSource(stream, "/");

			@SuppressWarnings("rawtypes")
			HashMap mapa = new HashMap();

			JasperReport jasperReport = null;
			InputStream jasperFile = Thread.currentThread().getClass()
					.getResourceAsStream("/report/compVenda.jasper");
			jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);

			JasperPrint jp = JasperFillManager.fillReport(jasperReport, mapa, xml);

			// exportando arquivo para formulario java.
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setTitle("ORCAMENTO");
			// jv.setIconImage(imagemTituloJanela.getImage());
			jv.setVisible(true);

		} catch (JRException e) {

			Util.showExceptionDialog(e);

			e.printStackTrace();
		} catch (Exception e) {

			Util.showExceptionDialog(e);

			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private static byte[] getBytes(JRTextExporter exporter, ByteArrayOutputStream baos, JasperPrint jp) {
		// TODO Auto-generated method stub
		return null;
	}

}
