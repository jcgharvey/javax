package javatraits.scopes;


public class GlobalScope extends BasicScope {
	public GlobalScope(){
		super(null, "Global");
		this.scopeType = ScopeType.Global;
	}
	
	@Override
	public Scope getEnclosingScope() {
		return this;
	}
}