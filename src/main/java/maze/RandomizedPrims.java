package maze;

import pathfinding.Node;
import utils.MazeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomizedPrims {

    private Maze maze;
    private Node startNode;
    private Node endNode;
    private boolean[][] marked;
    private List<HashMap<MazeUtils.Directions, Node>> listOfWalls;
    private Random rand = new Random();

    public RandomizedPrims(Maze maze, Node startNode, Node endNode) {
        this.maze = maze;
        this.startNode = startNode;
        this.endNode = endNode;
        marked = new boolean[Maze.MAX_HEIGHT][Maze.MAX_WIDTH];
        listOfWalls = new ArrayList<>();
    }

    public void generateMaze() {
        // Pick a cell, mark it as part of the maze.
        marked[this.startNode.getRow()][this.startNode.getCol()] = true;

        // Add the walls of the cell to the wall list.
        addWalls(this.startNode.getRow(), this.startNode.getCol());

        // While there are walls in the list:
        while (!listOfWalls.isEmpty()) {


            // Pick a random wall from the list
            HashMap<MazeUtils.Directions, Node> randomWallMap = listOfWalls.get(rand.nextInt(listOfWalls.size()));

            System.out.println(listOfWalls.size());

            if (randomWallMap.containsKey(MazeUtils.Directions.NORTH)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.NORTH);

                if (!marked[randNode.getRow()-1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()-1][randNode.getCol()].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()-1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()-1, randNode.getCol());
                    removeWall(randomWallMap);
                }
                else if (!marked[randNode.getRow()+1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()+1][randNode.getCol()].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()+1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()+1, randNode.getCol());
                    removeWall(randomWallMap);
                }
//                removeWall(randomWallMap);
            }
            else if (randomWallMap.containsKey(MazeUtils.Directions.SOUTH)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.SOUTH);

                if (!marked[randNode.getRow()-1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()-1][randNode.getCol()].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()-1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()-1, randNode.getCol());
                    removeWall(randomWallMap);
                }
                else if (!marked[randNode.getRow()+1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()+1][randNode.getCol()].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()+1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()+1, randNode.getCol());
                    removeWall(randomWallMap);
                }
//                removeWall(randomWallMap);
            }
            else if (randomWallMap.containsKey(MazeUtils.Directions.EAST)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.EAST);

                if (!marked[randNode.getRow()][randNode.getCol()-1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()-1].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()][randNode.getCol()-1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()-1);
                    removeWall(randomWallMap);
                }
                else if (!marked[randNode.getRow()][randNode.getCol()+1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()+1].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()][randNode.getCol()+1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()+1);
                    removeWall(randomWallMap);
                }
//                removeWall(randomWallMap);
            }
            else if (randomWallMap.containsKey(MazeUtils.Directions.WEST)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.WEST);

                if (!marked[randNode.getRow()][randNode.getCol()-1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()-1].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()][randNode.getCol()-1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()-1);
                    removeWall(randomWallMap);
                }
                else if (!marked[randNode.getRow()][randNode.getCol()+1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()+1].setWall(false);
                    this.maze.update(15);
                    marked[randNode.getRow()][randNode.getCol()+1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()+1);
                    removeWall(randomWallMap);
                }
//                removeWall(randomWallMap);

            }

            // If only one of the two cells that the wall divides is visited then:
