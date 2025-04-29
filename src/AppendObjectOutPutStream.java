import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObjectOutPutStream extends ObjectOutputStream {

    public AppendObjectOutPutStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // 不写入新的流头，而是重置流状态
        reset();
    }
}
