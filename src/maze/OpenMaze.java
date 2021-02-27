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
        for (int row = 0; row < Maze.MAX_HEIGHT; row++) {
            for (int col = 0; col < Maze.MAX_WIDTH; col++) {
                Delay.delay(5);
                this.maze.update();
                this.maze.getMaze()[row][col].setWall(false);
            }
        }
    }

}
