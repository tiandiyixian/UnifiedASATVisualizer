package BlueTurtle.parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BlueTurtle.finders.ProjectInfoFinder;
import BlueTurtle.warnings.FindBugsWarning;
import BlueTurtle.warnings.Warning;

/**
 * Test class for FindBugsXMLParser.
 * 
 * @author BlueTurtle.
 *
 */
public class FindBugsXMLParserTest {

	private static String testSet2 = "./src/test/resources/exampleFindbugs1.xml";
	private static String srcDir = System.getProperty("user.dir") + "/src";

	private static String testSet2FileName = "FindBugsWarning.java";
	private static String testSet2RuleName = "HE_EQUALS_USE_HASHCODE";
	private static String testSet2Message = "BlueTurtle.warnings.FindBugsWarning defines equals and uses Object.hashCode()";
	private static String testSet2Category = "BAD_PRACTICE";
	private static String testSet2Priority = "High";
	private static String testSet2Classification = "Interface";
	private static String testSet3FilePath = System.getProperty("user.dir") + File.separatorChar + "src"
			+ File.separatorChar + "main" + File.separatorChar + "java" + File.separatorChar + "BlueTurtle"
			+ File.separatorChar + "warnings" + File.separatorChar + "FindBugsWarning.java";

	/**
	 * Set up the GDP parser, parse the category information.
	 * 
	 * @throws IOException
	 *             throws an exception while reading the files.
	 */
	@Before
	public void setUp() throws IOException {
		new ProjectInfoFinder().findFiles(new File(srcDir));
	}

	/**
	 * Clear the attributes of ProjectInfoFinder.
	 */
	@After
	public void tearDown() {
		ProjectInfoFinder.getClassLocs().clear();
		ProjectInfoFinder.getClassPackage().clear();
		ProjectInfoFinder.getClassPaths().clear();
		ProjectInfoFinder.getPackages().clear();

	}

	/**
	 * Test that the parser can parse a valid FindBugs output file.
	 */
	@Test
	public void testParseCorrectBehaviour() {
		FindBugsXMLParser parser = new FindBugsXMLParser();

		List<Warning> warnings = parser.parseFile(testSet2);

		assertSame(2, warnings.size());
	}

	/**
	 * Test whether the parser creates the right object.
	 */
	@Test
	public void testParsingOneWarning() {
		FindBugsXMLParser parser = new FindBugsXMLParser();

		FindBugsWarning expected = new FindBugsWarning(testSet3FilePath, testSet2FileName, 47, testSet2Message,
				testSet2Category, testSet2Priority, testSet2RuleName, testSet2Classification);

		FindBugsWarning actual = (FindBugsWarning) parser.parseFile(testSet2).get(0);

		assertEquals(expected, actual);
	}


	/**
	 * Test that the parser parse the wrong file.
	 */
	@Test
	public void testParseTheWrongFile() {
		FindBugsXMLParser parser = new FindBugsXMLParser();

		String testSet3 = "/ex.xml";

		List<Warning> warnings = parser.parseFile(testSet3);

		assertSame(0, warnings.size());
	}

}
