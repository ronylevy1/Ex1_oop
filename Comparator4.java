import java.util.Comparator;


    public class Comparator4 implements Comparator<Position> {
        private int[][][] arr = new int[11][11][37];

        public Comparator4(int[][][] arr) {
            this.arr = arr;
        }

        @Override
        public int compare(Position o1, Position o2) {
            int count = 0;
            int count2 = 0;
            for (int i = 0; i < 37; i ++){
               if(arr[o1.getY()][o1.getX()][i] != 0){
                   count ++;
               }
               if (arr[o2.getY()][o2.getX()][i] != 0){
                   count2 ++;
               }
            }

            if (count != count2){
                return count2 - count;
            }

            if(o1.getX() != o2.getX()){
                return o1.getX() - o2.getX();
            }
            return o1.getY() - o2.getY();
        }
}
