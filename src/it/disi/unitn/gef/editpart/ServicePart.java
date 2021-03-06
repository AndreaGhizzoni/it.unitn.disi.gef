package it.disi.unitn.gef.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import it.disi.unitn.gef.editpolicies.AppDeletePolicy;
import it.disi.unitn.gef.editpolicies.AppEditLayoutPolicy;
import it.disi.unitn.gef.editpolicies.AppRenamePolicy;
import it.disi.unitn.gef.figure.ServiceFigure;
import it.disi.unitn.gef.model.Node;
import it.disi.unitn.gef.model.Service;

public class ServicePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		IFigure figure = new ServiceFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy() );
	}

	@Override
	protected void refreshVisuals() {
		ServiceFigure figure = (ServiceFigure) getFigure();
		Service model = (Service) getModel();
		figure.setName(model.getName());
		figure.setEtage(model.getEtage());
		figure.setLayout(model.getLayout());
		figure.setBackgroundColor(model.getColor());
	}

	@Override
	public List<Node> getModelChildren() {
		return ((Service) getModel()).getChildrenArray();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals();
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();
		if (evt.getPropertyName().equals(Service.PROPERTY_COLOR)) refreshVisuals();
		if (evt.getPropertyName().equals(Service.PROPERTY_FLOOR)) refreshVisuals();
	}
}