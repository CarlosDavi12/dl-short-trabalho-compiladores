package inter;

import lexer.Token;

public final class Emitter {
	private StringBuffer code;

	public Emitter() {
		code = new StringBuffer();
	}

	public void emit(String s) {
		code.append(s + "\n");
	}

	@Override
	public String toString() {
		return code.toString();
	}

	public void emitHead(Token name) {
		emit(";LLVM version 3.8.0 (http://llvm.org/)");
		emit(";program " + name.lexeme());
		emit("declare i32 @printf(i8*, ...) nounwind");
		emit("@str_print_int = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\", align 1");
		emit("@str_print_double = private unnamed_addr constant [7 x i8] c\"%.2lf\\0A\\00\", align 1");
		emit("define i32 @main() nounwind {");
	}

	public void emitFoot() {
		emit("ret i32 0");
		emit("}");
	}
	
	/*public void emitWrite(Expr id) {
		String str = "[4 x i8], [4 x i8]* @str_print_int";
		if ( id.type().isReal() )
			str = "[7 x i8], [7 x i8]* @str_print_double";
		Temp tPrint = new Temp(id.type());
		emit( tPrint + " = call i32 (i8*, ...) "
				+ "@printf(i8* getelementptr inbounds"
				+ "(" + str + ", i32 0, i32 0), "
				+ codeType(id.type()) + " "
				+ id + ")" );
	}*/

}
