import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Rotations extends CubeED implements RotatingCells {
    public static double sigmoid(double t) {
        return 1 / (1 + Math.pow(Math.E, (-1 * t)));
    }

    public static Character rotDir(double number){
        return sigmoid(number)<0 ? 'R' : 'L';
    }

    public static String[][][] rotation(String[][][] cube, char c, List<List<String>> targetCoords){
        Map<List<String>, String> selectedCellsForRotate = selectCellByCoords(cube, cellsToSelect());
        String[][][] rotatedCube = new String[cube.length][cube[0].length][cube[0][0].length];
        Map<List<String>, String> rotatedCells = selectCellByCoords(cube, targetCoords);
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                System.arraycopy(cube[i][j], 0, rotatedCube[i][j], 0, cube[i][j].length);
            }
        }
        for (List<String> coord : selectedCellsForRotate.keySet()){
            int floor = Integer.parseInt(coord.get(0));
            int line = Integer.parseInt(coord.get(1));
            int cell = Integer.parseInt(coord.get(2));
            if (c == 'R'){
                rotatedCube[line][cell][floor] = cube[floor][line][cell];
                rotatedCells.put(Arrays.asList(String.valueOf(line), String.valueOf(cell), String.valueOf(floor)), selectedCellsForRotate.get(coord));
            }
            else if (c == 'L'){
                rotatedCube[cell][floor][line] = cube[floor][line][cell];
                rotatedCells.put(Arrays.asList(String.valueOf(cell), String.valueOf(floor), String.valueOf(line)), selectedCellsForRotate.get(coord));
            }
        }
        return rotatedCube;
    }

//    static String[][][] rotateCellsToCoords(String[][][] cube, List<int[]> targetCoords, Map<int[], Integer> selectedCells){
//        String[][][] rotatedCube = new int[cube.length][cube[0].length][cube[0][0].length];
//        Map<int[], Integer> newSelectedCells = SelectingCells.selectCellByCoords(cube, targetCoords);
//        for (int[] coord : selectedCells.keySet()){
//            int x = coord[0];
//            int y = coord[1];
//            int z = coord[2];
//            rotatedCube[y][cube.length - 1 - z][x] = cube[x][y][z];
//            newSelectedCells.put(new int[]{y, cube.length - 1 - z, x}, selectedCells.get(coord));
//        }
//        return rotatedCube;
//    }

    public static List<List<String>> cellsToSelect(){
        String filePath = "src/cellsToSelect_local_coordinate_system";
        List<List<String>> coords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String coordsLine;
            while ((coordsLine = reader.readLine()) != null) {
                List<String> coord = Arrays.stream(coordsLine.split(","))
                        .map(String::trim)
                        .toList();
                coords.add(coord);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключений
        }
        return coords;
    }

    public static Map<List<String>, String> selectCellByCoords(String[][][] cube, List<List<String>> coords){
        Map<List<String>, String> selectedCells = new HashMap<>(coords.size());
        List<String> cells = new ArrayList<>(coords.size());
        for (List<String> coord : coords){
            cells.add(cube[Integer.parseInt(coord.get(0))][Integer.parseInt(coord.get(1))][Integer.parseInt(coord.get(2))]);
            selectedCells.put(coord, cells.getLast());
        }
        return selectedCells;
    }
}
