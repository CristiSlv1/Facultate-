#
# The program's functions are implemented here. There is no user interaction in this file, therefore no input/print statements. Functions here
# communicate via function parameters, the return statement and raising of exceptions.
#

# Create a new participant

from ui import *
from functions import *
from start import *
from copy import *

def create_new_contestant(scores: list = [0, 0, 0]):

    """Create a new contestant represented as a dictionary.

    Args:
        scores (list, optional): List of scores for player 1, player 2, and player 3. Defaults to [0, 0, 0].

    Returns:
        dict: A dictionary containing player scores and the average score.
    """

    average_score = (scores[0] + scores[1] + scores[2]) * 10 // 3
    return {"p1": scores[0], "p2": scores[1], "p3": scores[2], "average": average_score}


def get_value_from_contestant(contestant: dict, position: str = ''):
    """
    Get a value from a dictionary using the specified position.

    Args:
        contestant (dict): A dictionary containing contestant information.
        position (str, optional): The position (key) to retrieve from the dictionary. Defaults to ''.

    Returns:
        str: The information from the dictionary at the specified position or the entire dictionary if position is empty.
    """
    if position == '':
        return contestant
    return contestant[position.lower()]



"""_summary_
  This module works with lists of contestant
"""


# def generate_new_list_of_contestants():
#   return []

def generate_history_command():
    """Generate an empty history list with lists.

    Returns:
        list: An empty list that will store lists of contestants.
    """
    return [[]]


def add_to_history(history: list, current_list: list):
    """Add a list of contestants to the history list.

    Args:
        history (list): The history list where the current list will be added.
        current_list (list): The list of contestants to add to the history.

    """
    history.append(copy(current_list))


def add_new_contestant_in_list(list_of_contestants: list, scores: dict):
    """Add a contestant to the list of contestants.

    Args:
        list_of_contestants (list): The list of contestants where the new contestant will be inserted.
        scores (dict): The contestant's scores to be added to the list.

    """
    list_of_contestants.append(scores)


def undo_value(history: list):
    """Remove the last element (list of contestants) from the history list.

    Args:
        history (list): The history list to remove the last element from.

    """
    history.pop()


def insert_in_list(list_of_contestants: list, values: list):
    """
    Update the values of a contestant in the list of contestants.

    Args:
        list_of_contestants (list): The list of contestants where the update will occur.
        values (list): A list containing the new scores for a contestant and the position to update.

    Raises:
        ValueError: Raised if the specified position is out of bounds.
        ValueError: Raised if the scores provided are not between 0 and 10.

    """
    if int(values[4]) >= len(list_of_contestants):
        raise ValueError("Final position is bigger than the length of list.")
    position = int(values.pop())
    values.pop()
    for i in range(len(values)):
        values[i] = int(values[i])
        if values[i] > 10 or values[i] < 0:
            raise ValueError("Scores needs to be between 0 and 10.")
    list_of_contestants[position] = create_new_contestant(values)


def remove_from_list(list_of_contestants: list, position: tuple):
    """Remove contestants from the list based on a range of positions.

    Args:
        list_of_contestants (list): The list of contestants where removal will occur.
        position (tuple): A tuple specifying the range of positions to remove.

    """
    first_position = position[0]
    while first_position <= position[1]:
        list_of_contestants[first_position] = create_new_contestant()
        first_position += 1


def remove_from_list_with_sign(list_of_contestants: list, command: list):
    """Remove contestants from the list based on a specified score and sign.

    Args:
        list_of_contestants (list): The list of contestants where removal will occur.
        command (list): A list containing the sign and score to determine removal.

    """
    score = int(command[1])
    remove_from_list_with_score(list_of_contestants, score, command[0])


def remove_from_list_with_score(list_of_contestants: list, score: int, sign: str):
    """ Remove contestants from the list based on a specified score and sign.

    Args:
        list_of_contestants (list): The list of contestants where removal will occur.
        score (int): The score used as a threshold for removal.
        sign (str): The sign ('=', '<', or '>') used to compare scores for removal.

    """

    if sign == '=':
        for i in range(len(list_of_contestants)):
            if get_value_from_contestant(list_of_contestants[i], "average") == score:
                list_of_contestants[i] = create_new_contestant()
    elif sign == '<':
        for i in range(len(list_of_contestants)):
            if get_value_from_contestant(list_of_contestants[i], "average") < score:
                list_of_contestants[i] = create_new_contestant()
    elif sign == '>':
        for i in range(len(list_of_contestants)):
            if get_value_from_contestant(list_of_contestants[i], "average") > score:
                list_of_contestants[i] = create_new_contestant()


