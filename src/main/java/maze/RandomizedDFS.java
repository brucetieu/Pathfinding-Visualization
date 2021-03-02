package maze;

import pathfinding.Node;
import utils.Delay;

import java.util.*;


/**
 * Randomized Depth First Search maze generation.
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_depth-first_search
 */
public class RandomizedDFS {

    enum Directions {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    private Maze maze;
    private boolean[][] marked;
    private Node startNode;
    private Node endNode;

    /**
     * Initialize variables for the randomized dfs.
     * @param maze The maze.
     * @param startNode The starting node.
     * @param endNode The ending node (destination).
     */
    public RandomizedDFS(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        marked = new boolean[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
    }


    /**
     * Generate the maze with randomized dfs.
     */
    public void generateMaze() {
        dfs(this.startNode.getRow(), this.startNode.getCol());
    }


    /**
     * Perform a dfs on a node.
     *
     * @param row The row in the maze.
     * @param col The col in the maze.
     */
    private void dfs(int row, int col) {

        // Mark current cell as visited.
        marked[row][col] = true;

//        Random rand = new Random();
//        List<HashMap<Directions, Node>> randomDirs = findRandomDirs(row, col);
//        HashMap<Directions, Node> randomMap = randomDirs.get(rand.nextInt(randomDirs.size()));

        for (HashMap<Directions, Node> dir : findRandomDirs(row, col)) {
            exploreNeighbors(dir);
        }
//            if (dir.containsKey(Directions.NORTH)) {
//                Node node = dir.get(Directions.NORTH);
//                if (!marked[node.getRow()][node.getCol()]) {
//                    this.maze.maze[node.getRow() + 1][node.getCol()].setWall(false);
//                    this.maze.maze[node.getRow()][node.getCol()].setWall(false);
//                    this.maze.update();
//                    Delay.delay(10);
//                    dfs(node.getRow(), node.getCol());
//                }
//            } else if (dir.containsKey(Directions.SOUTH)) {
//                Node node = dir.get(Directions.SOUTH);
//                if (!marked[node.getRow()][node.getCol()]) {
//                    this.maze.maze[node.getRow() - 1][node.getCol()].setWall(false);
//                    this.maze.maze[node.getRow()][node.getCol()].setWall(false);
//                    this.maze.update();
//                    Delay.delay(10);
//                    dfs(node.getRow(), node.getCol());
//                }
//            } else if (dir.containsKey(Directions.WEST)) {
//                Node node = dir.get(Directions.WEST);
//                if (!marked[node.getRow()][node.getCol()]) {
//                    this.maze.maze[node.getRow()][node.getCol() + 1].setWall(false);
//                    this.maze.maze[node.getRow()][node.getCol()].setWall(false);
//                    this.maze.update();
//                    Delay.delay(10);
//                    dfs(node.getRow(), node.getCol());
//                }
//            } else if (dir.containsKey(Directions.EAST)) {
//                Node node = dir.get(Directions.EAST);
//                if (!marked[node.getRow()][node.getCol()]) {
//                    this.maze.maze[node.getRow()][node.getCol() - 1].setWall(false);
//                    this.maze.maze[node.getRow()][node.getCol()].setWall(false);
//                    this.maze.update();
//                    Delay.delay(10);
//                    dfs(node.getRow(), node.getCol());
//                }
//            }
        }

//        Directions[] directions = generateRandomDirections();


//        for (int i = 0; i < directions.length; i++) {
//            exploreNeighbors(directions, i, row, col);
//
//        }


//        for (Node node : findAdjacent(row, col)) {
//            if (!marked[node.getRow()][node.getCol()]) {
//                Delay.delay(5);
//                this.maze.update();
//                dfs(node.getRow(), node.getCol());
//            }
//        }
//        if (!marked[randomNode.getRow()][randomNode.getCol()]) {
//            this.maze.maze[randomNode.getRow()]
//        }
//        for (Node node : findRandomDirs(row, col)) {
//            if (!marked[node.getRow()][node.getCol()]) {
//                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
//                this.maze.maze[row][col-1].setWall(false);
//                            this.maze.update();
//            Delay.delay(5);
//                dfs(node.getRow(), node.getCol());
//            }
//
//        }
//        Integer[] randDirs = generateRandomDirections();

//        for (int i = 0; i < randDirs.length; i++) {
//            if (randDirs[i] == 1) {
//                if (row - 2 < 0) continue;
//                if (row - 2 >= 0 && !marked[row - 2][col]) {
                    //                marked[row-1][col] = true;
//                    this.maze.maze[row-1][col].setWall(false);
//                    this.maze.maze[row-2][col].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row - 2, col);
//                }
//            }
//            else if (randDirs[i] == 2) {
//                if (row + 2 < Maze.MAX_HEIGHT && !marked[row + 2][col]) {
//                    //                marked[row+1][col] = true;
//                    this.maze.maze[row + 1][col].setWall(false);
//                                    this.maze.maze[row+2][col].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row + 2, col);
//                }
//                if (col + 2 < Maze.MAX_WIDTH) continue;
//                if (col+2 < Maze.MAX_WIDTH && !marked[row][col + 2]) {
                    //                marked[row][col+1] = true;
//                    this.maze.maze[row][col+1].setWall(false);
//                    this.maze.maze[row][col+2].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row, col + 2);
//                }
//            }
//            else if (randDirs[i] == 3) {
//                if (col - 2 >= 0 && !marked[row][col - 2]) {
//                    //                marked[row][col-1] = true;
//                    this.maze.maze[row][col - 1].setWall(false);
//                                    this.maze.maze[row][col-2].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row, col - 2);
//                }
//                if (row + 2 >= Maze.MAX_HEIGHT ) continue;
//                                if (row+2 < Maze.MAX_HEIGHT && !marked[row + 2][col]) {
                    //                marked[row+1][col] = true;
//                    this.maze.maze[row+1][col].setWall(false);
//                    this.maze.maze[row+2][col].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row + 2, col);
//                }
//            }
//            else if (randDirs[i] == 4) {
//                if (col + 2 < Maze.MAX_WIDTH && !marked[row][col + 2]) {
//                    //                marked[row][col+1] = true;
//                    this.maze.maze[row][col + 1].setWall(false);
//                                    this.maze.maze[row][col+2].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row, col + 2);
//                }
//                if (col-2 <= 0) continue;
//                if (col-2 >= 0 && !marked[row][col - 2]) {
//                    //                marked[row][col-1] = true;
//                    this.maze.maze[row][col-1].setWall(false);
//                    this.maze.maze[row][col-2].setWall(false);
//                    this.maze.update();
//                    Delay.delay(5);
//                    dfs(row, col - 2);
//                }
//            }
//        }

        // While the current cell has any unvisited neighbor cells...
//        for (Node node : findAdjacent(row, col)) {
//
//            // Choose one of the neighboring cells (given randomly).
//            if (!this.marked[node.getRow()][node.getCol()]) {
//                this.maze.maze[row][col].setWall(false);  // Remove the wall between the current cell and the chosen cell.
//                Delay.delay(5);
//                this.maze.update();
//                dfs(node.getRow(), node.getCol());  // Invoke dfs recursively for a chosen cell.
//            }
//        }

//    }

    private List<HashMap<Directions, Node>> findRandomDirs(int row, int col) {
        List<HashMap<Directions, Node>> dirs = new ArrayList<>();

        if (row-2 >= 0) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.NORTH, this.maze.maze[row-2][col]);
            dirs.add(map);
        }
        if (row+2 < Maze.MAX_HEIGHT) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.SOUTH, this.maze.maze[row+2][col]);
            dirs.add(map);
        }
        if (col-2 >= 0) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.WEST, this.maze.maze[row][col-2]);
            dirs.add(map);
        }
        if (col+2 < Maze.MAX_WIDTH) {
            HashMap<Directions, Node> map = new HashMap<>();
            map.put(Directions.EAST, this.maze.maze[row][col+2]);
            dirs.add(map);
        }

        Collections.shuffle(dirs);
        return dirs;

    }
    /**
     * Find the adjacent unvisited nodes given a current node.
     *
     * @param row The row position of this adjacent unvisited node.
     * @param col The col position of this adjacent unvisited node.
     * @return A list of adjacent nodes.
     */
    private List<Node> findAdjacent(int row, int col) {
        List<Node> adjNeighbors = new ArrayList<>();

        // Top
        if (row-2 >= 0 && !marked[row-2][col]) {
            this.maze.maze[row-1][col].setWall(false);
            this.maze.maze[row-2][col].setWall(false);
            adjNeighbors.add(this.maze.maze[row-2][col]);
        }
        // Bottom
        if (row+2 < Maze.MAX_HEIGHT && !marked[row+2][col]) {
            this.maze.maze[row+1][col].setWall(false);
            this.maze.maze[row+2][col].setWall(false);
            adjNeighbors.add(this.maze.maze[row+2][col]);
        }
        // Left
        if (col-2 >= 0 && !marked[row][col-2]) {
            this.maze.maze[row][col-1].setWall(false);
            this.maze.maze[row][col-2].setWall(false);
            adjNeighbors.add(this.maze.maze[row][col-2]);
        }
        // Right
        if (col+2 < Maze.MAX_WIDTH && !marked[row][col+2]) {
            this.maze.maze[row][col+1].setWall(false);
            this.maze.maze[row][col+2].setWall(false);
            adjNeighbors.add(this.maze.maze[row][col+2]);
        }

        // Important! We must shuffle the list randomly, hence 'randomized' dfs.
        Collections.shuffle(adjNeighbors);
        return adjNeighbors;

    }

    private Directions[] generateRandomDirections() {
        ArrayList<Directions> randoms = new ArrayList<>();
        for (Directions dir : Directions.values()) {
            randoms.add(dir);
        }
        Collections.shuffle(randoms);

        return randoms.toArray(new Directions[Directions.values().length]);
    }

    private void exploreNeighbors(HashMap<Directions, Node> dir) {
        if (dir.containsKey(Directions.NORTH)) {
            Node node = dir.get(Directions.NORTH);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow() + 1][node.getCol()].setWall(false);
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(10);
                dfs(node.getRow(), node.getCol());
            }
        } else if (dir.containsKey(Directions.SOUTH)) {
            Node node = dir.get(Directions.SOUTH);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow() - 1][node.getCol()].setWall(false);
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(10);
                dfs(node.getRow(), node.getCol());
            }
        } else if (dir.containsKey(Directions.WEST)) {
            Node node = dir.get(Directions.WEST);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow()][node.getCol() + 1].setWall(false);
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(10);
                dfs(node.getRow(), node.getCol());
            }
        } else if (dir.containsKey(Directions.EAST)) {
            Node node = dir.get(Directions.EAST);
            if (!marked[node.getRow()][node.getCol()]) {
                this.maze.maze[node.getRow()][node.getCol() - 1].setWall(false);
                this.maze.maze[node.getRow()][node.getCol()].setWall(false);
                this.maze.update();
                Delay.delay(10);
                dfs(node.getRow(), node.getCol());
            }
        }
