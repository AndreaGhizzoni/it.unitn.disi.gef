package it.disi.unitn.gef.actions;

import java.util.HashMap;
import java.util.List;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import it.disi.unitn.gef.model.Node;
import it.disi.unitn.gef.wizard.RenameWizard;

public class RenameAction extends SelectionAction {
	
	public RenameAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}
	
	@Override
	protected void init() {
		setText("Rename...");
		setToolTipText("Rename");
		setId(ActionFactory.RENAME.getId());
//		ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("it.disi.unitn.gef", "icons/rename-icon.png");
//		if (icon != null) setImageDescriptor(icon);
		setEnabled(false);
	}
	
	protected boolean calculateEnabled() {
		return createRenameCommand("") != null;
	}
	
	public Command createRenameCommand(String name) {
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name);
		Request renameReq = new Request("rename");
		renameReq.setExtendedData(reqData);
		List selectedObjects= getSelectedObjects();
		if( selectedObjects.isEmpty() ) return null;
		EditPart selectedEditPart = (EditPart)selectedObjects.get(0);
		return selectedEditPart.getCommand(renameReq);
	}
	
	@Override
	public void run() {
		Node node = getSelectedNode();
		RenameWizard wizard = new RenameWizard(node.getName());
		WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(), wizard);
		dialog.create();
		dialog.getShell().setSize(400, 180);
		dialog.setTitle("Rename wizard");
		dialog.setMessage("Rename");
		if (dialog.open() == WizardDialog.OK) {
			String name = wizard.getRenamedValue();
			execute( createRenameCommand(name) );
		}
	}
	
	private Node getSelectedNode() {
		@SuppressWarnings("rawtypes")
		List objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		EditPart part = (EditPart)objects.get(0);
		return (Node)part.getModel();
	}
}
