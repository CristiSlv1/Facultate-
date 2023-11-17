# Function to check if three points are collinear
def are_collinear(p1, p2, p3):
    x1, y1 = p1
    x2, y2 = p2
    x3, y3 = p3

    return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)



# Iterative function to find collinear subsets
def find_collinear_subsets(points):
    n = len(points)
    collinear_subsets = []

    stack = [(0, [], 0)]  # (index, current_subset, count)

    while stack:
        index, current_subset, count = stack.pop()

        if count >= 3:
            collinear = True
            for i in range(count - 2):
                if not are_collinear(current_subset[i], current_subset[i + 1], current_subset[i + 2]):
                    collinear = False
                    break

            if collinear:
                collinear_subsets.append(current_subset[:])

        for i in range(index, n):
            current_subset.append(points[i])
            stack.append((i + 1, current_subset[:], count + 1))
            current_subset.pop()

    return collinear_subsets


n = int(input("Enter the number of points: "))
points = []


for i in range(n):
    x, y = map(int, input(f"Enter coordinates of point {i + 1} (x y): ").split())
    points.append((x, y))

# Find collinear subsets using iterative backtracking
collinear_subsets = find_collinear_subsets(points)

if collinear_subsets:
    for subset in collinear_subsets:
        print("Collinear subset:", subset)
else:
    print("No collinear subsets of at least three points found.")
