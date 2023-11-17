# Function to check if three points are collinear
def are_collinear(p1, p2, p3):
    x1, y1 = p1
    x2, y2 = p2
    x3, y3 = p3

    return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)



# Recursive function to find collinear subsets
def find_collinear_subsets(points):
    # Backtracking function
    def backtrack(start, current_subset):
        if len(current_subset) >= 3:
            collinear = True
            for i in range(len(current_subset) - 2):
                if not are_collinear(current_subset[i], current_subset[i + 1], current_subset[i + 2]):
                    collinear = False
                    break

            if collinear:
                collinear_subsets.append(current_subset[:])

        for i in range(start, len(points)):
            current_subset.append(points[i])
            backtrack(i + 1, current_subset)  # Recursive call to explore subsets
            current_subset.pop()

    collinear_subsets = []
    backtrack(0, [])  # Start the backtracking process from index 0 and an empty current_subset
    return collinear_subsets


n = int(input("Enter the number of points: "))
points = []

for i in range(n):
    x, y = map(int, input(f"Enter coordinates of point {i + 1} (x y): ").split())
    points.append((x, y))

# Find collinear subsets using recursive backtracking
collinear_subsets = find_collinear_subsets(points)


if collinear_subsets:
    for subset in collinear_subsets:
        print("Collinear subset:", subset)
else:
    print("No collinear subsets of minimum three points found.")
