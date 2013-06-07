package javatraits.symbols;

import javatraits.types.Type;

public class Symbol {
	private String name;
	private Type type;
	private int modifiers;

	public Symbol(String name, Type type, int modifiers) {
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