//        if (directions[i] == Directions.NORTH) {
//            if (row-2 >= 0 && !marked[row - 2][col]) {
//                this.maze.maze[row-1][col].setWall(false);
//                this.maze.maze[row-2][col].setWall(false);
//                this.maze.update();
//                Delay.delay(10);
//                dfs(row - 2, col);
//            }
//        }
//        else if (directions[i] == Directions.SOUTH) {
//            if (row+2 < Maze.MAX_HEIGHT && !marked[row + 2][col]) {
//                this.maze.maze[row+1][col].setWall(false);
//                this.maze.maze[row+2][col].setWall(false);
//                this.maze.update();
//                Delay.delay(10);
//                dfs(row+2, col);
//            }
//        }
//        else if (directions[i] == Directions.WEST) {
//            if (col-2 >= 0 && !marked[row][col-2]) {
//                this.maze.maze[row][col-1].setWall(false);
//                this.maze.maze[row][col-2].setWall(false);
//                this.maze.update();
//                Delay.delay(10);
//                dfs(row, col-2);
//            }
//        }
//        else if (directions[i] == Directions.EAST) {
//            if (col+2 < Maze.MAX_WIDTH && !marked[row][col+2]) {
//                this.maze.maze[row][col+1].setWall(false);
//                this.maze.maze[row][col+2].setWall(false);
//                this.maze.update();
//                Delay.delay(10);
//                dfs(row, col+2);
//            }
//        }
    }

}