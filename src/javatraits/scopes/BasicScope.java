package javatraits.scopes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javatraits.CompilationException;
import javatraits.symbols.BuiltInTypeSymbol;
import javatraits.symbols.ClassSymbol;
import javatraits.symbols.ConstructorSymbol;
import javatraits.symbols.MethodSymbol;
import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public class BasicScope implements Scope {
	protected Scope enclosingScope;
	protected Map<String, Symbol> symbols;
	protected ScopeType scope;

	public BasicScope(Scope enclosingScope) {
		this.enclosingScope = enclosingScope;
		symbols = new HashMap<String, Symbol>();
	}

	@Override
	public Scope getEnclosingScope() {
		return enclosingScope;
	}

	@Override
	public Symbol getSymbol(String name) throws SymbolNotFoundException {
		if (symbols.containsKey(name))
			return symbols.get(name);
		throw new SymbolNotFoundException();
	}

	@Override
	public void addSymbol(Symbol symbol) {
		String name = symbol.getName();
//		if (symbol.getClass().equals(MethodSymbol.class)) {
//			List<Symbol> methodSymbols = getSymbolsOfClass(MethodSymbol.class);
//			for(Symbol methodSymbol : methodSymbols){
//				if (methodSymbol.equals(symbol)){
//					throw new CompilationException();
//				}
//			}
//		} else if (symbol.getClass().equals(ConstructorSymbol.class)) {
//			List<Symbol> constructorSymbols = getSymbolsOfClass(ConstructorSymbol.class);
//			for(Symbol constructorSymbol : constructorSymbols){
//				if (constructorSymbol.equals(symbol)){
//					throw new CompilationException();
//				}
//			}
//		}
		if (symbols.containsKey(name)) {
			throw new CompilationException();
		}
		symbols.put(name, symbol);
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
		if (symbols.keySet().contains(name)) {
			Symbol symbol = symbols.get(name);
			if (symbol.getClass().equals(BuiltInTypeSymbol.class)
					|| symbol.getClass().equals(ClassSymbol.class)) {
				return symbol;
			}
		}
		if (enclosingScope != null) {
			return enclosingScope.resolveType(name);
		}
		throw new CompilationException();
	}

	private List<Symbol> getSymbolsOfClass(Class<? extends Symbol> c) {
		List<Symbol> matchedSymbols = new ArrayList<Symbol>();
		Iterator<Entry<String, Symbol>> it = symbols.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Symbol> pairs = it.next();
			if(pairs.getValue().getClass().equals(c)){
				matchedSymbols.add(pairs.getValue());
			}
			it.remove(); // avoids a ConcurrentModificationException
		}
		return matchedSymbols;
	}
}
