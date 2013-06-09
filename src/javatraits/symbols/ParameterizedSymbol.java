package javatraits.symbols;

import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.util.ArrayList;
import java.util.List;

public class ParameterizedSymbol extends BasicSymbol {

	protected List<Parameter> parameters;

	public ParameterizedSymbol(String name, Type type, int modifiers) {
		super(name, type, modifiers);
	}

	public ParameterizedSymbol(String name, Type type, int modifiers,
			List<Parameter> parameters) {
		this(generateSigniture(name,parameters), type, modifiers);
		this.parameters = parameters;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	private static String generateSigniture(String name, List<Parameter> parameters) {
		parameters = parameters == null ? new ArrayList<Parameter>() : parameters;
		StringBuilder sb = new StringBuilder(name);
		sb.append('(');
		for (int i = 0; i < parameters.size() - 1; i++) {
			sb.append(parameters.get(i).getType().toString());
			sb.append(',');
		}
		if(parameters.size() != 0){
			sb.append(parameters.get(parameters.size() - 1).getType().toString());
		}
		sb.append(')');
		return sb.toString();
	}

}
