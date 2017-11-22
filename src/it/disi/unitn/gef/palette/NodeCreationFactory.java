package it.disi.unitn.gef.palette;

import org.eclipse.gef.requests.CreationFactory;

import it.disi.unitn.gef.model.Service;

public class NodeCreationFactory implements CreationFactory {
	
	private Class<?> template;
	
	public NodeCreationFactory( Class<?> template ) {
		this.template = template;
	}

	@Override
	public Object getNewObject() {
		if( this.template == null )
			return null;
		if( this.template == Service.class ){
			Service s = new Service();
			s.setName("NewService");
			s.setEtage(42);
			return s;
		}
		return null;
	}

	@Override
	public Object getObjectType() {
		return this.template;
	}
}
