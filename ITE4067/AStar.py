import itertools, operator, copy

class AStar:
    def __init__(self, init, target, graph):
        self.graph = graph
        self.init = init
        self.target = target
        self.result = []

    def h(self, head):
        return abs(head[0] - self.target[0]) + abs(head[1] - self.target[1])        

    def f(self, head, history):
        return len(history) + self.h(head)

    def close(self, x, y):
        wall = {}
        for i, j in itertools.product(range(x), range(y)):
            if self.graph[i][j]==1:
                wall[(i, j)] = 1
            else:
                wall[(i, j)] = 0
        return wall
    
    def find_direction(self, head, wall):
        x, y = head
        direction = {}
        if (x > 0):
            direction[(-1, 0)] = wall[(x-1, y)]
        if (x < len(self.graph)-1):
            direction[(1, 0)] = wall[(x+1, y)]
        if (y > 0):
            direction[(0, -1)] = wall[(x, y-1)]
        if (y < len(self.graph[0])-1):
            direction[(0, 1)] = wall[(x, y+1)]
        return direction

    def search(self, head, wall, history):
        if head == self.target:
            self.result.append((len(history), history))
        direction = self.find_direction(head, wall)
        move_cost = {}
        for d in direction:
            if direction[d] == 0:
                future = tuple(map(operator.add, head, d))
                move_cost[d] = self.f(future, history)
        if bool(move_cost):
            sorted_cost = sorted(move_cost.items(), key=lambda kv: kv[1])
            for s, _ in sorted_cost:
                future = tuple(map(operator.add, head, s))
                new_wall = copy.deepcopy(wall)
                new_wall[head] = 1
                new_history = copy.deepcopy(history)
                new_history.append(head)
                self.search(future, new_wall, new_history)
            


if __name__ == "__main__":
    graph = [[1, 3, 1, 1, 1, 1, 1, 1, 1, 1],
             [1, 6, 6, 1, 6, 6, 6, 1, 6, 1],
             [1, 6, 1, 6, 6, 1, 6, 6, 6, 1],
             [1, 6, 1, 1, 6, 6, 6, 1, 1, 1],
             [1, 6, 6, 6, 6, 1, 6, 6, 6, 1],
             [1, 1, 6, 1, 1, 1, 6, 1, 6, 1],
             [1, 6, 6, 1, 6, 1, 1, 1, 6, 1],
             [1, 1, 6, 1, 6, 6, 6, 6, 6, 1],
             [1, 1, 6, 6, 1, 6, 1, 1, 6, 1],
             [1, 1, 1, 1, 1, 5, 1, 1, 1, 1]]
    a = AStar(init=(0,1), target=(9,5), graph=graph)
    wall = a.close(x=len(graph), y=len(graph[0]))
    a.search((0,1), wall, [])
    print(sorted(a.result))
    