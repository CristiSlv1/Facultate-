[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/0NiWzAn2)
# :computer: Assignment 06

## Requirements
- You will be given one of the problems below to solve
- In addition to **procedural programming**, also use **modular programming** by having a **UI** module, a **Functions** module and a **Start** module
- The **UI** module provides a command-based console user interface that accepts given commands **exactly** as stated
- Handle the case of incorrect user input by displaying error messages. The program must not crash regardless of user input.
- Use the built-in `list` or `dict` compound types to represent entities in the problem domain and access/modify them using *getter* and *setter* functions
- Use Python's [exception mechanism](https://docs.python.org/3/tutorial/errors.html) so that functions can signal that an exceptional situation, or error, has happened.
- Provide **specifications** for all non-UI functions (except getters and setters), and tests for all non-UI functions related to functionalities **(A)** and **(B)**
- Have at least 10 randomly generated items in your application at program startup
- Deadline for maximum grade is **week 9**, hard deadline is **week 11**.

## Problem Statements

### Contest
During a programming contest, each contestant had to solve 3 problems (named `P1`, `P2` and `P3`). Afterwards, an evaluation committee graded the solutions to each of the problems using integers between `0` and `10`. The committee needs a program that will allow managing the list of scores and establishing the winners. Write a program that implements the functionalities exemplified below:

**(A) Add the result of a new participant**\
`add <P1 score> <P2 score> <P3 score>`\
`insert <P1 score> <P2 score> <P3 score> at <position>`\
e.g.\
`add 3 8 10` – add a new participant with scores `3`,`8` and `10` (scores for `P1`, `P2`, `P3` respectively)\
`insert 10 10 9 at 5` – insert scores `10`, `10` and `9` at position `5` in the list (positions numbered from `0`)

**(B) Modify scores**\
`remove <position>`\
`remove <start position> to <end position>`\
`replace <old score> <P1 | P2 | P3> with <new score>`\
e.g.\
`remove 1` – set the scores of the participant at position `1` to `0`\
`remove 1 to 3` – set the scores of participants at positions `1`, `2` and `3` to `0`\
`replace 4 P2 with 5` – replace the score obtained by participant `4` at `P2` with `5`

**(C) Display participants whose score has different properties.**\
`list`\
`list sorted`\
`list [ < | = | > ] <score>`\
e.g.\
`list` – display participants and all their scores\
`list < 4` – display participants with an average score `<4`\
`list = 6` – display participants with an average score `=6`\
`list sorted` – display participants sorted in decreasing order of average score

**(D) Establish the podium**\
`top <number>`\
`top <number> <P1 | P2 | P3>`\
`remove [ < | = | > ] <score>`\
e.g.\
`top 3` – display the 3 participants having the highest average score, in descending order of average score\
`top 4 P3` – display the 4 participants who obtained the highest score for P3, sorted descending by that score\
`remove < 70` – set the scores of participants having an average score `<70` to `0`\
`remove > 89` – set the scores of participants having an average score `>89` to `0`

**(E) Undo**\
`undo` – the last operation that modified program data is reversed. The user can undo all operations performed since program start by repeatedly calling this function.

