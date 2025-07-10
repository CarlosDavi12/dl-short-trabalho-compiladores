package inter.stmt;

import inter.expr.Expr;
import inter.expr.Id;
import lexer.Tag;
import llvm.LLVMEmitter;

public class Assign extends Stmt {

	protected Id id;
	protected Expr expr;

	public Assign(Id i, Expr e) {
		if (check(i.type(), e.type()) == null)
			error("valor de expressão "
					+ "incompatível com o "
					+ "tipo da variável");
		id = i;
		expr = e;
		addChild(id);
		addChild(expr);
	}

	private static Tag check(Tag t1, Tag t2) {
		if (t1.isReal() && !t2.isBool())
			return Tag.REAL;
		else if (t1.isInt() && t2.isInt())
			return Tag.INT;
		else if (t1.isBool() && t2.isBool())
			return Tag.BOOL;
		return null;
	}

	@Override
	public String toString() {
		return Tag.ASSIGN.toString();
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		String value = expr.getLLVM(emitter); // avalia expressão
		emitter.emitStore(id.op().lexeme(), value); // armazena valor na variável
	}
}
