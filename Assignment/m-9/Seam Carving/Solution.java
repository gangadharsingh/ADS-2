import java.util.Scanner;
import java.util.Arrays;
/**
 * Solution class.
 **/
final class Solution {
    /**
     * default constructor.
     **/
    private Solution() {

    }
    /**
     * printEnergies.
     * @param fileName String.
     **/
    public static void printEnergies(final String fileName) {
        Picture picture = new Picture(fileName);
        System.out.printf("image is %d pixels wide by %d pixels high.\n",
         picture.width(), picture.height());

        SeamCarver sc = new SeamCarver(picture);

        System.out.printf("Printing energy calculated for each pixel.\n");

        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++) {
                System.out.printf("%9.0f ", sc.energy(col, row));
            }
            System.out.println();
        }
    }
    /**
     * printSeam method.
     * @param carver seamCarver.
     * @param seam int[].
     * @param direction boolean.
     */
    public static void printSeam(final SeamCarver carver,
     final int[] seam, final boolean direction) {
        double totalSeamEnergy = 0.0;
        boolean t = true;
        boolean f = false;

        for (int row = 0; row < carver.height(); row++) {
            for (int col = 0; col < carver.width(); col++) {
                double energy = carver.energy(col, row);
                String marker = " ";
                if ((direction == t && row == seam[col])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                } else if ((direction == f   && col == seam[row])) {
                    marker = "*";
                    totalSeamEnergy += energy;
                }
                System.out.printf("%7.2f%s ", energy, marker);
            }
            System.out.println();
        }
        System.out.printf("Total energy = %f\n", totalSeamEnergy);
        System.out.println();
        System.out.println();
    }
/**
 * main method.
 * @param args Strings.
 */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();
        SeamCarver seamCarver = null;
        try {
            switch (cases) {
            case "width":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(new Picture(
                        "/Files/" + file));
                    System.out.println(seamCarver.width());
                }
                break;

            case "height":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(
                        new Picture("/Files/" + file));
                    System.out.println(seamCarver.height());
                }
                break;

            case "energy":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    printEnergies("/Files/" + file);
                }
                break;

            case "findVerticalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(new Picture("/Files/" + file));
                    System.out.println(Arrays.toString(
                        seamCarver.findVerticalSeam()));
                }
                break;

            case "findHorizontalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(new Picture("/Files/" + file));
                    System.out.println(Arrays.toString(
                        seamCarver.findHorizontalSeam()));
                }
                break;

            case "removeVerticalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(new Picture("/Files/" + file));
                    int[] verticalSeam = seamCarver.findVerticalSeam();
                    seamCarver.removeVerticalSeam(verticalSeam);
                    printSeam(seamCarver, verticalSeam, false);
                }
                break;

            case "removeHorizontalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(new Picture("/Files/" + file));
                    seamCarver.removeHorizontalSeam(
                        seamCarver.findHorizontalSeam());
                    int[] horizontalSeam = seamCarver.findHorizontalSeam();
                    seamCarver.removeHorizontalSeam(horizontalSeam);
                    printSeam(seamCarver, horizontalSeam, true);
                }
                break;

            case "removeHorizontalSeam removeVerticalSeam":
                while (scan.hasNextLine()) {
                    String file = scan.nextLine();
                    seamCarver = new SeamCarver(new Picture("/Files/" + file));
                    int[] horizontalSeam = seamCarver.findHorizontalSeam();
                    seamCarver.removeHorizontalSeam(horizontalSeam);
                    int[] verticalSeam = seamCarver.findVerticalSeam();
                    seamCarver.removeVerticalSeam(verticalSeam);
                    printEnergies("/Files/" + file);
                }
                break;

            default:
                seamCarver = new SeamCarver(null);
                break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
