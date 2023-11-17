# printare meniu
def printare_meniu():
    print("""
    1. Adauga scor la un participant.
    2. Modificare scor.
    3. Tipareste lista de participanti.
    4. Operatii pe un subset de participanti.
    5. Filtrare.
    6. Undo.
    7. Afisare liste.
    8. Afisare numar participanti.
    9. Iesire program.
""")


# printare_meniu_1
def var_1():
    print("""
    a. Adauga scor la un nou participant.
    b. Inserare scor pentru un participant.
""")


# printare_meniu_2
def var_2():
    print("""
    a. Sterge scorul pentru un participant dat.
    b. Sterge scorul pentru un interval de participanti.
    c. Inlocuieste scorul de la un participant.
""")


# printare_meniu_3
def var_3():
    print("""
    a. Tipareste participantii care au un scor mai mic decat un scor dat.
    b. Tipareste participantii ordonat dupa scor.
    c. Tipareste participantii cu scor mai mare decat un scor dat, ordonat dupa scor.
""")


# printare_meniu_4
def var_4():
    print("""
    a. Calculeaza media scorurilor pentru un interval dat.
    b. Calculeaza scorul minim pentru un interval de participanti dat.
    c. Tipareste participantii dintr-un interval dat care au scorul multiplu de 10.
""")


# printare_meniu_5
def var_5():
    print("""
    a. Filtrare participanti care au scorul multiplu unui numar dat.
    b. Filtrare participanti care au scorul mai mic decat un scor dat.
""")


# 1.a
def adauga_concurent(scor, c_nou=[], l_note=[], l_finala=[]):
    c_nou = [scor]
    l_note.extend([c_nou])
    l_finala.append(scor)

    return l_note, l_finala


def test_adauga_concurent():
    assert adauga_concurent(4, [], [[1, 2]], [3]) == ([[1, 2], [4]], [3, 4])
    assert adauga_concurent(4, [], [], []) == ([[4]], [4])


# 1.b
def inserare_scor(scor, concurent, l_note=[], l_finala=[]):
    if l_note[concurent - 1] == [0]:
        l_note[concurent - 1] = [scor]
    else:
        l_note[concurent - 1].append(scor)

    suma = 0
    for i in l_note[concurent - 1]:
        suma = suma + i
    l_finala[concurent - 1] = suma

    return l_note, l_finala


def test_inserare_scor():
    assert inserare_scor(5, 2, [[4, 5], [1, 2]], [9, 3]) == ([[4, 5], [1, 2, 5]], [9, 8])
    assert inserare_scor(5, 2, [[4, 5], [0]], [9, 0]) == ([[4, 5], [5]], [9, 5])


# 2.a
def sterge_scor_participant(part, l_note=[], l_finala=[]):
    l_note[part - 1] = [0]
    l_finala[part - 1] = 0

    return l_note, l_finala


def test_sterge_scor_participant():
    assert sterge_scor_participant(2, [[1, 2], [3, 4]], [3, 7]) == ([[1, 2], [0]], [3, 0])
    assert sterge_scor_participant(3, [[1, 2], [3, 4], [1, 2, 3]], [3, 7, 6]) == ([[1, 2], [3, 4], [0]], [3, 7, 0])


# 2.b
def sterge_scor_interval(p1, p2, l_note=[], l_finala=[]):
    for i in range(p1 - 1, p2):
        l_finala[i] = 0
        l_note[i] = [0]
    return l_note, l_finala


def test_sterge_scor_interval():
    assert sterge_scor_interval(2, 3, [[1, 2], [3, 4], [5, 6]], [3, 7, 11]) == ([[1, 2], [0], [0]], [3, 0, 0])


# 2.c
def inlocuieste_nota(nota, p, l_note=[]):
    if l_note[p - 1] == [0]:
        l_note[p - 1] = [nota]
    else:
        l_note[p - 1].append(nota)
    return l_note


def inlocuieste_scor(p, l_note=[], l_finala=[]):
    suma = 0
    for i in l_note[p - 1]:
        suma = suma + i
    l_finala[p - 1] = suma

    return l_finala


def test_inlocuieste_nota():
    assert inlocuieste_nota(5, 1, [[0], [1, 2]]) == [[5], [1, 2]]
    assert inlocuieste_nota(5, 1, [[2, 3], [1, 2]]) == [[2, 3, 5], [1, 2]]


def test_inlocuieste_scor():
    assert inlocuieste_scor(2, [[1, 2], [0]], [3, 4]) == [3, 0]
    assert inlocuieste_scor(2, [[1, 2], [3, 4]], [3, 5]) == [3, 7]


