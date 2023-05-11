import java.util.Arrays;

public class FenvicTree {
    private int[] tree;
    private int[] sourceArray;
    public int COUNT_OF_OPERATIONS = 0;

    public FenvicTree(int[] sourceArray){
        getTree(sourceArray);
    }

    private void getTree(int[] sourceArray){
        this.sourceArray = sourceArray;
        this.tree = new int[sourceArray.length];
        for (int i = 0; i < sourceArray.length; i++){
            tree[i] = 0;
        }
        for (int i = 1; i <= sourceArray.length; i++) {
            inc(i, sourceArray[i-1]);
        }
    }

//    private int[] getTree(int[] sourceArray){
//        int[] result = new int[sourceArray.length];
//        this.sourceArray = sourceArray;
//        for (int i = 0; i < sourceArray.length; i++){
//            int p = i & (i+1);
//            result[i] = 0;
//            for (int j = p; j <= i; j++) {
//                COUNT_OF_OPERATIONS++;
//                result[i] += sourceArray[j];
//            }
//        }
//        return result;
//    }

    public void deleteElement(int indx){
        if(indx < 1 || indx >= tree.length){
            throw new IndexOutOfBoundsException("this index does not exist. Pass in an int argument from 1 to " + tree.length);
        }
        indx = indx-1;
        int[] array = new int[tree.length-1];
        int j = 0;
        for (int i = 0; i < tree.length; i++) {
            if(i != indx){
                array[j] = sourceArray[i];
                j++;
            }
        }
        getTree(array);
        sourceArray = array;
    }

    // function that return element at current index
    public int getElement(int indx){
        return getSum(indx, indx);
    }

    // function that returns sum from l to r inclusive
    public int getSum(int l, int r){

        return getSum(r) - getSum(l-1);
    }

    // function that returns sum from 0 to r inclusive
    public int getSum(int r){
        r = r-1;
        int i = r;
        int result = 0;
        while (i >= 0){
            COUNT_OF_OPERATIONS++;
            result += tree[i];
            i = (i & (i+1)) - 1;
        }
        return result;
    }

    // increase the number of the array cell indx-1 by the alpha value
    public void inc(int indx, int alpha){
        indx = indx - 1;
        sourceArray[indx] += alpha;
        while (indx < tree.length){
            tree[indx] += alpha;
            indx = indx | (indx + 1);
            COUNT_OF_OPERATIONS++;
        }
    }

    public int length(){
        return tree.length;
    }

    // Position indx-1 is set to value
    public void set(int indx, int value){
        inc(indx, value-getElement(indx));
    }

    @Override
    public String toString() {
        return "FenvicTree{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }

    public static void main(String[] args) {
        FenvicTree fenvicTree = new FenvicTree(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        System.out.println(fenvicTree);
    }
}
