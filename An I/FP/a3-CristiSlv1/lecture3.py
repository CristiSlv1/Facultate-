def prim(num):
    if num < 2:
        return False
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return False
    return True


def functie_calcul(lst):
    # initializam n - lungimea listei; secv, index_anterior - liste auxiliare
    n = len(lst)
    secv = [1] * n
    index_anterior = [-1] * n

    for i in range(1, n):
        for j in range(i):
            if prim(lst[i]) and prim(lst[j]) and lst[i] > lst[j] and secv[i] < secv[j] + 1:
                secv[i] = secv[j] + 1
                index_anterior[i] = j

    '''
        in forul de sus parcurgem lista data de user, si calculam in secv[i] care este secv maxima pana la i, de aici alea 
        doua foruri, deci la final in vectorul secv o sa avem cea mai lunga secventa pana la fiecare element din vector
        in vectorul index_anterior sunt pozitivi indecsii celei mai lungi secvente 
    '''
    lungime_max = max(secv)  # lungimea maxima a secventei care respecta cerinta
    lungime_max_index = secv.index(lungime_max)  # indexul ultimului element din lista (chiar daca e elementul 11 - locul 10;
                                                # lista incepe de la 0)

    lista_finala = []

    # adaugam elemente in lista finala incepand de la ultimul element

    '''
        facem un for in care punem elementele cu indecsii pozitivi (astfel stiind ca acele elemente fac parte din secventa)
        dupa ce am pus un element, verificam in vectorul index_anterior pe ce pozitie se afla elementul anterior din secventa 
        ceruta,
        
        in for incepem si punem elementele de la coada, si folosim comanda insert ca sa punem elementele mai mici in fata;
        
    '''
    for i in range(1, lungime_max + 1):
        lista_finala.insert(0,(lst[lungime_max_index]))
        lungime_max_index = index_anterior[lungime_max_index]

    return lista_finala, lungime_max


# input_list = []
input_list = [1, 2, 5, 3, 2, 5, 1, 7, 12, 11, 22 , 13, 43, 17]


lista, lungime = functie_calcul(input_list)
print(lista, lungime)
