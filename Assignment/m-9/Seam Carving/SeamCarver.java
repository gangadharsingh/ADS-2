import java.awt.Color;
import java.util.*;
public class SeamCarver {
    // create a seam carver object based on the given picture
    double[][] energy;
    int width;
    int height;
    Picture pic;
    public SeamCarver(final Picture picture) {
        pic = picture;
        width = picture.width();
        height = picture.height();
        energy = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
                    energy[i][j] = 1000.0;
                } else {
                    energy[i][j] = Math.sqrt(vertSum(i, j) + horizSum(i, j));
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
    public double energy(final int x, final int y) {
        return energy[x][y];
    }
    public int vertSum(final int x, final int y) {
        Color a = pic.get(x - 1, y);
        Color b = pic.get(x + 1, y);
        int red = b.getRed() - a.getRed();
        int green = b.getGreen() - a.getGreen();
        int blue = b.getBlue() - a.getBlue();
        return (red * red + green * green + blue * blue);
    }
    public int horizSum(final int x, final int y) {
        Color a = pic.get(x, y - 1);
        Color b = pic.get(x, y + 1);
        int red = b.getRed() - a.getRed();
        int green = b.getGreen() - a.getGreen();
        int blue = b.getBlue() - a.getBlue();
        return (red * red + green * green + blue * blue);
    }
    // sequence of indices for horizontal seam
   private void topologicalSort(double[][] energy) {
        int h = energy.length, w = energy[0].length;
        for (int row = 1; row < h; row++) {
            for (int col = 0; col < w; col++) {
                double temp = energy[row - 1][col];
                double min = 0;
                if (col == 0) {
                    min = temp;
                } else {
                    min = Math.min(temp, energy[row - 1][col - 1]);
                }

                if (col != (w - 1)) {
                    min = Math.min(min, energy[row - 1][col + 1]);
                } else {
                    min = min;
                }
                energy[row][col] += min;
            }
        }

    }
    private double[][] transposeGrid(double[][] energy) {
        int h = energy.length, w = energy[0].length;
        double[][] trEnergy = new double[w][h];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                trEnergy[col][row] = energy[row][col];
            }
        }
        return trEnergy;
    }

    private int[] minVerticalPath(double[][] energy) {
        int h = energy.length, w = energy[0].length;
        int[] path = new int[h];

        topologicalSort(energy);

        // find the lowest element in last row
        path[h - 1] = 0;
        for (int i = 0; i < w; i++) {
            if (energy[h - 1][i] < energy[h - 1][path[h - 1]])
                path[h - 1] = i;
        }
        // trace path back to first row
        // assuming we need the cheapest upper neighboring entry
        for (int row = h - 2; row >= 0; row--) {
            int col = path[row + 1];
            // three neighboring, priority to center
            path[row] = col;
            if (col > 0 && energy[row][col - 1] < energy[row][path[row]])
                path[row] = col - 1;
            if (col < (w - 2) && energy[row][col + 1] < energy[row][path[row]])
                path[row] = col + 1;
        }
        return path;
    }
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] transposeEnergies = transposeGrid(energy);
        return minVerticalPath(transposeEnergies);
    }
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] normalEnergies = energy;
        return minVerticalPath(normalEnergies);
    }
    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] a) {
        if (height() <= 1 || !isValid(a, width(), height() - 1))
            throw new java.lang.IllegalArgumentException("IllegalArgumentException");
        Picture pic = new Picture(width(), height() - 1);
        for (int w = 0; w < width(); w++) {
            for (int h = 0; h < a[w]; h++) {
                pic.set(w, h, pic.get(w, h));
            }

            for (int h = a[w] + 1; h < height(); h++) {
                pic.set(w, h - 1, pic.get(w, h));
            }

        }
        pic = pic;
    }

    // remove vertical seam from picture
    public void removeVerticalSeam(int[] a) {
        if (width() <= 1 || !isValid(a, height(), width()))
            throw new java.lang.IllegalArgumentException("IllegalArgumentException");
        Picture pict = new Picture(width() - 1, height());
        for (int h = 0; h < height(); h++) {
            for (int w = 0; w < a[h]; w++) {
                pict.set(w, h, pict.get(w, h));
            }
                

            for (int w = a[h] + 1; w < width(); w++) {
                pict.set(w - 1, h, pict.get(w, h));
            }

        }
        pic = pict;
    }

    // return false if two consecutive entries differ by more than 1
    private boolean isValid(int[] a, int len, int range) {
        if (a == null) {
            return false;
        }
        if (a.length != len || a[0] < 0 || a[0] > range) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (a[i] < Math.max(0, a[i - 1] - 1) || a[i] > Math.min(range, a[i - 1] + 1))
                return false;
        }
        return true;
    }
}

// import java.awt.Color;


// public class SeamCarver {
//     private static final double BORDER = 1000;
//     private Picture picture;

//     public SeamCarver(Picture picture) {
//         if (picture == null) {
//             throw new java.lang.IllegalArgumentException("picture is null");            
//         }
//         this.picture = new Picture(picture);
//     }

//     // current picture
//     public Picture picture() {
//         return this.picture;
//     }

//     // width of current picture
//     public int width() {
//         return this.picture.width();
//     }

//     // height of current picture
//     public int height() {
//         return this.picture.height();
//     }

