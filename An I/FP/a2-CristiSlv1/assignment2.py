# Create a console based menu (press 1 to do...., pres 2 to do....)
# o	Generate a list (length given by the user; numbers between 0-100)
# o	2 Sorting algorithms
# o	Exit
# (the user can generate the list and then to sort that list; if the user press sort on empty
# list print an error message)
# (2 functions; 1 for each sorting algorithms; functions for menu; function for generating....)
# Def sort(.....)
# Def sort2(list, step); EX: if step == 2, we show every 2 steps, etc...
#
# I have to do:
# 	Selection sort
# 	Heap sort
import random


def generate_list(length):
    return [random.randint(0, 100) for _ in range(length)]


def heap_sort(lst, step):
    def heapify(lst, n, i):
        largest = i
        left = 2 * i + 1
        right = 2 * i + 2

        if left < n and lst[i] < lst[left]:
            largest = left

        if right < n and lst[largest] < lst[right]:
            largest = right

        if largest != i:
            lst[i], lst[largest] = lst[largest], lst[i]
            heapify(lst, n, largest)

    n = len(lst)

    for i in range(n // 2 - 1, -1, -1):
        heapify(lst, n, i)

    for i in range(n - 1, 0, -1):
        lst[i], lst[0] = lst[0], lst[i]
        heapify(lst, i, 0)
        if (n - i) % step == 0:
            print("Step", n-i,":", lst )

    return lst


def selection_sort(lst, step):
    n = len(lst)
    for i in range(n):
        min_index = i
        if i % step == 0:
            for j in range(i + 1, n):
                if lst[j] < lst[min_index]:
                    min_index = j
            lst[i], lst[min_index] = lst[min_index], lst[i]
            print("Step", i, ':', lst)
        elif i % step != 0:
            for j in range(i + 1, n):
                if lst[j] < lst[min_index]:
                    min_index = j
            lst[i], lst[min_index] = lst[min_index], lst[i]
    return lst


def menu():
    while True:
        print("\nMenu:")
        print("1. Generate a list")
        print("2. Sort using heap sort")
        print("3. Sort using selection sort")
        print("4. Exit")

        choice = input("Enter your choice (1-4): ")

        if choice == '1':
            length = int(input("Enter the length of the list: "))
            generated_list = generate_list(length)
            print("Generated list:", generated_list)

        elif choice == '2':
            if 'generated_list' in locals():
                step = int(input("Enter the step for display: "))
                if not generated_list:
                    print("Error: Please generate a list first.")
                    return
                heap_sorted_list = heap_sort(generated_list.copy(), step)
                print("Heap Sorted list:", heap_sorted_list)
            else:
                print("Error: Please generate a list first.")

        elif choice == '3':
            if 'generated_list' in locals():
                step = int(input("Enter the step for display: "))
                if not generated_list:
                    print("Error: Please generate a list first.")
                    return
                selection_sorted_list = selection_sort(generated_list.copy(), step)
                print("\nSelection Sorted list:")
                print(selection_sorted_list)
            else:
                print("Error: Please generate a list first.")

        elif choice == '4':
            print("Exiting the program. Goodbye!")
            break

        else:
            print("Invalid choice. Please enter a number between 1 and 4.")


if __name__ == "__main__":
    menu()
