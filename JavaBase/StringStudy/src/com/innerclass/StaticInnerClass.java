package com.innerclass;

/**
 * Created by Sean on 2018/5/9.
 */
public class StaticInnerClass {


    public static class  Pair{
        private int max;
        private int min;
        public Pair(int max,int min){
            this.max=max;
            this.min=min;
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }
        public static Pair minmax(int [] arr){
            int min=Integer.MAX_VALUE;
            int max=Integer.MIN_VALUE;
            for (int i : arr) {
                if (min>i) min=i;
                if (max<i) max=i;
            }
            return new Pair(min,max);
        }
    }

}