//     // energy of pixel at column x and row y
//     public  double energy(int x, int y) {
//         int w = width() - 1, h = height() - 1;
//         if (x < 0 || x > w || y < 0 || y > h) {
//             throw new java.lang.IllegalArgumentException("IllegalArgumentException");
//         }
//         if (x == 0 || x == w ||  y == 0 || y == h) {
//             return BORDER;
//         }
//         return internalEnergy(x, y);
//     }

//     // energy of pixel at column x and row y not on boarder
//     private double internalEnergy(int x, int y) {
//         Color left = this.picture.get(x - 1, y);
//         Color right = this.picture.get(x + 1, y);
//         Color up = this.picture.get(x, y - 1);
//         Color down = this.picture.get(x, y + 1);
//         return Math.sqrt(gradient(left, right) + gradient(up, down));
//     }

//     private double gradient(Color one, Color two) {
//         double red = one.getRed() - two.getRed();
//         double green = one.getGreen() - two.getGreen();
//         double blue = one.getBlue() - two.getBlue();
//         return red * red + green * green + blue * blue;
//     }

//     private double[][] initEnergies() {
//         double[][] energies = new double[height()][width()];
//         for (int i = 0; i < height(); i++) {
//             for (int j = 0; j < width(); j++) {
//                 energies[i][j] = energy(j, i);
//             }
//         }
//         return energies;
//     }

//     // pass through an array and mark the shorthest distance from top to entry
//     private void topologicalSort(double[][] energies) {
//         int h = energies.length, w = energies[0].length;
//         for (int row = 1; row < h; row++) {
//             for (int col = 0; col < w; col++) {
//                 double temp = energies[row - 1][col];
//                 double min = 0;
//                 if (col == 0) {
//                     min = temp;
//                 } else {
//                     min = Math.min(temp, energies[row - 1][col - 1]);
//                 }

//                 if (col != (w - 1)) {
//                     min = Math.min(min, energies[row - 1][col + 1]);
//                 } else {
//                     min = min;
//                 }
//                 energies[row][col] += min;
//             }
//         }

//     }
//     private double[][] transposeGrid(double[][] energies) {
//         int h = energies.length, w = energies[0].length;
//         double[][] trEnergies = new double[w][h];
//         for (int row = 0; row < h; row++) {
//             for (int col = 0; col < w; col++) {
//                 trEnergies[col][row] = energies[row][col];
//             }
//         }
//         return trEnergies;
//     }

//     private int[] minVerticalPath(double[][] energies) {
//         int h = energies.length, w = energies[0].length;
//         int[] path = new int[h];

//         topologicalSort(energies);

//         // find the lowest element in last row
//         path[h - 1] = 0;
//         for (int i = 0; i < w; i++) {
//             if (energies[h - 1][i] < energies[h - 1][path[h - 1]])
//                 path[h - 1] = i;
//         }
//         // trace path back to first row
//         // assuming we need the cheapest upper neighboring entry
//         for (int row = h - 2; row >= 0; row--) {
//             int col = path[row + 1];
//             // three neighboring, priority to center
//             path[row] = col;
//             if (col > 0 && energies[row][col - 1] < energies[row][path[row]])
//                 path[row] = col - 1;
//             if (col < (w - 2) && energies[row][col + 1] < energies[row][path[row]])
//                 path[row] = col + 1;
//         }
//         return path;
//     }
//     // sequence of indices for horizontal seam
//     public int[] findHorizontalSeam() {
//         double[][] transposeEnergies = transposeGrid(initEnergies());
//         return minVerticalPath(transposeEnergies);
//     }
//     // sequence of indices for vertical seam
//     public int[] findVerticalSeam() {
//         double[][] normalEnergies = initEnergies();
//         return minVerticalPath(normalEnergies);
//     }
//     // remove horizontal seam from picture
//     public void removeHorizontalSeam(int[] a) {
//         if (height() <= 1 || !isValid(a, width(), height() - 1))
//             throw new java.lang.IllegalArgumentException("IllegalArgumentException");
//         Picture pic = new Picture(width(), height() - 1);
//         for (int w = 0; w < width(); w++) {
//             for (int h = 0; h < a[w]; h++) {
//                 pic.set(w, h, this.picture.get(w, h));
//             }

//             for (int h = a[w] + 1; h < height(); h++) {
//                 pic.set(w, h - 1, this.picture.get(w, h));
//             }

//         }
//         this.picture = pic;
//     }

//     // remove vertical seam from picture
//     public void removeVerticalSeam(int[] a) {
//         if (width() <= 1 || !isValid(a, height(), width()))
//             throw new java.lang.IllegalArgumentException("IllegalArgumentException");
//         Picture pic = new Picture(width() - 1, height());
//         for (int h = 0; h < height(); h++) {
//             for (int w = 0; w < a[h]; w++) {
//                 pic.set(w, h, this.picture.get(w, h));
//             }
                

//             for (int w = a[h] + 1; w < width(); w++) {
//                 pic.set(w - 1, h, this.picture.get(w, h));
//             }

//         }
//         this.picture = pic;
//     }

//     // return false if two consecutive entries differ by more than 1
//     private boolean isValid(int[] a, int len, int range) {
//         if (a == null) {
//             return false;
//         }
//         if (a.length != len || a[0] < 0 || a[0] > range) {
//             return false;
//         }
//         for (int i = 1; i < len; i++) {
//             if (a[i] < Math.max(0, a[i - 1] - 1) || a[i] > Math.min(range, a[i - 1] + 1))
//                 return false;
//         }
//         return true;
//     }
// }