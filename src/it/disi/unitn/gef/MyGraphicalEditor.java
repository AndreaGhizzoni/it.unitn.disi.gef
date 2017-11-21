package it.disi.unitn.gef;

import java.util.ArrayList;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import it.disi.unitn.gef.editpart.AppEditPartFactory;
import it.disi.unitn.gef.editpart.tree.AppTreeEditPartFactory;
import it.disi.unitn.gef.model.Employe;
import it.disi.unitn.gef.model.Entreprise;
import it.disi.unitn.gef.model.Service;

public class MyGraphicalEditor extends GraphicalEditor {

	public static final String ID = "gef.mygraphicaleditor";
	private Entreprise model;
	private KeyHandler keyHandler;

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}
	
	public Entreprise CreateEntreprise(){
		Entreprise psyEntreprise = new Entreprise();
		psyEntreprise.setName("Psykokwak Entreprise");
		psyEntreprise.setAddress("Quelque part sur terre");
		psyEntreprise.setCapital(100000);
		
		Service comptaService = new Service();
		comptaService.setName("Compta");
		comptaService.setEtage(2);
		comptaService.setLayout(new Rectangle(30, 50, 250, 150));
		
		Employe employeCat = new Employe();
		employeCat.setName("Debroua");
		employeCat.setPrenom("Cat");
		employeCat.setLayout(new Rectangle(25, 40, 60, 40));
		comptaService.addChild(employeCat);

		Employe employeJyce = new Employe();
		employeJyce.setName("Psykokwak");
		employeJyce.setPrenom("Jyce");
		employeJyce.setLayout(new Rectangle(100, 60, 60, 40));
		comptaService.addChild(employeJyce);
		
		Employe employeEva = new Employe();
		employeEva.setName("Longoria");
		employeEva.setPrenom("Eva");
		employeEva.setLayout(new Rectangle(180, 90, 60, 40));
		comptaService.addChild(employeEva);

		psyEntreprise.addChild(comptaService);
		
		Service rhService = new Service();
		rhService.setName("Ressources Humaine");
		rhService.setEtage(1);
		rhService.setLayout(new Rectangle(220, 230, 250, 150));

		Employe employePaul = new Employe();
		employePaul.setName("Dupond");
		employePaul.setPrenom("Paul");
		employePaul.setLayout(new Rectangle(40, 70, 60, 40));
		rhService.addChild(employePaul);

		Employe employeEric = new Employe();
		employeEric.setName("Durand");
		employeEric.setPrenom("Eric");
		employeEric.setLayout(new Rectangle(170, 100, 60, 40));
		rhService.addChild(employeEric);

		psyEntreprise.addChild(rhService);

		return psyEntreprise;
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());
		
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);

		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		double[] zoomLevels = new double[] {0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0};
		manager.setZoomLevels(zoomLevels);
		ArrayList<String> zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);
		
		keyHandler = new KeyHandler();
		keyHandler.put(
			KeyStroke.getPressed(SWT.DEL, 127, 0),
			getActionRegistry().getAction(ActionFactory.DELETE.getId())
		);
		keyHandler.put(
			KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
			getActionRegistry().getAction(GEFActionConstants.ZOOM_IN)
		);
		keyHandler.put( 
			KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
			getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT)
		);
		viewer.setProperty( 
			MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
			MouseWheelZoomHandler.SINGLETON
		);
		viewer.setKeyHandler(keyHandler);
		
		ContextMenuProvider menuProvider = new AppContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu( menuProvider );
	}

	@Override
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		model = CreateEntreprise();
		viewer.setContents( model );
	}
	
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		if (type == ZoomManager.class)
			return ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager();
		if (type == IContentOutlinePage.class)
			return new OutlinePage();
		else
			return super.getAdapter(type);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
	}
	
	protected class OutlinePage extends ContentOutlinePage {
		private SashForm sash;
		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public OutlinePage() { super(new TreeViewer()); }

		public OutlinePage(EditPartViewer viewer) { super(viewer); }
		
		@Override
		public void createControl(Composite parent) {
			sash = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sash);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);
			getSelectionSynchronizer().addViewer(getViewer());
			
			// Creation of miniature.
			LightweightSystem lws = new LightweightSystem( new Canvas(sash, SWT.BORDER) );
			thumbnail = new ScrollableThumbnail(
				(Viewport) ((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getFigure()
			);
			thumbnail.setSource(
				((ScalableRootEditPart) getGraphicalViewer().getRootEditPart()).getLayer(LayerConstants.PRINTABLE_LAYERS)
			);
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) { 
						thumbnail.deactivate(); 
						thumbnail = null; 
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(disposeListener);
		}

		@Override
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(
				ActionFactory.UNDO.getId(),
				getActionRegistry().getAction(ActionFactory.UNDO.getId())
			);
			bars.setGlobalActionHandler(
				ActionFactory.REDO.getId(),
				getActionRegistry().getAction(ActionFactory.REDO.getId())
			);
			bars.setGlobalActionHandler(
				ActionFactory.DELETE.getId(),
				getActionRegistry().getAction(ActionFactory.DELETE.getId())
			);
			bars.updateActionBars();
			getViewer().setKeyHandler(keyHandler);
			
			ContextMenuProvider menuProvider = new AppContextMenuProvider(getViewer(), getActionRegistry());
			getViewer().setContextMenu( menuProvider );
		}

		@Override
		public Control getControl() { return sash; }
		
		@Override
		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null && !getGraphicalViewer().getControl().isDisposed())
				getGraphicalViewer().getControl().removeDisposeListener(disposeListener);
			super.dispose();
		}
	}
}
