package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage img = new APImage("/Users/username/Desktop/untitled folder 5/java vscode/Year-11-CS-Unit-9-2D-Arrays-Lab-Image-Manipulation/cyberpunk2077.jpg");
        // img.draw();
        rotateImage("/Users/username/Desktop/untitled folder 5/java vscode/Year-11-CS-Unit-9-2D-Arrays-Lab-Image-Manipulation/cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Pixel pixel = img.getPixel(x, y);
                int avg = getAverageColour(pixel);
                pixel.setRed(avg); pixel.setBlue(avg); pixel.setGreen(avg);
                img.setPixel(x, y, pixel);
            }
        }
        img.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getBlue() + pixel.getGreen() + pixel.getRed())/3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Pixel pixel = img.getPixel(x, y);
                if(getAverageColour(pixel) < 128){
                    pixel.setBlue(0); pixel.setGreen(0); pixel.setRed(0);
                }
                else{
                    pixel.setBlue(255); pixel.setGreen(255); pixel.setRed(255);
                }
                img.setPixel(x, y, pixel);
            }
        }
        img.draw();

    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage img = new APImage(pathToFile);
        APImage clone = img.clone();
        for(int x = img.getWidth()-1; x > 0; x--){
            for(int y = 0; y < img.getHeight()-1; y++){
                int avgThis = getAverageColour(img.getPixel(x, y));
                int avgDown = getAverageColour(img.getPixel(x, y+1));
                int avgLeft = getAverageColour(img.getPixel(x-1, y));
                if(Math.abs(avgThis-avgDown) > threshold || Math.abs(avgThis-avgLeft) > threshold){
                    clone.setPixel(x, y, new Pixel(0, 0, 0));
                }
                else{
                    clone.setPixel(x, y, new Pixel(255, 255, 255));
                }
            }
        }
        clone.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage clone = image.clone();
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                clone.setPixel(x, y, image.getPixel(image.getWidth()-x-1, y));
            }
        }
        clone.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        APImage clone = new APImage(img.getHeight(), img.getWidth());
        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                clone.setPixel(img.getHeight()-y-1, x, img.getPixel(x, y));
            }
        }
        clone.draw();
    }

}
