package it.disi.unitn.gef.editpart.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import it.disi.unitn.gef.model.Employe;
import it.disi.unitn.gef.model.Entreprise;
import it.disi.unitn.gef.model.Service;

public class AppTreeEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof Entreprise)
			part = new EntrepriseTreeEditPart();
		else if (model instanceof Service)
			part = new ServiceTreeEditPart();
		else if (model instanceof Employe)
			part = new EmployeTreeEditPart();
		if (part != null)
			part.setModel(model);
		return part;
	}
}
