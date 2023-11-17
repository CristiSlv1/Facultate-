#
# Write the implementation for A5 in this file
#
from math import sqrt


def generate_list():
    return []


def valid(option_chosen: str):
    if option_chosen.isnumeric() and int(option_chosen) in [1, 2, 3, 4, 5, 6, 7]:
        return True
    return False


def clear_list(complex_number_inserted: list):
    complex_number_inserted.clear()


def test_for_1(complex_number_inserted: list):
    values = ['1+i', '2+2i', '3+4i', '3+4i', '23+5i', '12+3i', '12+3i', '8+24i', '9+6i', '10+i']
    for value in values:
        add_complex_number(str(value), complex_number_inserted)


def test_for_13(complex_number_inserted: list):
    values = [1, 3, 2, 4, 10, 6, 1]
    for value in values:
        add_complex_number(str(value), complex_number_inserted)


# Proceed option and call what function he need
def option_proceed(option_chosen: int, complex_number_inserted):

    if option_chosen == 1:
        create_list_ui(complex_number_inserted)

    elif option_chosen == 2:
        if len(complex_number_inserted) == 0:
            raise ValueError("Inserted List is empty. You need to insert complex numbers.")
        problem_1_ui(complex_number_inserted)

    elif option_chosen == 3:
        if len(complex_number_inserted) == 0:
            raise ValueError("Inserted List is empty. You need to insert complex numbers.")
        problem_13_ui(complex_number_inserted)

    elif option_chosen == 4:
        if len(complex_number_inserted) == 0:
            raise ValueError("Inserted List is empty. You need to insert complex numbers.")
        clear_list(complex_number_inserted)

    elif option_chosen == 5:
        test_for_1(complex_number_inserted)

    elif option_chosen == 6:
        test_for_13(complex_number_inserted)

    elif option_chosen == 7:
        quit()


# Verify if the input is a complex number
def valid_str(number: str):

    if len(number) == 0:
        return False
    number_split = split_number(number)

    if len(number_split) != 2:
        return False

    if number_split[1][0] == "-":
        number_split[1] = number_split[1][1::]

    if number_split[0][0] == "+" or number_split[0][0] == "-":
        number_split[0] = number_split[0][1::]

    index_i_on_imaginary_part = number_split[1].find("i")

    if index_i_on_imaginary_part != len(number_split[1]) - 1:
        return False

    return True


# Split number in real part and imaginary part
def split_number(number: str):

    sign_real = ''
    middle_sign = ''
    number_split = ''

    if number[0] == "+":
        sign_real = number[0]
        number = number[1::]

    if "+" in number:
        number_split = number.split("+")

    if number_split == '':
        return [sign_real+number, '0i']

    for char in number_split[0]:
        sign_real += char
    number_split[0] = sign_real.strip()

    for char in number_split[1]:
        middle_sign += char
    number_split[1] = middle_sign.strip()

    return number_split


# Extract imaginary part from input
def divide_imaginary_part(imaginary_part_split: str):
    imaginary_part = ''
    if imaginary_part_split == 'i':
        return '1'
    for char in imaginary_part_split:
        if char != "i":
            imaginary_part += str(char)
    if imaginary_part == '-':
        imaginary_part += '1'
    return imaginary_part


# Problem Solving


def problem_1(complex_number_inserted: list):

    # Variable initialization
    current_subarray = []
    max_subarray = []

    for nr_complex in complex_number_inserted:
        # If the number not in the list, we add it
        if nr_complex not in current_subarray:
            current_subarray.append(nr_complex)
            if len(current_subarray) > len(max_subarray):
                max_subarray = current_subarray
        else:
            # if the number is in the list we start a new list
            if len(current_subarray) > len(max_subarray):
                max_subarray = current_subarray
            current_subarray = [nr_complex]

        # Check if the current subarray is greater than the length of max subarray
        if len(current_subarray) > len(max_subarray):
            max_subarray = current_subarray
    return max_subarray, len(max_subarray)


