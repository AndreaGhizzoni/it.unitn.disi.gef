package it.disi.unitn.gef.commands;

import org.eclipse.gef.commands.Command;

import it.disi.unitn.gef.model.Node;

public class RenameCommand extends Command {
	
	private Node model;
	private String oldName;
	private String newName;
	
	@Override
	public void execute() {
		this.oldName = model.getName();
		this.model.setName(newName);
	}

	public void setModel(Object model) {
		this.model = (Node)model;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Override
	public void undo() {
		this.model.setName(oldName);
	}
}
