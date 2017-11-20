package it.disi.unitn.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;

import it.disi.unitn.gef.model.Employe;

public class EmployeChangeLayoutCommand extends AbstractLayoutCommand {

	private Employe model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	@Override
	public void setConstraint(Rectangle rect) {
		this.layout = rect;

	}

	@Override
	public void setModel(Object model) {
		this.model = (Employe)model;
		this.oldLayout = ((Employe)model).getLayout();
	}
	
	@Override
	public void undo(){
		this.model.setLayout(this.oldLayout);		
	}
}
