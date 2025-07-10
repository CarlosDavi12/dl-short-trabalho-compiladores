package inter.expr;

import lexer.Tag;
import lexer.Token;
import llvm.LLVMEmitter;

public class Rel extends Expr {
	protected Expr expr1;
	protected Expr expr2;

	public Rel(Token op, Expr e1, Expr e2) {
		super(op, Tag.BOOL);
		switch (op.tag()) {
			case LT:
			case LE:
			case GT:
				if (!e1.type().isNum() || !e2.type().isNum())
					error("O operador relacional "
							+ op.lexeme()
							+ " só deve ser aplicado"
							+ " a tipos numéricos");
				break;
			default:
		}
		expr1 = e1;
		expr2 = e2;
		addChild(expr1);
		addChild(expr2);
	}

	@Override
	public String getLLVM(LLVMEmitter emitter) {
		String left = expr1.getLLVM(emitter);
		String right = expr2.getLLVM(emitter);
		String result = emitter.newTemp();

		switch (op.lexeme()) {
			case "<":
				emitter.emit(result + " = icmp slt i32 " + left + ", " + right);
				break;
			case "<=":
				emitter.emit(result + " = icmp sle i32 " + left + ", " + right);
				break;
			case ">":
				emitter.emit(result + " = icmp sgt i32 " + left + ", " + right);
				break;
			case ">=":
				emitter.emit(result + " = icmp sge i32 " + left + ", " + right);
				break;
			case "==":
				emitter.emit(result + " = icmp eq i32 " + left + ", " + right);
				break;
			case "!=":
				emitter.emit(result + " = icmp ne i32 " + left + ", " + right);
				break;
			default:
				emitter.emit("; operador relacional não suportado: " + op.lexeme());
		}

		return result;
	}
}
