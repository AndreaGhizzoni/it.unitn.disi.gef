package it.disi.unitn.gef.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class ServiceFigure extends Figure {
	
	private Label labelName = new Label();
	private Label labelEtage = new Label();

	public ServiceFigure() {
		setLayoutManager( new XYLayout() );
		setForegroundColor(ColorConstants.black);
		
		setBorder(new LineBorder(1));
		setOpaque(true);

		labelName.setForegroundColor(ColorConstants.darkGray);
		add(labelName, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelName, new Rectangle(5, 17, -1, -1));

		labelEtage.setForegroundColor(ColorConstants.black);
		add(labelEtage, ToolbarLayout.ALIGN_CENTER);
		setConstraint(labelEtage, new Rectangle(5, 5, -1, -1));
	}

	public void setName(String text) {
		labelName.setText(text);
	}

	public void setEtage(int etage) {
		labelEtage.setText("Etage:" + etage);
	}

	public void setLayout(Rectangle rect) {
		getParent().setConstraint(this, rect);
	}
}