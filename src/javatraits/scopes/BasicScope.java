package javatraits.scopes;

import japa.parser.ast.type.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javatraits.CompilationException;
import javatraits.symbols.BuiltInTypeSymbol;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public class BasicScope implements Scope {
	protected Scope enclosingScope;
	protected Map<String, Symbol> symbols;
	protected ScopeType scope;
	
	public BasicScope(Scope enclosingScope){
		this.enclosingScope = enclosingScope;
		symbols = new HashMap<String, Symbol>();
	}
	
	@Override
	public Scope getEnclosingScope(){
		return enclosingScope;
	}
	
	@Override
	public Symbol getSymbol(String name) throws SymbolNotFoundException {
		if (symbols.containsKey(name))
			return symbols.get(name);
		throw new SymbolNotFoundException();
	}
	
	@Override
	public boolean addSymbol(Symbol symbol){
		String name = symbol.getName();
		if (symbols.containsKey(name)){
			return false;
		}
		symbols.put(name, symbol);
		return true;
	}

	@Override
	public boolean symbolExists(String name) {
		return symbols.containsKey(name);
	}
	
	@Override
	public ScopeType getScopeType() {
		return scope;
	}
	
	@Override
	public Symbol resolveType(String name) {
		if(symbols.keySet().contains(name)){
			Symbol symbol = symbols.get(name);
			if (symbol.getClass().equals(BuiltInTypeSymbol.class) || symbol.getClass().equals(ClassSymbol.class)){
				return symbol;
			}
		}
		if (enclosingScope != null){
			return enclosingScope.resolveType(name);
		}
		throw new CompilationException();
	}
}
