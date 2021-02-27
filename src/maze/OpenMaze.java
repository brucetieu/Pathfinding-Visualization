package maze;

import utils.Delay;

/**
 * Build an open maze with no walls.
 */
public class OpenMaze {

    private Maze maze;

    public OpenMaze(Maze maze) {
        this.maze = maze;
    }

    public void generateMaze() {
        for (int row = 0; row < this.maze.getMaze().length; row++) {
            for (int col = 0; col < this.maze.getMaze()[0].length; col++) {
                Delay.delay(5);
                this.maze.update();
                this.maze.getMaze()[row][col].setWall(false);
            }
        }
    }

}
