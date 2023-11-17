# Solve the problem from the second set here
#print(6138888 % 6 + 1)
#Determine a calendar date (as year, month, day) starting from two integer numbers representing
#the year and the day number inside that year (e.g. day number 32 is February 1st). Take into account leap years.
#Do not use Python's inbuilt date/time functions.


def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            return year % 400 == 0
        else:
            return True
    return False

def day_of_month(year, day_number):

    days_in_month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    if is_leap_year(year):
        days_in_month[2] = 29

    month = 1
    while day_number > days_in_month[month]:
        day_number -= days_in_month[month]
        month += 1

    return month, day_number

def get_calendar_date(year, day_number):

    month, day = day_of_month(year, day_number)
    return year, month, day


year_input = int(input("Enter the year: "))
day_number_input = int(input("Enter the day: "))

calendar_date = get_calendar_date(year_input, day_number_input)
print("Calendar date: ", calendar_date)
