package it.disi.unitn.gef.commands.layout;

import org.eclipse.draw2d.geometry.Rectangle;
import it.disi.unitn.gef.model.Service;

public class ServiceChangeLayoutCommand extends AbstractLayoutCommand {

	private Service model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (Service) model;
		this.oldLayout = ((Service) model).getLayout();
	}
	
	@Override
	public void undo(){
		this.model.setLayout(this.oldLayout);
	}
}