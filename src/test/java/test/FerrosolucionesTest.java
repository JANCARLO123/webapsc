package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ferro.interfaces.ApscServiceInterf;
import ferro.model.ApscPedido;
import ferro.services.UserDetailsServicesImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class FerrosolucionesTest {

	@Autowired
	ApscServiceInterf apscService;
	
	@Autowired
	UserDetailsServicesImpl userDetailsServicesImpl;
	///SmsTestSend send = new SmsTestSend();
	
	//private ParamMB parammb;
	//private FacesMessages facesMessages;
	
	// ParamMB param= new ParamMB();
	@Before
	public void setup(){
		//parammb=mock(ParamMB.class);
		//parammb.init();
		//parammb.refreshList();
	}
	
	public void testJasper(){
		
	}
	
	
	@PreAuthorize("authenticated")
	public String getMessage() {
		Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
		return "Hello " + authentication;
	}
	
	@Test
	public void testFerro() {
		
	    //ApscPedido apscPedido=(ApscPedido) apscService.getApscPedidoByID(102);
	    //List<ApscPedido> list=new ArrayList<ApscPedido>();
	    //list.add(apscPedido);
		
		 ApscPedido apscPedido=new  ApscPedido();
		 
		 apscPedido.setEstado(false);
		 
		 apscService.insertPedido(apscPedido);
		
	   /* String sourcereport="src/main/resources/OrdenCompraJRXTest.jrxml";
	
		JRBeanCollectionDataSource pedidoDS=new JRBeanCollectionDataSource(list);
		//pedido detalles
		
		//
		System.err.println(apscPedido.getApscPedidoDets().size());
		JRBeanCollectionDataSource pedidoDetDS=new JRBeanCollectionDataSource(apscPedido.getApscPedidoDets());
		
		
		//System.err.println("jasper 1 :"+collectionDataSource.toString() );
		Map param=new HashMap();
		Map<String,Object> datosReport=new HashMap<String, Object>();
		//Map<String,Object> datosReportSubReport=new HashMap<String, Object>();
		
		try {
			//datosReport.put("datosPediDet",pedidoDetDS);
			
			System.err.println("datos sub report "+pedidoDetDS.getRecordCount());
			
			JasperReport report = JasperCompileManager.compileReport(sourcereport);
			
			JasperPrint print	=JasperFillManager.fillReport(report,datosReport,pedidoDS);
			
			JasperExportManager.exportReportToPdfFile(print,"src/main/resources/report7.pdf");
			
			//xls
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("src/main/resources/sample_report.xlsx"));
			
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			//exporter.setConfiguration(configuration);
           

            exporter.exportReport();
			
			// fin xls
			
			//System.err.println("visor :"+jasperViewer.getName());
		} catch (JRException e) {
			
			e.printStackTrace();
		}*/
		
	//	Map expectedResults=new Map()<String, Object> ;
		
		/*try{
		JasperPrint print	=JasperFillManager.fillReport(sourcereport, param,collectionDataSource);
		 //List<JRPrintPage> pages = print.getPages(); 
		 System.err.println("datos jarper : "+print.toString());
		 
		
		 //pages.get(0).
		
		 }catch(JRException e){
			e.getMessage();
		}*/
		
		
		/*System.err.println("tamaño de lista : "+apscService.getApscPedidoByID(35).get(0).getApscPedidoDets().size());
		
		
		for(ApscPedidoDet obj : apscService.getApscPedidoByID(35).get(0).getApscPedidoDets()){
			System.err.println("datos : "+obj.getCodProd()+" -- "+obj.getCant());
		}*/
		
				

	}// fin de test
}
