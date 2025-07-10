package llvm;

import java.util.*;

public class LLVMEmitter {
    private List<String> code = new ArrayList<>();
    private int tempCounter = 0;
    private int labelCounter = 0;

    public LLVMEmitter() {
        emitHeader();
    }

    private void emitHeader() {
        code.add("; LLVM IR gerado automaticamente");
        code.add("declare i32 @printf(i8*, ...)");
        code.add("@print.str = constant [4 x i8] c\"%d\\0A\\00\"");
    }

    public void emit(String line) {
        code.add(line);
    }

    public String newTemp() {
        return "%t" + (tempCounter++);
    }

    public String newLabel() {
        return "L" + (labelCounter++);
    }

    public void emitPrint(String value) {
        emit("call i32 (i8*, ...) @printf(i8* getelementptr ([4 x i8], [4 x i8]* @print.str, i32 0, i32 0), i32 "
                + value + ")");
    }

    public void emitAssign(String var, String value) {
        emit("%" + var + " = alloca i32");
        emit("store i32 " + value + ", i32* %" + var);
    }

    public void emitStore(String var, String value) {
        emit("store i32 " + value + ", i32* %" + var);
    }

    public void emitLoad(String result, String var) {
        emit(result + " = load i32, i32* %" + var);
    }

    public void emitSub(String result, String left, String right) {
        emit(result + " = sub i32 " + left + ", " + right);
    }

    public void emitAdd(String result, String left, String right) {
        emit(result + " = add i32 " + left + ", " + right);
    }

    public void emitNeg(String result, String operand) {
        emit(result + " = sub i32 0, " + operand);
    }

    public void emitLabel(String label) {
        emit(label + ":");
    }

    public void writeToFile(String filename) throws java.io.IOException {
        java.nio.file.Files.write(java.nio.file.Paths.get(filename), code);
    }

    public List<String> getCode() {
        return code;
    }
}
