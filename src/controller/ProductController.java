package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import jdk.nashorn.internal.runtime.Undefined;
import entities.Products;

@ManagedBean(name = "productController")
@RequestScoped
public class ProductController {
	
	private example1 exam = new example1();
	private exampleOne examo = new exampleOne();
	private model.ProductModel pror = new model.ProductModel();
	private entities.Products pro = new entities.Products();
	
	public List<Products> findProducts() {
		pror.openCurrentSession();
        List<Products> prox = pror.findAll();
        pror.closeCurrentSession();
		return prox;
	}
	
	public String createProduct() {
	    pror.openCurrentSessionwithTransaction();
	    pror.persist(this.pro);
	    this.pro = new Products();
	    pror.closeCurrentSessionwithTransaction();
		return "index";
	}
	
	public void delete(Products p) {
		pror.openCurrentSessionwithTransaction();
	    Products book = pror.findById(p.getId());
	    pror.delete(book);
	    pror.closeCurrentSessionwithTransaction();
	}
	
	public String redirectToEdit(Products p) {
		this.pro = p;
        return "modidy";
	}
	
	public String updateProduct() {
		pror.openCurrentSessionwithTransaction();
        pror.update(this.pro);
        pror.closeCurrentSessionwithTransaction();
        return "index";
	}
	
	public void exportPDF() throws JRException, IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("textUser", "User");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report2.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(this.findProducts()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReport.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public example1 getExam() {
		return exam;
	}

	public void setExam(example1 car) {
		this.exam = car;
	}
	
	public Products getPro() {
		return pro;
	}

	public void setPro(Products pro) {
		this.pro = pro;
	}
	
}
