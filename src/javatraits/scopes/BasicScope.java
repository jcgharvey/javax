package javatraits.scopes;

import java.util.HashMap;
import java.util.Map;

import javatraits.symbols.BasicSymbol;
import javatraits.symbols.SymbolNotFoundException;

public class BasicScope implements Scope {
	protected Scope enclosingScope;
	protected Map<String, BasicSymbol> symbols;
	
	public BasicScope(Scope enclosingScope){
		this.enclosingScope = enclosingScope;
		symbols = new HashMap<String, BasicSymbol>();
	}
	
	@Override
	public Scope getEnclosingScope(){
		return enclosingScope;
	}
	
	@Override
	public BasicSymbol getSymbol(String name) throws SymbolNotFoundException {
		if (symbols.containsKey(name))
			return symbols.get(name);
		throw new SymbolNotFoundException();
	}
	
	@Override
	public boolean addSymbol(BasicSymbol symbol){
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
