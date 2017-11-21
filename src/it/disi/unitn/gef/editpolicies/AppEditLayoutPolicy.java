package it.disi.unitn.gef.editpolicies;

import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.EditPart;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.CreateRequest;

import it.disi.unitn.gef.commands.layout.AbstractLayoutCommand;
import it.disi.unitn.gef.commands.layout.EmployeChangeLayoutCommand;
import it.disi.unitn.gef.commands.layout.ServiceChangeLayoutCommand;
import it.disi.unitn.gef.editpart.EmployePart;
import it.disi.unitn.gef.editpart.ServicePart;


public class AppEditLayoutPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof EmployePart) {
			command = new EmployeChangeLayoutCommand();
		} else if (child instanceof ServicePart) {
			command = new ServiceChangeLayoutCommand();
		}
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}