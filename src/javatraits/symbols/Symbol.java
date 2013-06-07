package javatraits.symbols;

import japa.parser.ast.type.Type;

public interface Symbol {

	public String getName();

	public Type getType();

	public int getModifiers();

}
