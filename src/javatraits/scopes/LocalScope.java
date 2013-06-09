package javatraits.scopes;


public class LocalScope extends BasicScope {
	public LocalScope(Scope enclosingScope, String name) {
		super(enclosingScope, name);
		this.scopeType = ScopeType.Local;
	}
}
