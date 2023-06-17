package controller;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GeraRelatorio {

	public GeraRelatorio() {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		File file  = new File("GeraRelatorio.java");
		String pathAbsoluto = file.getAbsolutePath();
		//E:\Documentos\Wellinson\Projetos\YouTube\workspace\Clientes\src\controller\GeraRelatorio.java
		String pathAbsolutoParcial = pathAbsoluto.substring(0,pathAbsoluto.lastIndexOf('\\'))+"\\relatorios\\Coffee.jrxml";
		
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(pathAbsolutoParcial);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(),connection);
			
			JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
			
			jasperViewer.setVisible(true);
			
			
			Conexao.getInstancia().fecharConexao();
			
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
