# Solve the problem from the first set here
# Problem 4
# For a given natural number n find the largest natural number written with the same digits. (e.g. n=3658, m=8653).

def create_largest_numbers(clone: int, numbers: list):
    i = 0
    while clone:
        numbers.append(clone % 10)
        clone = int(clone / 10)
        i += 1

    numbers.sort(reverse=True)
    number2 = 0
    for digits in numbers:
        number2 = number2 * 10 + digits
    return number2


first_number = input("Enter the number: ")
clone = int(first_number)
numbers = []

number2 = create_largest_numbers(clone, numbers)
print("The biggest number is:", number2)
