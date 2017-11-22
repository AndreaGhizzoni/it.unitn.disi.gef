package it.disi.unitn.gef.model;

public class Employe extends Node {

	private String prenom;
	
	public static final String PROPERTY_FIRSTNAME = "EmployePrenom";

	public void setPrenom(String prenom) {
		String oldPrenom = this.prenom;
		this.prenom = prenom;
		getListeners().firePropertyChange(PROPERTY_FIRSTNAME, oldPrenom, prenom);
	}

	public String getPrenom() {
		return this.prenom;
	}
}