package EXtentReport;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Cross_Browser {
	  @Test
	    public void testAddition() {
	       Functions.Chrome();
	       Functions.function();
	    }

	    @Test
	    public void testSubtraction() throws InterruptedException {
	    	Functions.Edge();
		       Functions.function();
	    }
}
