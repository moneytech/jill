// $Header$

package mnj.lua;

// For j2meunit see http://j2meunit.sourceforge.net/
import j2meunit.framework.Test;
import j2meunit.framework.TestSuite;

// Auxiliary files
// PackageLibTest.lua

/**
 * J2MEUnit tests for Jili's package library.  DO NOT SUBCLASS.
 * public access granted only because j2meunit makes it necessary.
 */
public class PackageLibTest extends JiliTestCase
  {
  /** void constructor, necessary for running using
   * <code>java j2meunit.textui.TestRunner PackageLibTest</code>
   */
  public PackageLibTest() { }

  /** Clones constructor from superclass.  */
  private PackageLibTest(String name)
  {
    super(name);
  }

  public void runTest()
  {
    // loads PackageLibTest.lua and calls a function defined therein
    Lua L = new Lua();
    BaseLib.open(L);
    PackageLib.open(L);
    StringLib.open(L);
    MathLib.open(L);    // required by test8

    loadFileAndRun(L, "PackageLibTest.lua", getName(), 0);
    assertTrue(true);
  }

  public Test suite()
  {
    TestSuite suite = new TestSuite();

    suite.addTest(new PackageLibTest("test1"));
    suite.addTest(new PackageLibTest("test2"));
    suite.addTest(new PackageLibTest("test3"));
    suite.addTest(new PackageLibTest("test4"));
    suite.addTest(new PackageLibTest("test5"));
    suite.addTest(new PackageLibTest("test6"));
    suite.addTest(new PackageLibTest("test7"));
    suite.addTest(new PackageLibTest("test8"));
    suite.addTest(new PackageLibTest("test9"));

    return suite;
  }
}