# 3.a
def scor_mai_mic(scor, lst=[], list_afis=[]):
    for i in range(0, len(lst)):
        if lst[i] < scor:
            list_afis.append(i + 1)

    return list_afis


def test_scor_mai_mic():
    assert scor_mai_mic(10, [5, 6, 13, 4, 11, 10, 1], []) == [1, 2, 4, 7]
    assert scor_mai_mic(10, [11, 12], []) == []


# 3.b
def ord_dupa_scor(lst=[], lst2=[]):
    for i in range(0, len(lst)):
        for j in range(i + 1, len(lst)):
            if lst[i] > lst[j]:
                aux1 = lst[i]
                lst[i] = lst[j]
                lst[j] = aux1

                aux2 = lst2[i]
                lst2[i] = lst2[j]
                lst2[j] = aux2

    return lst2


def test_ord_dupa_scor():
    assert ord_dupa_scor([3, 1, 2], [1, 2, 3]) == [2, 3, 1]
    assert ord_dupa_scor([3, 1, 2, 4, 10, 11], [1, 2, 3, 4, 5, 6]) == [2, 3, 1, 4, 5, 6]


# 3.c
def ord_dupa_scor_maimaredecatunscor(scor, lst=[], lst2=[], list_afis=[]):
    for i in range(0, len(lst)):
        for j in range(i + 1, len(lst)):
            if lst[i] > lst[j]:
                aux1 = lst[i]
                lst[i] = lst[j]
                lst[j] = aux1

                aux2 = lst2[i]
                lst2[i] = lst2[j]
                lst2[j] = aux2

    for i in range(0, len(lst2)):
        if lst[i] > scor:
            list_afis.append(lst2[i])

    return list_afis


def test_ord_dupa_scor_maimaredecatunscor():
    assert ord_dupa_scor_maimaredecatunscor(10, [8, 10, 15, 13, 16], [1, 2, 3, 4, 5], []) == [4, 3, 5]
    assert ord_dupa_scor_maimaredecatunscor(18, [8, 10, 15, 13, 16], [1, 2, 3, 4, 5], []) == []


# 4.a
def medie_scor_interval(participant1, participant2, lista_note=[], lista_finala=[]):
    suma_scoruri = 0
    nr_scoruri = 0

    for i in range(participant1 - 1, participant2):
        suma_scoruri += lista_finala[i]
        nr_scoruri += len(lista_note[i])

    if nr_scoruri == 0:
        return 0.0

    medie = suma_scoruri / nr_scoruri
    return medie


def test_medie_scor_interval():

    assert medie_scor_interval( 2, 3, [[1, 2], [8, 9], [10]], [3, 17, 10]) == 27 / 3
    assert medie_scor_interval( 1, 3, [[1, 2], [2, 3], [3, 4]], [3, 5, 7]) == 2.5


# 4.b
def scor_minim_interval(s, p1, p2, lst=[]):
    for i in range(p1 - 1, p2):
        if s > lst[i] and lst[i] > 0:
            s = lst[i]
    return s


def test_scor_minim_interval():
    assert scor_minim_interval(1001, 1, 3, [10, 5, 18]) == 5
    assert scor_minim_interval(1001, 1, 3, [10, 2, 18]) == 2
    assert scor_minim_interval(1001, 1, 3, [0, 0, 0]) == 1001


# 4.c
def tip_part_m(p1, p2, lst1=[], lst2=[]):
    for i in range(p1 - 1, p2):
        if lst1[i] % 10 == 0:
            lst2.append(i + 1)
    return lst2


def test_tip_part_m():
    assert tip_part_m(1, 3, [10, 11, 30], []) == [1, 3]
    assert tip_part_m(1, 3, [11, 11, 32], []) == []


# 5.a
def filtrare_scor_multiplu(numar, l_note=[], l_finala=[]):
    for i in range(0, len(l_finala)):
        if l_finala[i] % numar != 0:
            l_finala[i] = 0
            l_note[i] = [0]
    return l_note, l_finala


def test_filtrare_scor_multiplu():
    assert filtrare_scor_multiplu(5, [[1, 2], [3, 2], [10, 5], [4]], [3, 5, 15, 4]) == (
    [[0], [3, 2], [10, 5], [0]], [0, 5, 15, 0])
    assert filtrare_scor_multiplu(5, [[1, 4], [3, 2], [10, 5]], [5, 5, 15]) == ([[1, 4], [3, 2], [10, 5]], [5, 5, 15])
    assert filtrare_scor_multiplu(5, [[1, 2], [4]], [3, 4]) == ([[0], [0]], [0, 0])