//            if (randomWallMap.containsKey(MazeUtils.Directions.NORTH)) {
//                Node randNode = randomWallMap.get(MazeUtils.Directions.NORTH);
//                if (!marked[randNode.getRow()][randNode.getCol()]) {
//
//                    System.out.println("North");
////                        Make the (random) wall a passage and mark the unvisited cell as part of the maze.
////                        Add the neighboring walls of the cell to the wall list.
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.maze[randNode.getRow()+1][randNode.getCol()].setWall(false);
//                    this.maze.update(15);
//                    marked[randNode.getRow()][randNode.getCol()] = true;
//                    addWalls(randNode.getRow(), randNode.getCol());
//                    removeWall(randomWallMap);
//                }
//                else if (!marked[randNode.getRow()+1][randNode.getCol()]) {
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.maze[randNode.getRow()+1][randNode.getCol()].setWall(false);
//                    this.maze.update(15);
//                    marked[randNode.getRow()+1][randNode.getCol()] = true;
//                    addWalls(randNode.getRow()+1, randNode.getCol());
//                    removeWall(randomWallMap);
//                }
//                removeWall(randomWallMap);
//            }
//            else if (randomWallMap.containsKey(MazeUtils.Directions.SOUTH)) {
//                System.out.println("S");
//                Node randNode = randomWallMap.get(MazeUtils.Directions.SOUTH);
//                if (!marked[randNode.getRow()][randNode.getCol()]) {
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.update(15);
//                    marked[randNode.getRow()][randNode.getCol()] = true;
//                    addWalls(randNode.getRow(), randNode.getCol());
//                    removeWall(randomWallMap);
//                }
//                else if (!marked[randNode.getRow()-1][randNode.getCol()]) {
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.maze[randNode.getRow()-1][randNode.getCol()].setWall(false);
//                    this.maze.update(15);
//                    marked[randNode.getRow()-1][randNode.getCol()] = true;
//                    addWalls(randNode.getRow()-1, randNode.getCol());
//                    removeWall(randomWallMap);
//                }
//                removeWall(randomWallMap);
//            }
//            else if (randomWallMap.containsKey(MazeUtils.Directions.WEST)) {
//                System.out.println("W");
//                Node randNode = randomWallMap.get(MazeUtils.Directions.WEST);
//                if (!marked[randNode.getRow()][randNode.getCol()]) {
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.update(15);
//                    marked[randNode.getRow()][randNode.getCol()] = true;
//                    addWalls(randNode.getRow(), randNode.getCol());
//                    removeWall(randomWallMap);
//                }
//                else if (!marked[randNode.getRow()][randNode.getCol()+1]) {
//                    marked[randNode.getRow()][randNode.getCol()+1] = true;
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.maze[randNode.getRow()][randNode.getCol()+1].setWall(false);
//                    this.maze.update(15);
//                    addWalls(randNode.getRow(), randNode.getCol()+1);
//                    removeWall(randomWallMap);
//                }
//                removeWall(randomWallMap);
//            }
//            else if (randomWallMap.containsKey(MazeUtils.Directions.EAST)) {
//                System.out.println("E");
//                Node randNode = randomWallMap.get(MazeUtils.Directions.EAST);
//                if (!marked[randNode.getRow()][randNode.getCol()-1]) {
//                    marked[randNode.getRow()][randNode.getCol()-1] = true;
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.maze[randNode.getRow()][randNode.getCol()-1].setWall(false);
//                    this.maze.update(15);
//                    addWalls(randNode.getRow(), randNode.getCol()-1);
//                    removeWall(randomWallMap);
//                }
//                else if (!marked[randNode.getRow()][randNode.getCol()]) {
//                    marked[randNode.getRow()][randNode.getCol()] = true;
//                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
//                    this.maze.update(15);
//                    addWalls(randNode.getRow(), randNode.getCol());
//                    removeWall(randomWallMap);
//                }
//                removeWall(randomWallMap);
//            }

            //    Make the (random) wall a passage and mark the unvisited cell as part of the maze.
            //    Add the neighboring walls of the cell to the wall list.

            // Remove the (random) wall from the list.
        }


    }

    private void removeWall(HashMap<MazeUtils.Directions, Node> randMap) {
        listOfWalls.removeIf(map -> map.equals(randMap));
    }


    private void addWalls(int row, int col) {

        if (row - 1 >= 0) {
            HashMap<MazeUtils.Directions, Node> map = new HashMap<>();
            if (this.maze.maze[row-1][col].isWall()) {
                map.put(MazeUtils.Directions.NORTH, this.maze.maze[row - 1][col]);
                listOfWalls.add(map);
            }

        }
        if (row + 1 < Maze.MAX_HEIGHT) {
            HashMap<MazeUtils.Directions, Node> map = new HashMap<>();
            if (this.maze.maze[row+1][col].isWall()) {
                map.put(MazeUtils.Directions.SOUTH, this.maze.maze[row + 1][col]);
                listOfWalls.add(map);
            }

        }
        if (col - 1 >= 0) {
            HashMap<MazeUtils.Directions, Node> map = new HashMap<>();
            if (this.maze.maze[row][col-1].isWall()) {
                map.put(MazeUtils.Directions.WEST, this.maze.maze[row][col - 1]);
                listOfWalls.add(map);
            }

        }
        if (col + 1 < Maze.MAX_WIDTH) {
            HashMap<MazeUtils.Directions, Node> map = new HashMap<>();
            if (this.maze.maze[row][col+1].isWall()) {
                map.put(MazeUtils.Directions.EAST, this.maze.maze[row][col + 1]);
                listOfWalls.add(map);
            }

        }

    }
}
