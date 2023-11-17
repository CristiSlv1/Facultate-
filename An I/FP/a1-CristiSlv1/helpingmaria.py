def day_of_year(year, month, day):
    days_in_month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    # verifica daca e an bisect
    if year % 4 == 0 and (year % 100 != 0 or year % 400 == 0):
        days_in_month[2] = 29  # in an bisect februarie => 29 zile

    day_number = day
    for i in range(1, month):
        day_number += days_in_month[i]

    return day_number


year_input = int(input("Anul: "))
month_input = int(input("Luna: "))
day_input = int(input("Ziua: "))

rezultat = day_of_year(year_input, month_input, day_input)
print("Rezultat:", rezultat)
