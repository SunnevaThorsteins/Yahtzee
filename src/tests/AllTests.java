package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import is.hi.yatzee.utlit.TestAdal;

@RunWith(Suite.class)
@SuiteClasses({
  TestTeningar.class,
  TestYhatzeeImpl.class,
  TestAdal.class
})
public class AllTests {

}
