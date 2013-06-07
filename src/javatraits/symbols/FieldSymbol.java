package javatraits.symbols;

import japa.parser.ast.type.Type;

public class FieldSymbol extends BasicSymbol {
	public FieldSymbol(String name, Type type, int modifiers) {
		super(name, type, modifiers);
	}
}
