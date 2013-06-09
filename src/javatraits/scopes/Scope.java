package javatraits.scopes;

import japa.parser.ast.expr.Expression;
import japa.parser.ast.type.Type;

import java.util.List;

import javatraits.symbols.Symbol;
import javatraits.symbols.SymbolNotFoundException;

public interface Scope {
	public enum ScopeType {
		Global, Local, Class, Method, Default
	}
	
	public String getName();
	
	public List<Scope> getEnclosingScopes();
	
	public Scope getEnclosingScope();
	
	public void addEnclosingScope(Scope s);
	
	public Symbol getSymbol(String name) throws SymbolNotFoundException;
	
	public void addSymbol(Symbol symbol);
	
	public boolean symbolExists(String name);
	
	public ScopeType getScopeType();
	
	public Symbol resolveType(String name);
	
	public Symbol resolveVariable(String name);
	
	public Type resolveMethod(String name, List<Expression> expressions, boolean runningFirst);

}
