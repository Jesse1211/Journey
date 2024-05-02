General format for backtracking

Pseudo Code

def backtracking(parameters):
    if meet requirement:
        add the path to the result
    
    for option in your options:
        make the choice
        backtracking
        undo the choice

Personally, I find it easier to handle when the parameters contain: inputList, path, used
Here, "used" is used to keep track of if we have added the element in the path, which might be added 
to the final result