def replace_score(list_of_contestants: list, old_score: int, position: str, new_score: int):
    """Replace a score for a contestant in the list of contestants.

    Args:
        list_of_contestants (list): The list of contestants where the score will be replaced.
        old_score (int): The old score to be replaced.
        position (str): The position (player) to update the score.
        new_score (int): The new score to replace the old score with.
    """
    new_contestants = create_new_contestant(get_value_from_contestant(list_of_contestants[old_score], "p1"),
                                            get_value_from_contestant(list_of_contestants[old_score], "p2"),
                                            get_value_from_contestant(list_of_contestants[old_score], "p3"))
    new_contestants[position] = new_score
    list_of_contestants[old_score] = new_contestants


# LOGICAL FUNCTIONS FOR COMMANDS

def extract_from_remove_command(command: list):
    """Extract positions from a 'remove' command.

    Args:
        command (list): A list containing the 'remove' command.

    Returns:
        tuple: A tuple specifying the range of positions to remove.
    """
    positions = ()
    if command[0].isnumeric():
        if len(command) == 1:
            positions = (int(command[0]), int(command[0]))
        else:
            positions = (int(command[0]), int(command[2]))
    else:
        positions = (0, 0)
    return positions


def sort_contestant_list(list_of_contestants: list):
    """Sort a list of contestants based on their average scores in ascending order.

    Args:
        list_of_contestants (list): The list of contestants to be sorted.

    Returns:
        list: A sorted list of contestants.
    """
    sorted_list = sorted(list_of_contestants, key=lambda i: i['average'])
    return sorted_list


def show_list_for_sign(list_of_contestants: list, sign: str, score: int):
    """Process and filter a list of contestants based on a specified sign and score.

    Args:
        list_of_contestants (list): The list of contestants to be filtered.
        sign (str): The sign ('=', '<', or '>') used for comparison.
        score (int): The score used as a threshold for filtering.

    Returns:
        list: A filtered list of contestants that meet the specified criteria.
    """
    new_list = []
    list_with_id = []

    if sign == '=':
        for i in range(len(list_of_contestants)):
            if get_value_from_contestant(list_of_contestants[i], "average") == score:
                new_list.append(list_of_contestants[i])
                list_with_id.append(i)

    elif sign == '<':
        for i in range(len(list_of_contestants)):
            if get_value_from_contestant(list_of_contestants[i], "average") < score:
                new_list.append(list_of_contestants[i])
                list_with_id.append(i)

    elif sign == '>':
        for i in range(len(list_of_contestants)):

            ''''
            if get_value_from_contestant(list_of_contestants[i], "average") > score:
                new_list.append(list_of_contestants[i])
                list_with_id.append(i)
            '''
    return new_list, list_with_id


def sort_list_by(list_of_contestants_original: list, sort_by: str):
    """Sort a list of contestants based on a specified element in descending order.

    Args:
        list_of_contestants_original (list): The original list of contestants to be sorted.
        sort_by (str): The element (player) to sort the contestants by.

    Returns:
        list: A sorted list of contestants in descending order.
    """
    sorted = False
    sort_by = sort_by.lower()
    id_list = []
    list_of_contestants = list_of_contestants_original.copy()
    for i in range(len(list_of_contestants)):
        sorted = True
        id_list.append(i)
        for j in range(1, len(list_of_contestants)):
            if get_value_from_contestant(list_of_contestants[j], sort_by) > get_value_from_contestant(
                    list_of_contestants[j - 1], sort_by):
                aux = list_of_contestants[j - 1]
                list_of_contestants[j - 1] = list_of_contestants[j]
                list_of_contestants[j] = aux
                sorted = False
                id_list[-1] = j - 1
        if sorted == True:
            i = len(list_of_contestants)
    return list_of_contestants, id_list


def top_list(list_of_contestants: list, sort_by: str, number: int):
    """Retrieve the top N contestants based on a specified element and number.

    Args:
        list_of_contestants (list): The list of contestants to be considered.
        sort_by (str): The element (player) to sort the contestants by.
        number (int): The number of top contestants to retrieve.

    Returns:
        list: The top N contestants based on the specified criteria.
    """
    new_sort, id_list = sort_list_by(list_of_contestants, sort_by)
    return new_sort[:number], id_list[:number]