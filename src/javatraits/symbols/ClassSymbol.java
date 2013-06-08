package javatraits.symbols;

import java.util.List;

import javatraits.scopes.ClassScope;

public class ClassSymbol extends BasicSymbol {

	private List<ClassScope> parentScopes;

	public ClassSymbol(String name, int modifiers) {
		super(name, null, modifiers);
	}

	public void addScope(ClassScope s) {
		parentScopes.add(s);
	}

}
