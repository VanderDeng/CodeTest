package com.vcg.zombie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Position {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "Position{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public Position move(Position offset, int gridSize) {
        return new Position(correct(x + offset.x, gridSize),
            correct(y + offset.y, gridSize));
    }

    private int correct(int position, int gridSize) {
        return position >= gridSize ? 0 : position;
    }
}
