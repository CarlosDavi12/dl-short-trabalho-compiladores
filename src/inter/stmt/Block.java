package inter.stmt;

import java.util.List;
import llvm.LLVMEmitter;
import inter.Node;

public class Block extends Stmt {

	public Block() {
	}

	public void addStmt(Stmt stmt) {
		addChild(stmt);
	}

	@Override
	public String toString() {
		return "BLOCK";
	}

	@Override
	public void genLLVM(LLVMEmitter emitter) {
		List<Node> stmts = children(); // obtém os filhos (statements)
		for (Node node : stmts) {
			if (node instanceof Stmt stmt) {
				stmt.genLLVM(emitter); // gera LLVM para cada stmt
			}
		}
	}
}
