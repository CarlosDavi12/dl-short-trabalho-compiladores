package inter.stmt;

import inter.Node;
import llvm.LLVMEmitter;

public abstract class Stmt extends Node {
    public abstract void genLLVM(LLVMEmitter emitter);
}
