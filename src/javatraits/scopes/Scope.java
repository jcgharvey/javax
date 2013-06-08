package javatraits.scopes;

import japa.parser.ast.type.Type;

import java.util.List;

import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public interface Scope {
	public enum ScopeType {
		Global, Local, Class, Method, Default
	}
	
	public Scope getEnclosingScope();
	
	public Symbol getSymbol(String name) throws SymbolNotFoundException;
	
	public void addSymbol(Symbol symbol);
	
	public boolean symbolExists(String name);
	
	public ScopeType getScopeType();
	
	public Symbol resolveType(String name);
}
