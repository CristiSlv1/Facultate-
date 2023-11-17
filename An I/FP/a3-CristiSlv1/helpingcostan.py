def citire_lista(lst: list):
    n = int(input("Introduceti numarul de elemente: "))

    for i in range(0, n):
        ele = int(input())
        lst.append(ele)


lst = []

# def primul_criteriu():


def are_cel_putin_doua_cifre_comune(num1, num2):
    set_num1 = set(str(num1))
    set_num2 = set(str(num2))
    common_digits = set_num1.intersection(set_num2)
    return len(common_digits) >= 2


def criteriul_doi(lst):
    max_sequence = []
    current_sequence = [lst[0]]

    for i in range(1, len(lst)):
        if are_cel_putin_doua_cifre_comune(current_sequence[-1], lst[i]):
            current_sequence.append(lst[i])
        else:
            if len(current_sequence) > len(max_sequence):
                max_sequence = current_sequence.copy()
            current_sequence = [lst[i]]

    if len(current_sequence) > len(max_sequence):
        max_sequence = current_sequence

    return max_sequence


def menu():
    while True:
        print("\nMenu:")
        print("1. Citire lista: ")
        print("2. Prima secventa: ")
        print("3. A doua secventa: ")
        print("4. Exit")

        choice = input("Introduceti alegerea (1-4): ")

        if choice == '1':
            citire_lista(lst)
        elif choice == '2':
            print(lst)
        elif choice == '3':
            rezultat = criteriul_doi(lst)
            print("Lungimea maxima a secventei cu cel putin 2 cifre distincte comune:", rezultat)
        elif choice == '4':
            print("Parasire program!")
            break

        else:
            print("Invalid choice. Please enter a number between 1 and 4.")


if __name__ == "__main__":
    menu()
