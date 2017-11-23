package it.disi.unitn.gef.commands.copyandpaste;

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;
import it.disi.unitn.gef.model.Employe;
import it.disi.unitn.gef.model.Node;
import it.disi.unitn.gef.model.Service;

public class CopyNodeCommand extends Command {

	private ArrayList<Node> list = new ArrayList<Node>();

	public boolean addElement(Node node) {
		if (!list.contains(node)) {
			return list.add(node);
		}
		return false;
	}

	@Override
	public boolean canExecute() {
		if (list == null || list.isEmpty())
			return false;
		Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			if (!isCopyableNode(it.next()))
				return false;
		}
		return true;
	}

	public boolean isCopyableNode(Node node) {
		if (node instanceof Service || node instanceof Employe)
			return true;
		return false;
	}

	@Override
	public void execute() {
		if (canExecute())
			Clipboard.getDefault().setContents(list);
	}

	@Override
	public boolean canUndo() {
		return false;
	}
}
