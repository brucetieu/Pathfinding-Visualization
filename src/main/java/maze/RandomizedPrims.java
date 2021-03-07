package maze;

import maze.Maze;
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

            removeWall(randomWallMap);

            if (randomWallMap.containsKey(MazeUtils.Directions.NORTH)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.NORTH);

                if (randNode.getRow()-1 >= 0 && !marked[randNode.getRow()-1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()-1][randNode.getCol()].setWall(false);
                    marked[randNode.getRow()-1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()-1, randNode.getCol());
                }
                else if (randNode.getRow()+1 < Maze.MAX_HEIGHT && !marked[randNode.getRow()+1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()+1][randNode.getCol()].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()+1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()+1, randNode.getCol());
                }
            }
            else if (randomWallMap.containsKey(MazeUtils.Directions.SOUTH)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.SOUTH);

                if (randNode.getRow()-1 >= 0 && !marked[randNode.getRow()-1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()-1][randNode.getCol()].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()-1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()-1, randNode.getCol());
                }
                else if (randNode.getRow()+1 < Maze.MAX_HEIGHT && !marked[randNode.getRow()+1][randNode.getCol()]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()+1][randNode.getCol()].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()+1][randNode.getCol()] = true;
                    addWalls(randNode.getRow()+1, randNode.getCol());
                }
            }
            else if (randomWallMap.containsKey(MazeUtils.Directions.EAST)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.EAST);

                if (randNode.getCol()-1 >= 0 && !marked[randNode.getRow()][randNode.getCol()-1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()-1].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()][randNode.getCol()-1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()-1);
                }
                else if (randNode.getCol()+1 < Maze.MAX_WIDTH && !marked[randNode.getRow()][randNode.getCol()+1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()+1].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()][randNode.getCol()+1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()+1);
                }
            }
            else if (randomWallMap.containsKey(MazeUtils.Directions.WEST)) {
                Node randNode = randomWallMap.get(MazeUtils.Directions.WEST);

                if (randNode.getCol()-1 >= 0 && !marked[randNode.getRow()][randNode.getCol()-1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()-1].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()][randNode.getCol()-1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()-1);
                }
                else if (randNode.getCol()+1 < Maze.MAX_WIDTH && !marked[randNode.getRow()][randNode.getCol()+1]) {
                    this.maze.maze[randNode.getRow()][randNode.getCol()].setWall(false);
                    this.maze.maze[randNode.getRow()][randNode.getCol()+1].setWall(false);
                    this.maze.update();
                    marked[randNode.getRow()][randNode.getCol()+1] = true;
                    addWalls(randNode.getRow(), randNode.getCol()+1);
                }

            }


            //    Make the (random) wall a passage and mark the unvisited cell as part of the maze.
            //    Add the neighboring walls of the cell to the wall list.

            // Remove the (random) wall from the list.
        }

    }

    private void removeWall(HashMap<MazeUtils.Directions, Node> randMap) {
        listOfWalls.remove(randMap);
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