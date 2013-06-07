package javatraits.symbols;

import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.util.List;

public class ParameterizedSymbol extends BasicSymbol {

	protected List<Parameter> parameters;
	
	public ParameterizedSymbol(String name, Type type, int modifiers) {
		super(name, type, modifiers);
	}
	
	public ParameterizedSymbol(String name, Type type, int modifiers, List<Parameter> parameters) {
		this(name, type, modifiers);
		this.parameters = parameters;
	}
	
	public List<Parameter> getParameters(){
		return parameters;
	}
	
}
