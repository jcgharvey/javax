package javatraits.scopes;


public class ClassScope extends BasicScope {
	public ClassScope(Scope enclosingScope){
		super(enclosingScope);
		this.scope = ScopeType.Class;
	}
}