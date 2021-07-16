import string


arr = []

data = list(string.ascii_lowercase)

for d in data:
    if d == "r":
        break
    for i in range(1, 30):
        arr.append("1")


for d in data:
    for i in range(1, 2):
        arr.append(d)
arr = arr[:700]
print("".join(arr))
