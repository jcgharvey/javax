package javatraits.symbols;

import japa.parser.ast.body.Parameter;

import java.util.List;

public class ConstructorSymbol extends ParameterizedSymbol {

	public ConstructorSymbol(String name, int modifiers, List<Parameter> parameters) {
		super(name, null, modifiers, parameters);
	}

}
