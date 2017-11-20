package it.disi.unitn.gef.commands;

import org.eclipse.gef.commands.Command;

import it.disi.unitn.gef.model.Node;

public class DeleteCommand extends Command {

	private Node model;
	private Node parentModel;

	@Override
	public void execute() {
		this.parentModel.removeChild(model);
	}

	public void setModel(Object model) {
		this.model = (Node) model;
	}

	public void setParentModel(Object model) {
		parentModel = (Node) model;
	}

	@Override
	public void undo() {
		this.parentModel.addChild(model);
	}
}
