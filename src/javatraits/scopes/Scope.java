package javatraits.scopes;

import javatraits.symbols.BasicSymbol;
import javatraits.symbols.SymbolNotFoundException;

public interface Scope {
	public Scope getEnclosingScope();
	
	public BasicSymbol getSymbol(String name) throws SymbolNotFoundException;
	
	public boolean addSymbol(BasicSymbol symbol);
	
	public boolean symbolExists(String name);
}
