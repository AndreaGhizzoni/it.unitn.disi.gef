package it.disi.unitn.gef.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import it.disi.unitn.gef.editpolicies.AppDeletePolicy;
import it.disi.unitn.gef.model.Node;
import it.disi.unitn.gef.model.Service;

public class ServiceTreeEditPart extends AppAbstractTreeEditPart {
	
	@Override
	protected List<Node> getModelChildren() {
		return ((Service)getModel()).getChildrenArray();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy());
	}

	@Override
	public void refreshVisuals(){
		Service model = (Service)getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
	}
}
