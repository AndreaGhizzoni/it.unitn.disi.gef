package it.disi.unitn.gef.editpolicies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import it.disi.unitn.gef.commands.RenameCommand;

public class AppRenamePolicy extends AbstractEditPolicy {
	
	@Override
	public Command getCommand(Request request) {
		if (request.getType().equals("rename")){
			RenameCommand command = new RenameCommand();
			command.setModel(getHost().getModel());
			command.setNewName((String)request.getExtendedData().get("newName"));
			return command;
		}
		return null;
	}
}
