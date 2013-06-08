package javatraits.scopes;


public class GlobalScope extends BasicScope {
	public GlobalScope(){
		super(null);
		this.scope = ScopeType.Global;
	}
	
	@Override
	public Scope getEnclosingScope() {
		return this;
	}
}