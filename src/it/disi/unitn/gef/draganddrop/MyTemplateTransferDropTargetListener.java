package it.disi.unitn.gef.draganddrop;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import it.disi.unitn.gef.palette.NodeCreationFactory;

public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	protected CreationFactory getFactory(Object template) {
		return new NodeCreationFactory(template.getClass());
	}
}