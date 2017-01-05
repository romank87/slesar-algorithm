import sys

def first_greater_or_equal(array, value):
    length = len(array)
    l,r = 0,length
    while r-l > 1:
        mid = (r+l)/2
        if value >= array[mid]:
            l = mid
        else:
            r = mid

    if array[l] >= value:
        return array[l]
    if r < length and array[r] >= value:
        return array[r]
    return None

def solve(array, n, lines):
    d = {}
    for (idx, value) in enumerate(array.split()):
        l = d.get(int(value))
        if l == None:
            d[int(value)] = [idx+1]
        else:
            l.append(idx+1)

    for values in d.values():
        values.sort()


    solution = []
    for line in lines:
        start,end,x = [int(i) for i in line.split()]
        l = d.get(x)
        if l == None:
            solution.append("0")
            continue

        res = first_greater_or_equal(l,start)
        if res != None and res <= end:
            solution.append("1")
        else:
            solution.append("0")

    return "".join(solution)


n = int(sys.stdin.readline())
array = sys.stdin.readline()
q = int(sys.stdin.readline())
queries = [sys.stdin.readline() for _ in xrange(q) ]
print solve (array, n, queries)

#assert (solve())
#assert (solve([4,7,7,7,7,1,2,4,5], ["1 1 4","3 4 7", "2 2 2", "9 9 5"]) == "1101")
#assert (solve([1], ["1 1 1"]) == "1")
#assert (solve([2], ["1 1 1"]) == "0")
#assert (solve([1], ["1 1 3"]) == "0")
#assert (solve([5,6], ["1 1 1"]) == "0")
#assert (solve([5,6], ["1 1 5", "2 2 6"]) == "11")
#assert (solve([5,6], ["1 2 5", "1 2 6"]) == "11")
#assert (solve([6,6], ["1 1 6", "2 2 6"]) == "11")
#assert (solve([6,6], ["1 2 6", "1 2 6"]) == "11")
#assert (solve([6,6], ["1 2 4", "1 2 6"]) == "01")
#assert (solve([6,6], ["1 2 4", "1 2 5"]) == "00")
#assert (solve([3,3,3,3,3], ["1 5 3", "1 1 3"]) == "11")
#assert (solve([3,3,3,3,5], ["5 5 5", "4 4 3"]) == "11")
#assert (solve([1,2,3,4,5], ["1 1 1", "2 2 2", "3 3 3", "4 4 4", "5 5 5"]) == "11111")
#assert (solve([1,2,3,4,5], ["1 5 1", "2 5 2", "3 5 3", "4 5 4", "5 5 5"]) == "11111")
#assert (solve([6,6,6,6,6], ["1 5 6"]) == "1")
#assert (solve([99999999], ["1 1 99999999"]) == "1")
#assert (solve([999999999,4], ["2 2 999999999"]) == "0")

#print solve([4,7,7,7,7,1,2,4,5], ["1 1 4","3 4 7", "2 2 2", "9 9 5"])
#print solve([1], ["1 1 1","1 1 3", "2 2 2", "9 9 5"])
#print solve([5,3], ["1 1 5","1 1 3", "2 2 3", "2 2 5"])
#print solve([5,3], ["1 1 4","1 1 4", "2 2 4", "2 2 4"])
#print solve([5,5,5], ["1 1 5","1 2 5", "2 2 5", "1 3 5"])
#print solve([5,3], ["1 1 4","1 1 4", "2 2 4", "2 2 4"])
#
