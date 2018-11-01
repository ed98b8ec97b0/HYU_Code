import json

train_data = json.load(open('train.json'))
output = []
for recipe in train_data:
    ingredients = recipe['ingredients']
    for ingredient in ingredients:
        if (ingredient not in output):
            output.append(ingredient)


f = open('pre.txt', 'w')
output.sort()
for o in output:
    f.write(o + "\n")