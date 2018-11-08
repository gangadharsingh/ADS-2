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
		calcEnergy();
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
	int vertSum(int x, int y) {
		int red1 = getred(x-1, y);
		int green1 = getgreen(x-1, y);
		int blue1 = getblue(x-1, y);
		int red2 = getred(x+1, y);
		int green2 = getgreen(x+1, y);
		int blue2 = getblue(x+1, y);
		int sum = (red2 - red1)^2 + (green2 - green1)^2 + (blue2 - blue1)^2;
		return sum;
	}
	int horizSum(int x, int y) {
		int red1 = getred(x, y-1);
		int green1 = getgreen(x, y-1);
		int blue1 = getblue(x, y-1);
		int red2 = getred(x, y+1);
		int green2 = getgreen(x, y+1);
		int blue2 = getblue(x, y+1);
		int sum = (red2 - red1)^2 + (green2 - green1)^2 + (blue2 - blue1)^2;
		return sum;
	}
	void calcEnergy() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x != 0 || y != 0 || x != width-1 || y != height-1) {
					energy[x][y] = Math.sqrt(vertSum(x, y)+horizSum(x, y));
				}
			}
		}
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