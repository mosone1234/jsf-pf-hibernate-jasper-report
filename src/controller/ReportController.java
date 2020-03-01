package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "reportController")
@RequestScoped
public class ReportController {
	
	public ReportController() {
		// TODO Auto-generated constructor stub
	}
	
	public String jaspertRport() {
		return "This is report";
	}

}
