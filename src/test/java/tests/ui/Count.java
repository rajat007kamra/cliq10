package tests.ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Count implements IAnnotationTransformer {
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	        int numberOfTimesTOExecute = 2;
			// TODO Auto-generated method stub
	        annotation.setInvocationCount(numberOfTimesTOExecute);
	    }

}