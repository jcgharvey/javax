package javatraits.symbols;

import japa.parser.ast.type.Type;

public class BasicSymbol implements Symbol{
	protected String name;
	protected Type type;
	protected int modifiers;

	public BasicSymbol(String name, Type type, int modifiers) {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}

	public String getName() {
		return this.name;
	} 
	
	public Type getType() {
		return this.type;
	}
	
	public int getModifiers(){
		return this.modifiers;
	}
}
