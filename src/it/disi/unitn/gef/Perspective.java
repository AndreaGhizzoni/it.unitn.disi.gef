package it.disi.unitn.gef;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.addStandaloneView( IPageLayout.ID_OUTLINE, true, IPageLayout.LEFT, 0.3f, editorArea );
	}
}
