package actions.output.base;

import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteExcel {

	
	private boolean doesExcelExist(String filePathString) {
		return Files.exists(Paths.get(filePathString));
	}
	
	private boolean doesHeaderExist() {
		
		return false;
	}
	
	
}
