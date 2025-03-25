import java.util.List;
import java.util.Map;

public interface RotatingCells {
    static int[][][] rotateCellToCoords(int[][][] cube, List<int[]> targetCoords, Map<int[], Integer> selectedCells){
        int[][][] rotatedCube = new int[cube.length][cube[0].length][cube[0][0].length];
        Map<int[], Integer> newSelectedCells = SelectingCells.selectCellByCoords(cube, targetCoords);
        for (int[] coord : selectedCells.keySet()){
            int x = coord[0];
            int y = coord[1];
            int z = coord[2];
            rotatedCube[y][cube.length - 1 - z][x] = cube[x][y][z];
            newSelectedCells.put(new int[]{y, cube.length - 1 - z, x}, selectedCells.get(coord));
        }
        return rotatedCube;
    }
}
