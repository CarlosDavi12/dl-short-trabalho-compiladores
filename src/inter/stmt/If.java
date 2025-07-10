package inter.stmt;

import inter.expr.Expr;
import lexer.Tag;
import llvm.LLVMEmitter;

public class If extends Stmt {
	private Expr expr;
	private Stmt stmt;

	public If(Expr e, Stmt s) {
		if (!e.type().isBool())
			error("esperada uma expressão lógica");
		expr = e;
		stmt = s;
		addChild(expr);
		addChild(stmt);
	}

	@Override
	public String toString() {
		return Tag.IF.toString();
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		String cond = expr.getLLVM(emitter);
		String labelTrue = emitter.newLabel();
		String labelEnd = emitter.newLabel();

		// if cond then labelTrue else labelEnd
		emitter.emit("br i1 " + cond + ", label %" + labelTrue + ", label %" + labelEnd);

		// bloco verdadeiro
		emitter.emitLabel(labelTrue);
		stmt.genLLVM(emitter);

		// fim do if
		emitter.emitLabel(labelEnd);
	}
}
