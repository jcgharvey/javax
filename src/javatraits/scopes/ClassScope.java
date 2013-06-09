package javatraits.scopes;

import java.util.ArrayList;
import java.util.List;


public class ClassScope extends BasicScope {
	
	List<ClassScope> parentScopes;
	
	public ClassScope(Scope enclosingScope, String name){
		super(enclosingScope, name);
		this.scopeType = ScopeType.Class;
		this.parentScopes = new ArrayList<ClassScope>();
	}

	public void addParentScope(ClassScope scope){
		parentScopes.add(scope);
	}
	
}