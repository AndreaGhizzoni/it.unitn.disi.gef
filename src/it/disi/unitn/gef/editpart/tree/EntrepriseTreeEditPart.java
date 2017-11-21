package it.disi.unitn.gef.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import it.disi.unitn.gef.model.Entreprise;
import it.disi.unitn.gef.model.Node;

public class EntrepriseTreeEditPart extends AppAbstractTreeEditPart {

	@Override
	protected List<Node> getModelChildren() {
		return ((Entreprise)getModel()).getChildrenArray();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();
		if(evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();
	}
}
