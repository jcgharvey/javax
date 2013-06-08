package javatraits.symbols;

import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.util.List;

public class ParameterizedSymbol extends BasicSymbol {

	protected List<Parameter> parameters;

	public ParameterizedSymbol(String name, Type type, int modifiers) {
		super(name, type, modifiers);
	}

	public ParameterizedSymbol(String name, Type type, int modifiers,
			List<Parameter> parameters) {
		this(name, type, modifiers);
		this.parameters = parameters;
		this.name = generateSigniture(name);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	private String generateSigniture(String name) {
		if (parameters == null) {
			return name;
		}
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
