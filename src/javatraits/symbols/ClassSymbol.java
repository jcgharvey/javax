package javatraits.symbols;

import javatraits.scopes.ClassScope;

public class ClassSymbol extends BasicSymbol {

	private ClassScope scope;

	public ClassSymbol(String name, int modifiers) {
		super(name, null, modifiers);
	}
	
	public ClassSymbol(String name, int modifiers, ClassScope scope) {
		super(name, null, modifiers);
		this.scope = scope;
	}

	public ClassScope getScope(){
		return scope;
	}

}
