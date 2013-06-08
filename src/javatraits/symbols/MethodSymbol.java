package javatraits.symbols;

import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.util.List;

public class MethodSymbol extends ParameterizedSymbol {

	public MethodSymbol(String name, Type type, int modifiers, List<Parameter> parameters) {
		super(name, type, modifiers, parameters);
	}
	
	

}
