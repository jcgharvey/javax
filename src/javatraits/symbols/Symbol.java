package javatraits.symbols;

import javatraits.types.Type;

public interface Symbol {

	public String getName();

	public Type getType();

	public int getModifiers();

}
