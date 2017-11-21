package it.disi.unitn.gef;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class AppContextMenuProvider extends ContextMenuProvider {
	
	private ActionRegistry actionRegistry;

	public AppContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		actionRegistry = registry;
	}

	public AppContextMenuProvider(EditPartViewer viewer) {
		super(viewer);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		GEFActionConstants.addStandardActionGroups(menu);
		menu.appendToGroup(
			GEFActionConstants.GROUP_UNDO, 
			actionRegistry.getAction(ActionFactory.UNDO.getId())
		);
		menu.appendToGroup(
			GEFActionConstants.GROUP_UNDO, 
		    actionRegistry.getAction(ActionFactory.REDO.getId())
		);
		menu.appendToGroup(
			GEFActionConstants.GROUP_EDIT, 
			actionRegistry.getAction(ActionFactory.DELETE.getId())
		);
	}
}
