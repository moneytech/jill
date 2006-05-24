// $Header$

/**
 * Class that models Lua's tables.  Each Lua table is an instance of
 * this class.
 */
public final class LuaTable extends java.util.Hashtable {
  private Object metatable;

  LuaTable() {
    super();
  }

  /**
   * Fresh LuaTable with hints for preallocating to size.
   * @param narray  number of array slots to preallocate.
   * @param nhash   number of hash slots to preallocate.
   */
  LuaTable(int narray, int nhash) {
    super(narray+nhash);
  }

  /**
   * Getter for metatable member.
   * @return  The metatable.
   */
  Object getMetatable() {
    return metatable;
  }
  /**
   * Setter for metatable member.
   * @param metatable  The metatable.
   */
  // :todo: Support metatable's __gc and __mode keys appropriately.
  //        This involves detecting when those keys are present in the
  //        metatable, and changing all the entries in the Hashtable
  //        to be instance of java.lang.Ref as appropriate.
  void setMetatable(Object metatable) {
    this.metatable = metatable;
    return;
  }

  /**
   * Like put for numeric (integer) keys.
   */
  void putnum(int k, Object v) {
    put(new Double(k), v);
  }

  /**
   * Supports Lua's length (#) operator.  More or less equivalent to
   * "unbound_search" in ltable.c.
   */
  int getn() {
    int i = 0;
    int j = 1;
    // Find 'i' and 'j' such that i is present and j is not.
    while (get(new Double(j)) != null) {
      i = j;
      j *= 2;
      if (j < 0) {      // overflow
        // Pathological case.  Linear search.
        i = 1;
        while (get(new Double(i)) != null) {
          ++i;
        }
        return i-1;
      }
    }
    // binary search between i and j
    while (j - i > 1) {
      int m = (i+j)/2;
      if (get(new Double(m)) == null) {
        j = m;
      } else {
        i = m;
      }
    }
    return i;
  }
}
