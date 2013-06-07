package javatraits.symbols;

public class BuiltInTypeSymbol extends Symbol {
	public static final String bInt = "int";
	public static final String bLong = "long";
	public static final String bShort = "short";
	public static final String bDouble = "double";
	public static final String bByte = "byte";
	public static final String bChar = "char";
	public static final String bFloat = "float";
	public static final String bString = "String";
	public static final String bBoolean = "boolean";
	
	public BuiltInTypeSymbol(String name) {
		super(name, null, 0);
	}
}
