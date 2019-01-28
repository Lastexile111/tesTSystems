package ru.tsystems.pyramidBuilder;


public class Launcher {
    public static void main(String[] args) {

        int[] array1 = {2,4,3,5,6,1};
//        int[] array2 = {4,3,2,5,7,1,6};


        PyramidBuilder pb = new PyramidBuilderImpl();
        int[][] p1 = pb.build(array1);
        // TODO: Формирование вывода
        for(int i = 0; i < p1.length; i++ ){
            System.out.print("[");
            for(int j = 0; j < p1[0].length; j++ ){
                if(j+1 != p1[0].length) {
                    System.out.print(p1[i][j] + ",");
                }else{
                    System.out.print(p1[i][j]);
                }
            }
            System.out.println("]");
        }



//        int[][] p2 = pb.build(array2);
//        for(int i = 0; i < p2.length; i++ ){
//            System.out.println("[");
//            for(int j = 0; j < p2[0].length; j++ ){
//                if(j+1 != p2[0].length) {
//                    System.out.print(p2[i][j] + ",");
//                }else{
//                    System.out.print(p2[i][j]);
//                }
//            }
//            System.out.print("]");
//        }


    }
}
