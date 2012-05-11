package be.faros.vaadin.event;

public class VaadinEvent {

	public enum Type {
		ADDPERSON, SELECTPERSON, DOUBLECLICKPERSON
	};

	private Object data;

	public VaadinEvent(Type type, Object data) {
		super();
		this.type = type;
		this.data = data;
	}

	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
