"""
Determine the longest common subsequence of two given sequences. Subsequence elements are not required to occupy
    consecutive positions. For example, if X = "MNPNQMN" and Y = "NQPMNM", the longest common subsequence has length 4,
    and can be one of "NQMN", "NPMN" or "NPNM". Determine and display both the length of the longest common subsequence
    as well as at least one such subsequence.
"""


def longest_common_subsequence(X, Y):
    m, n = len(X), len(Y)  # salvam lungimile sirurilor

    dp = [[0] * (n + 1) for _ in range(m + 1)]
    # Aici se creează o matrice bidimensională dp pentru a stoca lungimile subsecvențelor comune maxime la fiecare
    # poziție (i, j). Această matrice are dimensiunile (m+1) x (n+1) și este inițializată cu zerouri.

    # Fill the dp array using dynamic programming, parcurgem matricea
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if X[i - 1] == Y[j - 1]:  # daca sunt egale se incrementeaza lungimea secventei comune
                dp[i][j] = dp[i - 1][j - 1] + 1
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
                # Dacă caracterele nu sunt egale, atunci lungimea subsecvenței comune maximă la poziția (i, j)
                # este maximul dintre lungimea de sus (dp[i-1][j]) și lungimea din stânga (dp[i][j-1]).
        print(dp)
    lcs_length = dp[m][n]  # lungimea subsecventei comune maxima
    max_length = lcs_length
    lcs = [""] * lcs_length
    # Se creează o listă goală lcs care va fi folosită pentru a stoca caracterele din subsecvența comună maximă.

    i, j = m, n

    # Aici se începe reconstrucția subsecvenței comune maximă folosind matricea dp.
    # Se verifică dacă nu s-a ajuns la marginea superioară sau stângă a matricei.
    while i > 0 and j > 0:
        # Dacă sunt egale, caracterul X[i-1] este adăugat la lista lcs
        # la poziția lcs_length-1. Aceasta este o parte a subsecvenței comune maximă.
        if X[i - 1] == Y[j - 1]:
            lcs[lcs_length - 1] = X[i - 1]
            i -= 1
            j -= 1
            lcs_length -= 1
            # Se actualizează pozițiile i și j, precum și lungimea subsecvenței comune maximă.

        elif dp[i - 1][j] > dp[i][j - 1]:
            i -= 1
            # Dacă caracterul din X nu este egal cu caracterul din Y,
            # se verifică care dintre cele două valori vecine (dp[i-1][j] și dp[i][j-1]) este mai mare.
        else:
            j -= 1
        print(lcs)

    return max_length, "".join(lcs)

# Example usage:


X = "MNPNQMNM"
Y = "NQPMNM"
length, lcs = longest_common_subsequence(X, Y)
print("Length of LCS:", length)
print("LCS:", lcs)
