package javatraits.scopes;

import japa.parser.ast.expr.Expression;
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
import javatraits.symbols.ParameterizedSymbol;
import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public class BasicScope implements Scope {
	protected List<Scope> extendsScopes = new ArrayList<Scope>();
	protected Scope parentScope;
	protected Map<String, Symbol> symbols;
	protected ScopeType scopeType;
	protected String name;

	public BasicScope(Scope enclosingScope, String name) {
		parentScope = enclosingScope;
		symbols = new HashMap<String, Symbol>();
		this.name = name;
	}

	@Override
	public List<Scope> getEnclosingScopes() {
		return extendsScopes;
	}

	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public Scope getEnclosingScope() {
		return parentScope;
	}

	@Override
	public void addEnclosingScope(Scope s) {
		extendsScopes.add(s);
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
		// if (symbol.getClass().equals(MethodSymbol.class)) {
		// List<Symbol> methodSymbols = getSymbolsOfClass(MethodSymbol.class);
		// for(Symbol methodSymbol : methodSymbols){
		// if (methodSymbol.equals(symbol)){
		// throw new CompilationException();
		// }
		// }
		// } else if (symbol.getClass().equals(ConstructorSymbol.class)) {
		// List<Symbol> constructorSymbols =
		// getSymbolsOfClass(ConstructorSymbol.class);
		// for(Symbol constructorSymbol : constructorSymbols){
		// if (constructorSymbol.equals(symbol)){
		// throw new CompilationException();
		// }
		// }
		// }
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
		return scopeType;
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
		if (parentScope != null) {
			return parentScope.resolveType(name);
		}
		throw new CompilationException();
	}
	
	@Override
	public Symbol resolveVariable(String name){
		System.out.println("VARSYMS: "+symbols.keySet().toString());
		if (symbols.keySet().contains(name)) {
			return symbols.get(name);
		}
		if (parentScope != null) {
			return parentScope.resolveType(name);
		}
		throw new CompilationException();
	}

	private List<Symbol> getSymbolsOfClass(Class<? extends Symbol> c) {
		List<Symbol> matchedSymbols = new ArrayList<Symbol>();
		Iterator<Entry<String, Symbol>> it = symbols.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Symbol> pairs = it.next();
			if (pairs.getValue().getClass().equals(c)) {
				matchedSymbols.add(pairs.getValue());
			}
			it.remove(); // avoids a ConcurrentModificationException
		}
		return matchedSymbols;
	}

	@Override
	public Type resolveMethod(String name, List<Expression> expressions,
			boolean runningFirst) {
		if (runningFirst) {
			expressions = expressions == null ? new ArrayList<Expression>()
					: expressions;
			name = generateName(name, getTypesOfExpressions(expressions));
		}
		// attempt to find the symbol
		Symbol symbol = symbols.get(name);
		// if we cant find it lets look at the parent or the extends scopes
		if(symbol == null){
			// check the extends scopes first
			for (Scope s : extendsScopes) {
				Type tony = ((BasicScope) s).resolveMethod(name, expressions, false);
				if (tony != null){
					return tony;
				}
			}
			// check the parent scope if not null
			if(parentScope != null){
				return parentScope.resolveMethod(name, expressions, false);
			} else {
				//if parent scope is null then we failed to find the method
				System.out.println("Could not find method " + name);
				throw new CompilationException();
			}
		} else if(symbol.getClass().equals(ParameterizedSymbol.class)){
			return symbol.getType();
		}
		return null;
	}
	
	private String generateName(String name, List<Type> types) {
		StringBuilder sb = new StringBuilder(name);
		sb.append('(');
		for (int i = 0; i < types.size() - 1; i++) {
			sb.append(types.get(i).toString());
			sb.append(',');
		}
		if (types.size() != 0) {
			sb.append(types.get(types.size() - 1).toString());
		}
		sb.append(')');
		return sb.toString();
	}

	private List<Type> getTypesOfExpressions(List<Expression> expressions) {
		List<Type> types = new ArrayList<Type>();
		if (expressions.size() == 0) {
			return types;
		}
		return types;
	}
}
