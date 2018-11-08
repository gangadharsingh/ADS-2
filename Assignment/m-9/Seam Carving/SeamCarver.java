import java.awt.Color;
import java.util.*;
public class SeamCarver {
	// create a seam carver object based on the given picture
	double[][] energy;
	int width;
	int height;
	Picture pic;
	public SeamCarver(Picture picture) {
		pic = picture;
		width = picture.width();
		height = picture.height();
		energy = new double[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i == 0 || j == 0 || i == width-1 || j == height-1) {
					energy[i][j] = 1000.0;
				} else {
					energy[i][j] = Math.sqrt(vertSum(i, j)+horizSum(i, j));
				}
			}
		}
	}
	// current picture
	public Picture picture() {
		return pic;
	}
	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return energy[x][y];
	}
	int getred(int x, int y) {
		return pic.get(x, y).getRed();
	}
	int getgreen(int x, int y) {
		return pic.get(x, y).getGreen();
	}
	int getblue(int x, int y) {
		return pic.get(x, y).getBlue();
	}
	public int vertSum(int x, int y) {
        // System.out.println("VERTical");
        Color a = pic.get(x - 1, y);
        Color b = pic.get(x + 1, y);
        int red = b.getRed() - a.getRed();
        int green = b.getGreen() - a.getGreen();
        int blue = b.getBlue() - a.getBlue();
        // int sum = (red ^ 2 + green ^ 2 + blue ^ 2);
        // System.out.println(sum);
        return (red*red + green*green + blue*blue);
    }
    public int horizSum(int x, int y) {
        // System.out.println("horizontal");
        Color a = pic.get(x, y - 1);
        Color b = pic.get(x, y + 1);
        int red = b.getRed() - a.getRed();
        int green = b.getGreen() - a.getGreen();
        int blue = b.getBlue() - a.getBlue();
        // int sum = (red ^ 2 + green ^ 2 + blue ^ 2);
        // System.out.println(sum);
        return (red*red + green*green + blue*blue);
    }
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}