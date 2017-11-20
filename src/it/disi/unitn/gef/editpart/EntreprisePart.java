package it.disi.unitn.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import it.disi.unitn.gef.editpolicies.AppEditLayoutPolicy;
import it.disi.unitn.gef.figure.EntrepriseFigure;
import it.disi.unitn.gef.model.Entreprise;
import it.disi.unitn.gef.model.Node;

public class EntreprisePart extends AppAbstractEditPart{

	@Override
	protected IFigure createFigure() {
		IFigure figure = new EntrepriseFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
	}

	protected void refreshVisuals() {
		EntrepriseFigure figure = (EntrepriseFigure) getFigure();
		Entreprise model = (Entreprise) getModel();
		figure.setName(model.getName());
		figure.setAddress(model.getAddress());
		figure.setCapital(model.getCapital());
	}

	public List<Node> getModelChildren() {
		return ((Entreprise)getModel()).getChildrenArray();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
	}
}