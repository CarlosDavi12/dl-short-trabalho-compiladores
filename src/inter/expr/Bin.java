package inter.expr;

import lexer.Tag;
import lexer.Token;
import llvm.LLVMEmitter;

public class Bin extends Expr {
	protected Expr expr1;
	protected Expr expr2;

	public Bin(Token op, Expr e1, Expr e2) {
		super(op, null);
		type = maxType(e1.type(), e2.type());
		if (this.type == null)
			error("tipos incompatíveis");
		expr1 = e1;
		expr2 = e2;
		addChild(expr1);
		addChild(expr2);
	}

	private static Tag maxType(Tag t1, Tag t2) {
		if (!t1.isNum() || !t2.isNum())
			return null;
		else if (t1.isReal() || t2.isReal())
			return Tag.REAL;
		else
			return Tag.INT;
	}

	@Override
	public String getLLVM(LLVMEmitter emitter) {
		String left = expr1.getLLVM(emitter);
		String right = expr2.getLLVM(emitter);
		String result = emitter.newTemp();

		switch (op.lexeme()) {
			case "+":
				emitter.emitAdd(result, left, right);
				break;
			case "-":
				emitter.emitSub(result, left, right);
				break;
			case ">":
				emitter.emit(result + " = icmp sgt i32 " + left + ", " + right);
				break;
			case "<":
				emitter.emit(result + " = icmp slt i32 " + left + ", " + right);
				break;
			case ">=":
				emitter.emit(result + " = icmp sge i32 " + left + ", " + right);
				break;
			case "<=":
				emitter.emit(result + " = icmp sle i32 " + left + ", " + right);
				break;
			case "==":
				emitter.emit(result + " = icmp eq i32 " + left + ", " + right);
				break;
			case "!=":
				emitter.emit(result + " = icmp ne i32 " + left + ", " + right);
				break;
			default:
				emitter.emit("; operador binário não suportado: " + op.lexeme());
		}

		return result;
	}
}
