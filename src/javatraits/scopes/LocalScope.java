package javatraits.scopes;


public class LocalScope extends BasicScope {
	public LocalScope(Scope enclosingScope) {
		super(enclosingScope);
		this.scope = ScopeType.Local;
	}
}
