package orderedDictionaryADT;

import java.util.ArrayList;
import java.util.Random;

public class RunningTimeTestWhole {
    public static void main(String[] args) {

        int[] operationTimes = {20, 40, 80, 160, 320,
                               640, 1280, 2560, 5120, 10240,
                               20480, 40960, 81920, 163840};  // number of operation times;

//        getAvlTreeInsertTime(operationTimes);                 //ok!
//        getSkipListInsertTime(operationTimes);                //ok!
//        getSkipListClosestKeyAfterTime(operationTimes);       //ok!
//        getSkipListFindElementTime(operationTimes);           //ok!
//        getSkipListRemoveTime(operationTimes);                //ok!
//        getAvlTreeFindElementTime(operationTimes);            //ok!
//        getAvlTreeRemoveTime(operationTimes);                 //ok!
        //getAvlTreeClosestKeyAfterTime(operationTimes);        // not ok!
        System.out.println("\n\nAll data showing below are average number that repeated three times");
        System.out.println("The average running time for AvlTree and SkipList: \n");
        System.out.println("The program running time is huge, please wait patiently... ");
        System.out.printf("%-30s%-20s%-20s\n","Insert Operation(Times)", "AvlTree(ms)", "SkipList(ms)");
        for (int i = 0; i <= operationTimes.length - 1; i++) {
            System.out.printf("%-32d%-22.2f%-15.2f\n", operationTimes[i],
                    getAvlTreeInsertTime(operationTimes)[i],
                    getSkipListInsertTime(operationTimes)[i]);
        }
        System.out.printf("%-30s%-20s%-20s\n","Remove Operation(Times)", "AvlTree(ms)", "SkipList(ms)");
        for (int i = 0; i <= operationTimes.length - 1; i++) {
            System.out.printf("%-32d%-22.2f%-15.2f\n", operationTimes[i],
                    getAvlTreeRemoveTime(operationTimes)[i],
                    getSkipListRemoveTime(operationTimes)[i]);
        }
        System.out.printf("%-30s%-20s%-20s\n","FindElement Operation(Times)", "AvlTree(ms)", "SkipList(ms)");
        for (int i = 0; i <= operationTimes.length - 1; i++) {
            System.out.printf("%-32d%-22.2f%-15.2f\n", operationTimes[i],
                    getAvlTreeFindElementTime(operationTimes)[i],
                    getSkipListFindElementTime(operationTimes)[i]);
        }
        System.out.printf("%-30s%-20s%-20s\n","ClosestKeyAfter Operation(Times)", "AvlTree(ms)", "SkipList(ms)");
        for (int i = 0; i <= operationTimes.length - 1; i++) {
            System.out.printf("%-32d%-22.2f%-15.2f\n", operationTimes[i],
                    getAvlTreeClosestKeyAfterTime(operationTimes)[i],
                    getSkipListClosestKeyAfterTime(operationTimes)[i]);
        }
    }

