package it.disi.unitn.gef.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;
import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import it.disi.unitn.gef.editpolicies.AppDeletePolicy;
import it.disi.unitn.gef.model.Employe;
import it.disi.unitn.gef.model.Node;

public class EmployeTreeEditPart extends AppAbstractTreeEditPart {

	protected List<Node> getModelChildren() {
		return ((Employe) getModel()).getChildrenArray();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	public void refreshVisuals(){
		Employe model = (Employe)getModel();
		setWidgetText(model.getName()+" "+model.getPrenom());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW));
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
	}
}
