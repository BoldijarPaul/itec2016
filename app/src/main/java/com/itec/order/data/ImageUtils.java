package com.itec.order.data;

import java.util.Random;

/**
 * Created by Paul on 5/14/2016.
 */
public class ImageUtils {
    private static String[] HARDCODED_IMAGES = new String[]
            {
                    "http://www.telegraph.co.uk/content/dam/food-and-drink/2016/02/16/juicespair-large_trans++eo_i_u9APj8RuoebjoAHt0k9u7HhRJvuo-ZLenGRumA.jpg",
                    "http://cdn1.bostonmagazine.com/wp-content/uploads/2013/12/juices_main.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/6/65/Absolut_vodka.jpg",
                    "http://www.newhealthadvisor.com/images/1HT00604/Glass-of-milk-2009.png",
                    "http://media.dcnews.ro/image/201604/w670/fanta_squeeze_25489700.JPG"
            };

    private static Random random = new Random();

    public static String getImageForInt(int value) {
        random.setSeed(value);
        return HARDCODED_IMAGES[random.nextInt(HARDCODED_IMAGES.length)];
    }
}
