/*  $Header$
 *  (c) Copyright 2002-2006, Intuwave Ltd. All Rights Reserved.
 *
 *  Although Intuwave has tested this program and reviewed the documentation,
 *  Intuwave makes no warranty or representation, either expressed or implied,
 *  with respect to this software, its quality, performance, merchantability,
 *  or fitness for a particular purpose. As a result, this software is licensed
 *  "AS-IS", and you are assuming the entire risk as to its quality and
 *  performance.
 *
 *  You are granted license to use this code as a basis for your own
 *  application(s) under the terms of the separate license between you and
 *  Intuwave.
 */

package mnj.lua;

/**
 * Represent a Lua error
 */
final class LuaError extends RuntimeException
{
  int errorStatus;

  LuaError(int errorStatus)
  {
    super("Lua error");
    this.errorStatus = errorStatus;
  }
}