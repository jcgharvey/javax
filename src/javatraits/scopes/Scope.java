package javatraits.scopes;

import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public interface Scope {
	public Scope getEnclosingScope();
	
	public Symbol getSymbol(String name) throws SymbolNotFoundException;
	
	public boolean addSymbol(Symbol symbol);
	
	public boolean symbolExists(String name);
}
