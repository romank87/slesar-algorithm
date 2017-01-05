import sys
def first_less_then (array, n, value, default):
    l,r = 0,n
    while r - l > 1 :
        mid = (l+r)/2
        if array[mid][0] >= value:
            r = mid
        else:
            l = mid
    return l if array[l][0] < value else default

if __name__ == "__main__":
    n,d = [int(x) for x in sys.stdin.readline().split()]
    array = sorted([tuple([int(x) for x in sys.stdin.readline().split()]) for _ in xrange(n)])

    agregated = 0
    agregated_array = []
    for i in xrange(n):
        agregated += array[i][1]
        agregated_array.append(agr  egated)

    max_value = 0
    for (i, (money, value)) in enumerate(array):
        last = first_less_then (array, n, d + money, i)
        max_value = max(max_value, agregated_array[last] - (agregated_array[i - 1] if (i-1) >= 0 else 0))

    print max_value

