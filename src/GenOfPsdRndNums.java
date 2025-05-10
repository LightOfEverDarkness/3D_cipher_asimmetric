public class GenOfPsdRndNums {
    private static final int[] initialNums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//    private static int[] permutatedNums;
    
    private static int key_E;
    private static int key_D;
    private static int num_N;
    public GenOfPsdRndNums(int key_E, int key_D, int num_N){
        GenOfPsdRndNums.key_E = key_E;
        GenOfPsdRndNums.key_D = key_D;
        GenOfPsdRndNums.num_N = num_N;
    }
    private static int[] getInitialNums() {
        return initialNums;
    }
    private static int getKey_E() {
        return key_E;
    }
    private static void setKey_E(int key_E) {
        GenOfPsdRndNums.key_E = key_E;
    }
    private static int getKey_D() {
        return key_D;
    }
    private static void setKey_D(int key_D) {
        GenOfPsdRndNums.key_D = key_D;
    }
    private static int getNum_N() {
        return num_N;
    }
    private static void setNum_N(int num_N) {
        GenOfPsdRndNums.num_N = num_N;
    }
    
    private static String keyE_proccessing(int key_E){
        String[] kEsymb = String.valueOf(key_E).split("");

        for (int kEindex = 0; kEindex <= kEsymb.length; kEindex++){
            if (Integer.parseInt(kEsymb[kEindex]) % 2 == 0){
                if (!firstCondition() && !secondCondition()){

                }
                if (firstCondition()){

                }
                else if (secondCondition()) {

                }
            }
            else if (Integer.parseInt(kEsymb[kEindex]) % 2 == 1){
                if (!firstCondition() && !secondCondition()){

                }
                if (secondCondition()){

                }
                else if (firstCondition()) {

                }
            }
        }
        return "";
    }

    private static boolean secondCondition() {
        return true;
    }

    private static boolean firstCondition() {
        return true;
    }

    private static String replacementRule(){
        return "";
    }
}