def problem_13(complex_number_inserted: list):
    length_of_list = len(complex_number_inserted)
    if length_of_list == 0:
        return []

    # dp - matrice cu doua coloane, am salvat lungimea maxima daca elementul din i e mai mic decat ultimul
    # element din secventa, si daca e mai mic, sau invers

    # dp[i][0] - lungimea secventei cerute cu ultimul element > elementul precedent
    # dp[0][1] - lungimea secventei cerute cu ultimul element < elementul precedent
    dp = [[1, 1] for _ in range(length_of_list)]

    for i in range(length_of_list):
        real_part_of_i = int(get_real_part(complex_number_inserted[i]))
        imaginary_part_of_i = int(get_imaginary_part(complex_number_inserted[i]))
        modulus_i = sqrt((real_part_of_i * real_part_of_i) + (imaginary_part_of_i * imaginary_part_of_i))

        for j in range(i):
            real_part_of_j = int(get_real_part(complex_number_inserted[j]))
            imaginary_part_of_j = int(get_imaginary_part(complex_number_inserted[j]))
            modulus_j = sqrt((real_part_of_j * real_part_of_j) + (imaginary_part_of_j * imaginary_part_of_j))

            # if modulus of i is grater than modulus of j number then check with dp[j][1]
            if modulus_i > modulus_j and dp[i][0] < dp[j][1] + 1:
                dp[i][0] = dp[j][1] + 1
            # if modulus of i number is smaller than modulus of j number then check with dp[j][0]
            elif modulus_i < modulus_j and dp[i][1] < dp[j][0] + 1:
                dp[i][1] = dp[j][0] + 1

    # We search max length of the requested subarray

    max_length = max(max(dp))
    result = []
    for i in range(length_of_list - 1, -1, -1):
        if max(dp[i]) == max_length:
            result.append(complex_number_inserted[i])
            max_length -= 1
    max_length = max(max(dp))
    return result[::-1], max_length


#
# Write below this comment
# Functions to deal with complex numbers -- list representation
# -> There should be no print or input statements in this section
# -> Each function should do one thing only
# -> Functions communicate using input parameters and their return values
#

# def create_complex_to_be_added(real_part: str, imaginary_part: str):
#     return real_part, imaginary_part
#
#
# def get_imaginary_part(number: list):
#     return number[1]
#
#
# def get_real_part(number: list):
#     return number[0]


#
# Write below this comment
# Functions to deal with complex numbers -- dict representation
# -> There should be no print or input statements in this section
# -> Each function should do one thing only
# -> Functions communicate using input parameters and their return values
#
# =========================================================================


def create_complex_to_be_added(real_part: str, imaginary_part: str):
    return {"real": real_part, "imaginary": imaginary_part}


def get_imaginary_part(number: list):
    return number['imaginary']


def get_real_part(number: list):
    return number['real']

#
# Write below this comment
# Functions that deal with subarray/subsequence properties
# -> There should be no print or input statements in this section
# -> Each function should do one thing only
# -> Functions communicate using input parameters and their return values
#


def add_complex_number(number: str, complex_number_inserted):
    if number.isnumeric():
        number += ' + 0i'

    if not valid_str(number.strip()):
        raise ValueError("The inserted number is incorrect!")

    number_split = split_number(number)
    real_part = number_split[0]
    imaginary_part = divide_imaginary_part(number_split[1])
    complex_number_inserted.append(create_complex_to_be_added(real_part, imaginary_part))
    return complex_number_inserted


#
# Write below this comment
# Todo UI section
# Write all functions that have input or print statements here
# Ideally, this section should not contain any calculations relevant to program functionalities
#
def problem_1_ui(complex_number_inserted: list):
    max_subb, len_max_subb = problem_1(complex_number_inserted)
    print(f"\nThe length of the requested subarray: {len_max_subb}")
    print(f"The subarray: {max_subb}")
    # print_complex_list(max_subb,1)


def problem_13_ui(complex_number_inserted: list):
    solution_list, length = problem_13(complex_number_inserted)
    print(f"Length: {length}")
    print_complex_list(solution_list, 1)


def create_list_ui(complex_number_inserted: list):
    number = input("Type the complex number(a+bi): ")
    try:
        add_complex_number(number, complex_number_inserted)
    except ValueError as e:
        print("ERROR: " + str(e))


def print_menu():
    print("<<<<<<<<<<<Menu for Complex Numbers>>>>>>>>>>")
    print("1. Add a complex number to list. ")
    print("2. Solve problem 1. ")
    print("3. Solve problem 13. ")
    print("4. Clear inserted list. ")
    print("5. Add test for first problem: ['1+i', '2+2i', '3+4i', '3+4i', '23+5i', '12+3i', '12+3i', '8+24i', '9+6i', '10+i']")
    print("6. Test for the problem 13: [1, 3, 2, 4, 10, 6, 1]")
    print("7. Exit")


def print_complex_list(complex_number_inserted: list, case: int):
    solution_subb = []
    for value in complex_number_inserted:
        solution_subb.append(get_real_part(value) + '+' + get_imaginary_part(value) + 'i')
    if case == 1:
        print(f"\nElements: \n{solution_subb}\n")
    elif case == 2:
        print(f"\nInserted numbers: \n{solution_subb}\n")


def choose_option(complex_number_inserted: list):
    option = input("Choose option: ")
    if not valid(option):
        raise ValueError("You need to write a valid option!")
    try:
        option_proceed(int(option), complex_number_inserted)
    except ValueError as e:
        print("ERROR: " + str(e))


def start():
    complex_number_inserted = generate_list()
    while True:
        print_complex_list(complex_number_inserted, 2)
        print_menu()
        try:
            choose_option(complex_number_inserted)
        except ValueError as e:
            print("ERROR: " + str(e))


if __name__ == "__main__":
    start()
    