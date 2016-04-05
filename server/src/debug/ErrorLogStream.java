package debug;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ErrorLogStream extends ByteArrayOutputStream {
	
	public ErrorLogStream() {
		super();
	}
	
	@Override
	public void flush() throws IOException {
		super.flush();
		Debug.err(new String(toByteArray()));
	}
}
