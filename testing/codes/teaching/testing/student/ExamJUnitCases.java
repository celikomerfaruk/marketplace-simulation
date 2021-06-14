
package codes.teaching.testing.student;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

/* TODO: Import the classes you need for testing */

// Tests will be sorted in lexicographical order. Therefore start the names as "test1_TestName" 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//@RunWith(JUnit4.class)
public final class ExamJUnitCases extends ExamJUnit {
	
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//	private final PrintStream originalOut = System.out;
//	private final PrintStream originalErr = System.err;
	
	public static String lineSeperator = System.lineSeparator();

	@Rule
	public TestName name = new TestName();
	
	@Test
	public void test1_emptyCheck() {
		String inputForReport = "Empty check. Does nothing actually.";
		String expectedAnswer = "success";
		ExamJUnit.testInitialization(1, name.getMethodName(), inputForReport, expectedAnswer);
		try {
			answerStudent = "success";
			
			ExamJUnit.testCheck(answerStudent);
		} catch (Exception e) {
			testFailedExecution(e);
		}
	}
}
