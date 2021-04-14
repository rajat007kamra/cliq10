package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import com.squareup.javapoet.MethodSpec.Builder;

import actions.FindAction;
import core.testdata.manager.TestCase;
import core.testdata.manager.TestDataManager;
import core.testdata.manager.TestSuite;
import core.utilities.TestGenerator;
import selenium.context.Base;
import selenium.listeners.WebDriverListener;
import testNG.utils.DynamicTestNG;
import testNG.utils.MasterTestNg;
import testNG.utils.SuiteIdentifiers;

/**
 * 1. Read all test json file 2. Parse them one by one 3. Generate test classes.
 * 4. Generate Testng.xml
 * 
 * @author Surendra.Shekhawat
 *
 */
public class TestController {
	private String suitePath;
	TestDataManager testDataManager;
	private Class<?> baseClazz;
	private LinkedList<String> testCaseList;
	private LinkedList<String> testNgList;
	public Map<String, Class<?>> actionMap;

	public TestController(String suitePath) {
		this.suitePath = suitePath;
		testDataManager = new TestDataManager();
		testCaseList = new LinkedList<String>();
		testNgList = new LinkedList<String>();
	}

	public TestSuite getTestSuite(String suiteFile) {
		testDataManager.setTestDataPath(this.suitePath);
		return testDataManager.loadTestDataFromJson(suiteFile);
	}

	public List<TestSuite> getTestSuites(String suiteFile) {
		testDataManager.setTestDataPath(this.suitePath);
		return testDataManager.loadTestDataFromJsonMultipleSuites(suiteFile);
	}

	public ArrayList<TestCase> getTestCases(TestSuite testSuite) {
		return testSuite.get_testCases();
	}

	public Class<?> getBaseClazz() {
		return baseClazz;
	}

	public void setBaseClazz(Class<?> baseClazz) {
		this.baseClazz = baseClazz;
	}

	public LinkedList<String> populateTestValues(String suiteFile, Map<String, Class<?>> actionMap) {
		LinkedList<String> testList = new LinkedList<String>();
		try {
			ArrayList<TestCase> testCases = getTestCases(getTestSuite(suiteFile));
			for (TestCase testCase : testCases) {
				TestGenerator testsGenerator = new TestGenerator(testCase);
				Builder builder = testsGenerator.generateMathod();
				testsGenerator.generateAnnotations(builder);
				testsGenerator.generateStatements(builder, actionMap);
				String testCaseName = testsGenerator.generateTestClass(builder.build(), Base.class,
						WebDriverListener.class);
				testList.add(testCaseName);
				return testList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public LinkedList<String> populateTestValues(TestSuite testSuite, Map<String, Class<?>> actionMap) {
		LinkedList<String> testList = new LinkedList<String>();
		try {
			ArrayList<TestCase> testCases = getTestCases(testSuite);
			for (TestCase testCase : testCases) {
				TestGenerator testsGenerator = new TestGenerator(testCase);
				Builder builder = testsGenerator.generateMathod();
				testsGenerator.generateAnnotations(builder);
				testsGenerator.generateStatements(builder, actionMap);
				String testCaseName = testsGenerator.generateTestClass(builder.build(), Base.class,
						WebDriverListener.class);
				testList.add(testCaseName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testList;
	}

	public boolean populateTestNg(String suiteFile) {
		try {
			TestSuite testSuite = getTestSuite(suiteFile);
			SuiteIdentifiers suiteIdentifiers = new SuiteIdentifiers();
			suiteIdentifiers.setSuiteName(testSuite.get_name());
			suiteIdentifiers.setCategory(testSuite.getCategory());
			suiteIdentifiers.setBattery(testSuite.getBattery());
			suiteIdentifiers.setTestCases(testCaseList);
			DynamicTestNG dynamicTestNG = new DynamicTestNG();
			dynamicTestNG.createTestNGFile(suiteIdentifiers);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String populateTestNg(TestSuite testSuite, LinkedList<String> testList) {
		try {
			SuiteIdentifiers suiteIdentifiers = new SuiteIdentifiers();
			suiteIdentifiers.setSuiteName(testSuite.get_name());			
			suiteIdentifiers.setCategory(testSuite.getCategory());
			suiteIdentifiers.setBattery(testSuite.getBattery());
			suiteIdentifiers.setTestCases(testList);
			DynamicTestNG dynamicTestNG = new DynamicTestNG();
			String testngFile = dynamicTestNG.createTestNGFile(suiteIdentifiers);
			testNgList.add(testngFile);
			return testngFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createMasterTestng() {
		MasterTestNg masterTestNg = new MasterTestNg();
		masterTestNg.createMasterFile(testNgList);
	}

	public Map<String, Class<?>> getActionMap() {
		return actionMap;
	}

	public void setActionMap(Map<String, Class<?>> actionMap) {
		this.actionMap = actionMap;
	}

	public static void main(String[] args) throws MavenInvocationException {
		Map<TestSuite, LinkedList<String>> suiteMap = new HashMap<TestSuite, LinkedList<String>>();
		System.out.println(args[0]);
		System.out.println(args[1]);
		if (args[1] != null) {
			TestController testController = new TestController(args[0]);
			testController.setActionMap(new FindAction().createActionMap());
			List<TestSuite> testSuites = testController.getTestSuites(args[1]);
			for (TestSuite testSuite : testSuites) {
				LinkedList<String> testLists = testController.populateTestValues(testSuite,
						testController.getActionMap());
				suiteMap.put(testSuite, testLists);
			}

			// Compile the code once you test files are generated
			InvocationRequest request = new DefaultInvocationRequest();
			request.setGoals(Arrays.asList("install", "-DskipTests=true", "-Dmaven.exec.skip=true"));
			Invoker invoker = new DefaultInvoker();
			InvocationResult execute = invoker.execute(request);
			System.out.println(execute.getExitCode());

			Set<TestSuite> keySet = suiteMap.keySet();
			for (TestSuite testSuite : keySet) {
				// Generate testng file
				testController.populateTestNg(testSuite, suiteMap.get(testSuite));
			}
			testController.createMasterTestng();
		}
	}

	/*
	 * public static void main(String[] args) throws MavenInvocationException {
	 * Map<TestSuite, LinkedList<String>> suiteMap = new HashMap<TestSuite,
	 * LinkedList<String>>(); String path =
	 * "src/test/resources/testdata/file-upload/"; String suiteFile =
	 * "testSuite.json"; if (suiteFile != null) { TestController testController =
	 * new TestController(path); List<TestSuite> testSuites =
	 * testController.getTestSuites(suiteFile); for (TestSuite testSuite :
	 * testSuites) { LinkedList<String> testLists =
	 * testController.populateTestValues(testSuite); suiteMap.put(testSuite,
	 * testLists); }
	 * 
	 * // Compile the code once you test files are generated InvocationRequest
	 * request = new DefaultInvocationRequest();
	 * request.setGoals(Arrays.asList("install", "-DskipTests=true",
	 * "-Dmaven.exec.skip=true")); Invoker invoker = new DefaultInvoker();
	 * InvocationResult execute = invoker.execute(request);
	 * System.out.println(execute.getExitCode());
	 * 
	 * Set<TestSuite> keySet = suiteMap.keySet(); for (TestSuite testSuite : keySet)
	 * { // Generate testng file testController.populateTestNg(testSuite,
	 * suiteMap.get(testSuite)); } testController.createMasterTestng(); } }
	 */

}
