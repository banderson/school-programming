import java.io.File;
import java.io.IOException;

import org.junit.*;

public class FileAccessTest {
	
	static final String TEST_FILE = "out.txt";
	File f;
	
	@Before
	public void setUp() throws Exception {
		// write a file to the disk
		f = writeFile(TEST_FILE);
		
		System.out.println("File Contents: "+ f.toString());
	}
	
	@Test public void canReadFile() {
		Assert.assertTrue("File should exist", f.exists());
		Assert.assertTrue("Object should point to a file", f.isFile());
	}
	
	public File writeFile(String relativePath) throws IOException {
		File f = new File(relativePath);
		f.createNewFile();
		
		return f;
	}
	
	public boolean Exists(File f) {
		return f.exists();
	}
	
	@After
	public void tearDown() {
		// delete the file
		f.delete();
	}

}
