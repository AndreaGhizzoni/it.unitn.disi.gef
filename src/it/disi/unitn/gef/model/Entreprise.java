package it.disi.unitn.gef.model;

public class Entreprise extends Node {

	private String address;
	private int capital;

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public String getAddress() {
		return this.address;
	}

	public int getCapital() {
		return this.capital;
	}
}