# 5.b
def filtrare_scor_mai_mic(scor, l_note=[], l_finala=[]):
    for i in range(0, len(l_finala)):
        if l_finala[i] < scor:
            l_finala[i] = 0
            l_note[i] = [0]

    return l_note, l_finala


def test_filtrare_scor_mai_mic():
    assert filtrare_scor_mai_mic(10, [[1, 2]], [3]) == ([[0]], [0])
    assert filtrare_scor_mai_mic(10, [[1, 2], [5, 6]], [3, 11]) == ([[0], [5, 6]], [0, 11])


# 6.undo
def undo(concurenti, uconcurenti, l_note=[], l_finala=[], ul_note=[], ul_finala=[]):
    l_note = ul_note.copy()
    l_finala = ul_finala.copy()
    concurenti = uconcurenti

    return concurenti, l_note, l_finala


# teste
test_adauga_concurent()
test_inserare_scor()
test_sterge_scor_participant()
test_sterge_scor_interval()
test_inlocuieste_nota()
test_inlocuieste_scor()
test_scor_mai_mic()
test_ord_dupa_scor()
test_ord_dupa_scor_maimaredecatunscor()
test_medie_scor_interval()
test_scor_minim_interval()
test_tip_part_m()
test_filtrare_scor_multiplu()
test_filtrare_scor_mai_mic()


def citeste_numar(numar: str):
    while True:
        try:
            return int(input(numar))
        except ValueError:
            print("Alegere invalida! Mai incearca o data!")


