package com.yidong.jon.widget.water;

import java.util.Random;

/**
 * Created by Administrator on 2016/10/8.
 */
public class MathHelper {
    public static Random rand = new Random();
    public static float randomRange(float min, float max) {


        int randomNum = rand.nextInt(((int) max - (int) min) + 1) + (int) min;

        return randomNum;
    }
}
