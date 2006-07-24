-- $Header$

function check (a, f)
  f = f or function (x,y) return x<y end;
  for n=#a,2,-1 do
    assert(not f(a[n], a[n-1]))
  end
end

-- From [LUA 2006-06-28] sort.lua
function testsort()
  a = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
       "Oct", "Nov", "Dec"}

  table.sort(a)
  check(a)
  return true
end

-- From [LUA 2006-06-28] strings.lua
function testconcat()
  assert(table.concat{} == "")
  assert(table.concat({}, 'x') == "")
  assert(table.concat({'\0', '\0\1', '\0\1\2'}, '.\0.') == "\0.\0.\0\1.\0.\0\1\2")
  local a = {}; for i=1,3000 do a[i] = "xuxu" end
  -- assert(table.concat(a, "123").."123" == string.rep("xuxu123", 3000))
  assert(table.concat(a, "b", 20, 20) == "xuxu")
  assert(table.concat(a, "", 20, 21) == "xuxuxuxu")
  assert(table.concat(a, "", 22, 21) == "") 
  assert(table.concat(a, "3", 2999) == "xuxu3xuxu")

  a = {"a","b","c"}
  assert(table.concat(a, ",", 1, 0) == "")
  assert(table.concat(a, ",", 1, 1) == "a")
  assert(table.concat(a, ",", 1, 2) == "a,b")
  assert(table.concat(a, ",", 2) == "b,c")
  assert(table.concat(a, ",", 3) == "c")
  assert(table.concat(a, ",", 4) == "")
  return true
end

-- From [Lua 2006-06-28] nextvar.lua
function testinsertremove()
  local function test (a)
    table.insert(a, 10); table.insert(a, 2, 20);
    table.insert(a, 1, -1); table.insert(a, 40);
    table.insert(a, #a+1, 50)
    table.insert(a, 2, -2)
    assert(table.remove(a,1) == -1)
    assert(table.remove(a,1) == -2)
    assert(table.remove(a,1) == 10)
    assert(table.remove(a,1) == 20)
    assert(table.remove(a,1) == 40)
    assert(table.remove(a,1) == 50)
    assert(table.remove(a,1) == nil)
  end

  a = {n=0, [-7] = "ban"}
  test(a) 
  assert(a.n == 0 and a[-7] == "ban")
      
  a = {[-7] = "ban"};
  test(a)
  assert(a.n == nil and #a == 0 and a[-7] == "ban")


  table.insert(a, 1, 10); table.insert(a, 1, 20); table.insert(a, 1, -1)
  assert(table.remove(a) == 10)
  assert(table.remove(a) == 20)
  assert(table.remove(a) == -1)
    
  a = {'c', 'd'}
  table.insert(a, 3, 'a')
  table.insert(a, 'b')
  assert(table.remove(a, 1) == 'c')
  assert(table.remove(a, 1) == 'd')
  assert(table.remove(a, 1) == 'a')
  assert(table.remove(a, 1) == 'b')
  assert(#a == 0 and a.n == nil)
  return true
end

-- From [Lua 2006-06-28] nextvar.lua
function testmaxn()
  assert(table.maxn{} == 0)
  assert(table.maxn{["1000"] = true} == 0)
  assert(table.maxn{["1000"] = true, [24.5] = 3} == 24.5)
  assert(table.maxn{[1000] = true} == 1000)
  -- assert(table.maxn{[10] = true, [100*math.pi] = print} == 100*math.pi)
  return true
end