def run():
    lista_note = [[5, 6, 7], [8, 9]]
    lista_finala = [18, 17]
    concurenti = 2


    undo_lista_note = [[5, 6, 7], [8, 9]]
    undo_lista_finala = [18, 17]
    undo_concurenti = 2

    final = False
    while not final:
        printare_meniu()
        alegere = input("Introduceti alegerea(1-8): ")

        if alegere == "1":

            var_1()
            varianta = input("Introduceti varianta(a/b): ")

            if varianta == "a":
                scor = int(citeste_numar("Introduceti scorul: "))

                # concurent_nou:list = [scor]
                # lista_note.extend([concurent_nou])
                # lista_finala.append(scor)
                concurent_nou = []

                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti

                concurenti += 1
                lista_note, lista_finala = adauga_concurent(scor, concurent_nou, lista_note, lista_finala)


            elif varianta == "b":
                concurent = int(citeste_numar("Introduceti concurentul: "))
                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti
                if len(lista_note[concurent - 1]) < 9:
                    scor = int(citeste_numar("Introduceti scorul: "))
                    # if lista_note[concurent-1] == [0]:
                    # lista_note[concurent-1] = [scor]
                    # else:
                    # lista_note[concurent-1].append(scor)
                    # suma = 0
                    # for i in lista_note[concurent-1]:
                    # suma = suma + i
                    # lista_finala[concurent-1] = suma

                    # undo_lista_note = lista_note.copy()
                    # undo_lista_finala = lista_finala.copy()
                    # undo_concurenti = concurenti

                    lista_note, lista_finala = inserare_scor(scor, concurent, lista_note, lista_finala)

                else:
                    print("Participantul are deja numarul maxim de note!")
            else:
                print("Alegere invalida! Alegeti una din variantele a/b!")
        elif alegere == "2":

            var_2()
            varianta = input("Introduceti varianta(a/b/c): ")

            if varianta == "a":

                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti

                participant = int(citeste_numar("Introduceti participantul: "))
                # lista_note[participant-1] = [0]
                # lista_finala[participant-1] = 0

                lista_note, lista_finala = sterge_scor_participant(participant, lista_note, lista_finala)

            elif varianta == "b":

                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti

                participant1 = int(citeste_numar("Introduceti primul participant: "))
                participant2 = int(citeste_numar("Introduceti ultimul participant: "))
                # for i in range(participant1-1, participant2):
                # lista_finala[i] = 0
                # lista_note[i] = [0]
                lista_note, lista_finala = sterge_scor_interval(participant1, participant2, lista_note, lista_finala)

            elif varianta == "c":

                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti

                participant = int(citeste_numar("Introduceti participantul: "))
                # lista_note[participant-1] = [0]
                # lista_finala[participant-1] = 0
                sterge_scor_participant(participant, lista_note, lista_finala)
                nr_note = int(citeste_numar("Introduceti numarul de note: "))
                for i in range(nr_note):
                    nota = int(citeste_numar("Introduceti nota: "))
                    # if lista_note[participant-1] == [0]:
                    # lista_note[participant-1] = [nota]
                    # else:
                    # lista_note[participant-1].append(nota)
                    lista_note = inlocuieste_nota(nota, participant, lista_note)
                # suma = 0
                # for i in lista_note[participant-1]:
                # suma = suma + i
                # lista_finala[participant-1] = suma
                lista_finala = inlocuieste_scor(participant, lista_note, lista_finala)
            else:
                print("Alegere invalida! Alegeti una din variantele a/b/c!")

        elif alegere == "3":

            var_3()
            varianta = input("Introduceti varianta(a/b/c): ")

            if varianta == "a":
                scor_dat = int(citeste_numar("Introduceti scorul dat: "))
                list_afis = []
                a = scor_mai_mic(scor_dat, lista_finala, list_afis)
                print(a)

            elif varianta == "b":
                c_lista_finala = lista_finala.copy()
                list_p = []
                for i in range(0, len(lista_finala)):
                    list_p.append(i + 1)
                ord_dupa_scor(c_lista_finala, list_p)
                a = list_p
                print(a)

            elif varianta == "c":
                scor = int(citeste_numar("Introduceti scorul dat: "))
                c_lista_finala = lista_finala.copy()
                list_p = []
                for i in range(0, len(lista_finala)):
                    list_p.append(i + 1)
                list_afis = []
                ord_dupa_scor_maimaredecatunscor(scor, c_lista_finala, list_p, list_afis)
                a = list_afis
                print(a)
            else:
                print("Alegere invalida! Alegeti una din variantele a/b/c!")

        elif alegere == "4":

            var_4()
            varianta = input("Introduceti varianta(a/b/c): ")

            if varianta == "a":

                participant1 = int(citeste_numar("Introduceti primul participant: "))
                participant2 = int(citeste_numar("Introduceti ultimul participant: "))
                suma_scoruri = 0
                # for i in range(participant1-1, participant2):
                # suma_scoruri += lista_finala[i]

                nr_scoruri = 0
                # for i in range(participant1-1, participant2):
                # nr_scoruri += len(lista_note[i])
                a = medie_scor_interval(suma_scoruri, nr_scoruri, participant1, participant2, lista_finala, lista_note)
                print(a)

            elif varianta == "b":

                participant1 = int(citeste_numar("Introduceti primul participant: "))
                participant2 = int(citeste_numar("Introduceti ultimul participant: "))
                scor_minim = 1001

                # for i in range(participant1-1, participant2):
                # if scor_minim > lista_finala[i]:
                # scor_minim = lista_finala[i]
                a = scor_minim_interval(scor_minim, participant1, participant2, lista_finala)
                if a == 1001:
                    print("Nu exista scoruri!")
                else:
                    print(a)

            elif varianta == "c":

                participant1 = int(citeste_numar("Introduceti primul participant: "))
                participant2 = int(citeste_numar("Introduceti ultimul participant: "))

                list_afis = []

                # for i in range(participant1-1, participant2):
                # if lista_finala[i] % 10 == 0:
                # print(i)
                a = tip_part_m(participant1, participant2, lista_finala, list_afis)
                print(a)
            else:
                print("Alegere invalida! Alegeti una din variantele a/b/c!")

        elif alegere == "5":

            var_5()
            varianta = input("Introduceti varianta(a/b): ")

            if varianta == "a":

                numar = int(citeste_numar("Introduceti numarul: "))
                # for i in range(0, len(lista_finala)):
                # if lista_finala[i] % numar != 0:
                # lista_finala[i] = 0
                # lista_note[i] = [0]

                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti

                lista_note, lista_finala = filtrare_scor_multiplu(numar, lista_note, lista_finala)

            elif varianta == "b":

                scor = int(citeste_numar("Introduceti scor: "))
                # for i in range(0, len(lista_finala)):
                # if lista_finala[i] < scor:
                # lista_finala[i] = 0
                # lista_note[i] = [0]

                undo_lista_note = lista_note.copy()
                undo_lista_finala = lista_finala.copy()
                undo_concurenti = concurenti

                lista_note, lista_finala = filtrare_scor_mai_mic(scor, lista_note, lista_finala)
            else:
                print("Alegere invalida! Alegeti una din variantele a/b!")

        elif alegere == "6":
            # undo2_lista_note = lista_note.copy()
            # undo2_lista_finala = lista_finala.copy()
            # undo2_concurenti = concurenti
            concurenti, lista_note, lista_finala = undo(concurenti, undo_concurenti, lista_note, lista_finala,
                                                        undo_lista_note, undo_lista_finala)

        elif alegere == "7":

            for i in lista_note:
                print(i)
            print(lista_finala)

        elif alegere == "8":

            print(concurenti)

        elif alegere == "9":

            final = True
            print("Parasire program")
        else:
            print("Alegere invalida! Alege un numar de la 1 la 9!")


run()