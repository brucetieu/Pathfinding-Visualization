package pathfinding;

import utils.MazeUtils;

import java.util.Objects;

public class Wall {

    private Node node;
    private MazeUtils.Directions direction;

    public Wall(MazeUtils.Directions direction, Node node) {
        this.direction = direction;
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public MazeUtils.Directions getDirection() {
        return direction;
    }

    public void setDirection(MazeUtils.Directions direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return Objects.equals(node, wall.node) && direction == wall.direction;
    }


}
