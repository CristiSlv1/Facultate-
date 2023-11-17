def naive_longest_common_subsequence(X, Y, m, n):
    if m == 0 or n == 0:
        return 0, ""

    if X[m - 1] == Y[n - 1]:
        length, lcs = naive_longest_common_subsequence(X, Y, m - 1, n - 1)
        return length + 1, lcs + X[m - 1]
    else:
        length1, lcs1 = naive_longest_common_subsequence(X, Y, m - 1, n)
        length2, lcs2 = naive_longest_common_subsequence(X, Y, m, n - 1)
        if length1 > length2:
            # print(lcs1)
            return length1, lcs1

        else:
            # print(lcs2)
            return length2, lcs2



# Example usage:
X = "MNPNQMN"
Y = "NQPMNM"
length, lcs = naive_longest_common_subsequence(X, Y, len(X), len(Y))
print("Length of LCS:", length)
print("LCS:", lcs)
