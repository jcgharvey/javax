package javatraits.symbols;

import japa.parser.ast.type.Type;

public class VariableSymbol extends BasicSymbol {
	public VariableSymbol(String name, Type type, int modifiers) {
		super(name, type, modifiers);
	}
}