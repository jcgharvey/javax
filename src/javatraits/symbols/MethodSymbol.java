package javatraits.symbols;

import japa.parser.ast.type.Type;

public class MethodSymbol extends BasicSymbol {

	public MethodSymbol(String name, Type type, int modifiers) {
		super(name, type, modifiers);
	}

}
