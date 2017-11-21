package it.disi.unitn.gef.actions;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

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
		ImageDescriptor icon = AbstractUIPlugin.imageDescriptorFromPlugin("it.disi.unitn.gef", "icons/rename-icon.png");
		if (icon != null) setImageDescriptor(icon);
		setEnabled(false);
	}
	
	@Override
	protected boolean calculateEnabled() {
		Command cmd = createRenameCommand("");
		if (cmd == null)
			return false;
		return true;
	}
	
	public Command createRenameCommand(String name) {
		Request renameReq = new Request("rename");
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newName", name);
		renameReq.setExtendedData(reqData);
		EditPart object = (EditPart)getSelectedObjects().get(0);
		Command cmd = object.getCommand(renameReq);
		return cmd;
	}
	
	@Override
	public void run() {
		Node node = getSelectedNode();
		RenameWizard wizard = new RenameWizard(node.getName());
		WizardDialog dialog = new WizardDialog(getWorkbenchPart().getSite().getShell(),
		wizard);
		dialog.create();
		dialog.getShell().setSize(400, 180);
		dialog.setTitle("Rename wizard");
		dialog.setMessage("Rename");
		if (dialog.open() == WizardDialog.OK) {
		String name = wizard.getRenameValue();
		execute(createRenameCommand(name));
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
