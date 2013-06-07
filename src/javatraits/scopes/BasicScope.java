package javatraits.scopes;

import java.util.HashMap;
import java.util.Map;

import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public class BasicScope implements Scope {
	protected Scope enclosingScope;
	protected Map<String, Symbol> symbols;
	
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
}
