package inter.expr;

import lexer.Tag;
import lexer.Token;
import llvm.LLVMEmitter;

public class Literal extends Expr {

	public Literal(Token op, Tag type) {
		super(op, type);
	}

	@Override
	public String toString() {
		switch (op.tag()) {
			case TRUE:
				return "true";
			case FALSE:
				return "false";
			default:
				return op.lexeme();
		}
	}

	@Override
	public String getLLVM(LLVMEmitter emitter) {
		if (op.tag() == Tag.TRUE) {
			return "1";
		} else if (op.tag() == Tag.FALSE) {
			return "0";
		} else {
			return op.lexeme(); // número literal (ex: 3, 4.5)
		}
	}
}