    public static double[] getAvlTreeClosestKeyAfterTime (int[] operationTimes) {
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                AVLTreeNode root = new AVLTreeNode(327690, 22);     // create a new tree for each loop (5 times)
                ArrayList<Integer> newList = new ArrayList();         // the constructor need newList, this method used in closestKeyAfter only.
                OrderedDictionaryAvlTree tree = new OrderedDictionaryAvlTree(root, newList);
                for (int j = 0; j <= operationTimes[i]; j++) {
                    tree.insertElement(j, 2);
                }
                double startTime = System.nanoTime();
                for (int j = operationTimes[i]; j >= 0; j--) {
                    tree.closestAfter(j);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime) / 100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / (matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }

    public static double[] getAvlTreeFindElementTime (int[] operationTimes) {
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                AVLTreeNode root = new AVLTreeNode(327690, 22);     // create a new tree for each loop (5 times)
                ArrayList<Integer> newList = new ArrayList();         // the constructor need newList, this method used in closestKeyAfter only.
                OrderedDictionaryAvlTree tree = new OrderedDictionaryAvlTree(root, newList);
                for (int j = 0; j <= operationTimes[i]; j++) {
                    tree.insertElement(j, 2);
                }
                double startTime = System.nanoTime();
                for (int j = operationTimes[i]; j >= 0; j--) {
                    tree.find(root,j);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime) / 100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / (matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }

    public static double[] getSkipListFindElementTime (int[] operationTimes) {
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                OrderedDictionarySkipList skl = new OrderedDictionarySkipList();
                for (int j = 0; j <= operationTimes[i]; j++) {
                    skl.insertElement(j, 2);
                }
                double startTime = System.nanoTime();
                for (int j = operationTimes[i]; j >= 0; j--) {
                    skl.findElement(j);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime) / 100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / (matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }

    public static double[] getSkipListClosestKeyAfterTime (int[] operationTimes) {
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                OrderedDictionarySkipList skl = new OrderedDictionarySkipList();
                for (int j = 0; j <= operationTimes[i]; j++) {
                    skl.insertElement(j, 2);
                }
                double startTime = System.nanoTime();
                for (int j = operationTimes[i]; j >= 0; j--) {
                    skl.closestKeyAfter(j);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime) / 100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / (matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }

    public static double[] getSkipListRemoveTime (int[] operationTimes) {
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                OrderedDictionarySkipList skl = new OrderedDictionarySkipList();
                for (int j = 0; j <= operationTimes[i]; j++) {
                    skl.insertElement(j, 2);
                }
                double startTime = System.nanoTime();
                for (int j = operationTimes[i]; j >= 0; j--) {
                    skl.removeElement(j);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime) / 100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length - 1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum / (matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }


    public static double[] getAvlTreeRemoveTime (int[] operationTimes) {
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                AVLTreeNode root = new AVLTreeNode(327690, 22);     // create a new tree for each loop (5 times)
                ArrayList<Integer> newList = new ArrayList();         // the constructor need newList, this method used in closestKeyAfter only.
                OrderedDictionaryAvlTree tree = new OrderedDictionaryAvlTree(root, newList);
                for (int j = 0; j <= operationTimes[i]; j++) {
                    tree.insertElement(j,2);
                }
                double startTime = System.nanoTime();
                for (int j = operationTimes[i]; j >= 0; j--) {
                    tree.remove(root,j);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime)/100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length -1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum /(matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }

    public static double[] getSkipListInsertTime (int[] operationTimes) {
        Random random = new Random();
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {
            for (int i = 0; i <= operationTimes.length - 1; i++) {
                OrderedDictionarySkipList skl = new OrderedDictionarySkipList();
                double startTime = System.nanoTime();
                for (int j = 0; j <= operationTimes[i]; j++) {
                    int randomKey = random.nextInt(1000000000) + 1;
                    skl.insertElement(randomKey,2);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime)/100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length -1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum /(matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }

    public static double[] getAvlTreeInsertTime(int[] operationTimes) {
        Random random = new Random();
        int totalTimesForAveraging = 3;
        double[][] matrix = new double[operationTimes.length][totalTimesForAveraging];
        for (int m = 0; m < totalTimesForAveraging; m++) {     //10 columns
            for (int i = 0; i <= operationTimes.length - 1; i++) {     //15 rows
                AVLTreeNode root = new AVLTreeNode(40, 22);     // create a new tree for each loop (5 times)
                ArrayList<Integer> newList = new ArrayList();         // the constructor need newList, this method used in closestKeyAfter only.
                OrderedDictionaryAvlTree tree = new OrderedDictionaryAvlTree(root, newList);
                double startTime = System.nanoTime();
                for (int j = 0; j <= operationTimes[i]; j++) {
                    int randomKey = random.nextInt(1000000000) + 1;
                    tree.insertElement(randomKey, 2);
                }
                double endTime = System.nanoTime();
                double totalTime = (endTime - startTime)/100000;
                //System.out.println(totalTime);
                matrix[i][m] = totalTime;
            }
        }
        double[] averageOutput = new double[operationTimes.length];
        for (int i = 0; i <= matrix.length - 1; i++) {
            double sum = 0;
            for (int j = 0; j <= matrix[i].length -1; j++) {
                sum += matrix[i][j];
            }
            double avg = sum /(matrix[0].length);   // sum / 10 columns;
            averageOutput[i] = avg;
        }
        //System.out.println(Arrays.toString(averageOutput));
        return averageOutput;
    }
}
