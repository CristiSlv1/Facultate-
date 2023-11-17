# Solve the problem from the third set here
# Determine the age of a person, in number of days. Take into account leap years, as well as the date of birth
# and current date (year, month, day). Do not use Python's inbuilt date/time functions.
# print(6138888 % 4+1)


def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            return year % 400 == 0
        else:
            return True
    return False


def days_in_month(year, month):
    days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if month == 2 and is_leap_year(year):
        return 29
    else:
        return days[month - 1]


def calculate_age_in_days(birth_year, birth_month, birth_day, current_year, current_month, current_day):
    days = 0

    for year in range(birth_year, current_year):
        days += 365
        if is_leap_year(year):
            days += 1

    days += (int(current_day) - int(birth_day))

    for month in range(birth_month, current_month):
        days += days_in_month(current_year, month)

    return days

birth_year = int(input("Enter the birth year: "))
birth_month = int(input("Enter the month number: "))
birth_day = int(input("Enter the day: "))

current_year = 2023
current_month = 1
current_day = 1

age_in_days = calculate_age_in_days(birth_year, birth_month, birth_day, current_year, current_month, current_day)
print("Age in days:", age_in_days)
