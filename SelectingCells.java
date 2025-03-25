import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SelectingCells {
    static Map<int[], Integer> selectCellByCoords(int[][][] cube, List<int[]> coords){
        Map<int[], Integer> selectedCells = new HashMap<>(coords.size());
        List<Integer> cells = new ArrayList<>(coords.size());
        for (int[] coord : coords){
            cells.add(cube[coord[0]][coord[1]][coord[2]]);
            selectedCells.put(coord, cells.getLast());
        }
        return selectedCells;
    }
}
