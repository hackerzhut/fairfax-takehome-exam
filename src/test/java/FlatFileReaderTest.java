import static org.junit.Assert.assertEquals;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.fairfax.util.FlatFileReader;

public class FlatFileReaderTest {

	@Test (expected = RuntimeException.class)
	public void testForNull() {
		FlatFileReader fileReader = new FlatFileReader(null);
		fileReader.readFile();
	}
	
	@Test (expected = RuntimeException.class)
	public void testForFileNotFound() {
		FlatFileReader fileReader = new FlatFileReader("data/404.txt");
		fileReader.readFile();
	}
	
	@Test
	public void testReadValidFileData() {
		FlatFileReader fileReader = new FlatFileReader("data/bad_two.txt");
		List<List<String>> records = fileReader.readFile();
		assertEquals("Must return four records", 4, records.size());
		Assert.assertTrue(records.toString().contains("[[123, 1, H], [123, 2, W], [234, 1, W], [234, 2, H]]"));
	}
	
	@Test (expected = RuntimeException.class)
	public void testForInvalidFile() {
		FlatFileReader fileReader = new FlatFileReader("data/invalid.txt");
		fileReader.readFile();
	}

}
