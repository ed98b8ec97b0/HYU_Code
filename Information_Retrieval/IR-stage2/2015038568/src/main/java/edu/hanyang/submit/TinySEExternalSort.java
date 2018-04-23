package edu.hanyang.submit;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import edu.hanyang.indexer.ExternalSort;

import org.apache.commons.lang3.tuple.MutableTriple;

public class TinySEExternalSort implements ExternalSort {
    List<MutableTriple<Integer, Integer, Integer>> sortedList = new ArrayList<MutableTriple<Integer, Integer, Integer>>();
    String filePrefix = "./tmp/sorting";
    String filePostfix = ".data";

    // blocksize = 1024, nblocks = 160
    public void sort(String infile, String outfile, String tmpdir, int blocksize, int nblocks) throws IOException {
        // System.out.println("[1st] Start");
        // 준비
        int buffersize = blocksize * nblocks;
        File tmpDir = new File("./tmp");
        int n, i;
        
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        } else {
            tmpDir.delete();
            tmpDir.mkdir();
        }

        // 첫번째 정렬.
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(infile), buffersize));

        for (n = 0; n < 64; n++) {
            ArrayList<MutableTriple<Integer, Integer, Integer>> arr = new ArrayList<MutableTriple<Integer, Integer, Integer>>();

            // System.out.printf("[1st:%d] Load Data\n", n);
            for (i = 0; i < 15625; i++) {
                MutableTriple<Integer, Integer, Integer> tmp = readTriple(dis);
                arr.add(tmp);
                tmp = null;
            }

            // System.out.printf("[1st:%d] MergeSort\n", n);
            // mergesort
            mergeSort(arr, 0, arr.size()-1);

            // System.out.printf("[1st:%d] Create File\n", n);
            // file save
            String fileName = filePrefix + String.valueOf(0) + "_" + String.valueOf(n) + filePostfix;
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName, true), buffersize));
            File f = new File(fileName);
            
            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }

            // System.out.printf("[1st:%d] Save Data\n", n);
            for (i = 0; i < arr.size(); i++) {
                // System.out.println(arr.get(i));
                writeTriple(dos, arr.get(i));    
            }
            arr.clear();
            dos.close();
        }
        dis.close();

        // 파일을 이용한 mergeSort 중간 과정
        externalSort(1, 32, buffersize);
        externalSort(2, 16, buffersize);
        externalSort(3, 8, buffersize);
        externalSort(4, 4, buffersize);
        externalSort(5, 2, buffersize);
        lastSort(buffersize, outfile);
        
        // dis = new DataInputStream(new BufferedInputStream(new FileInputStream(outfile), buffersize));
        // int cnt = 0;
        // while (dis.available() > 0) {
        //     dis.readInt();
        //     dis.readInt();
        //     dis.readInt();
        //     cnt++;
        // }
        // System.out.println(cnt);
        // File[] fList = tmpDir.listFiles();
        // for (i = 0; i < fList.length; i++) {
        //     System.out.println(fList[i].getName() == "sorted.data");
        //     if (fList[i].getName() != "sorted.data") {
        //         fList[i].delete();
        //     }
        // }
    }

    private void lastSort(int buffersize, String outfile) throws IOException {
        String leftInfile = filePrefix + "5_0" + filePostfix;
        String rightInfile = filePrefix + "5_1" + filePostfix;
        File f = new File(outfile);
        int j;

        if (!f.exists()) {
                f.createNewFile();
        } else {
                f.delete();
                f.createNewFile();
        }

        DataInputStream leftDis = new DataInputStream(new BufferedInputStream(new FileInputStream(leftInfile), buffersize));
        DataInputStream rightDis = new DataInputStream(new BufferedInputStream(new FileInputStream(rightInfile), buffersize));
        DataOutputStream dos = new DataOutputStream(
            new BufferedOutputStream(new FileOutputStream(outfile, true), buffersize));
        
        while ((leftDis.available() > 0) && (rightDis.available() > 0)) {
            MutableTriple<Integer, Integer, Integer> left = readTriple(leftDis);
            MutableTriple<Integer, Integer, Integer> right = readTriple(rightDis);
            j = comparison(left, right);
            if (j == -1) {
                writeTriple(dos, right);
                left = readTriple(rightDis);
            } else if (j == 0) {
                writeTriple(dos, left);
                writeTriple(dos, right);
                left = readTriple(leftDis);
                right = readTriple(rightDis);
            } else {
                writeTriple(dos, left);
                left = readTriple(leftDis);
            }

            while (leftDis.available() > 0) {
                writeTriple(dos, left);
                left = readTriple(leftDis);
            }

            while (rightDis.available() > 0) {
                writeTriple(dos, right);
                left = readTriple(rightDis);
            }
        }

        leftDis.close();
        rightDis.close();
        dos.close();
    }
    

    private void externalSort(int level, int fileCnt, int buffersize) throws IOException {
        int j;

        for (int i = 0; i < fileCnt; i ++) {
            String leftInfile = filePrefix + String.valueOf(level-1) + "_" + String.valueOf(2*i) + filePostfix;
            String rightInfile = filePrefix + String.valueOf(level-1) + "_" + String.valueOf(2*i + 1) + filePostfix;
            String outfile = filePrefix + String.valueOf(level) + "_" + String.valueOf(i) + filePostfix;

            File f = new File(outfile);

            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }

            DataInputStream leftDis = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(leftInfile), buffersize));
            DataInputStream rightDis = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(rightInfile), buffersize));
            DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(outfile, true), buffersize));

            while ((leftDis.available() > 0) && (rightDis.available() > 0)) {
                MutableTriple<Integer, Integer, Integer> left = readTriple(leftDis);
                MutableTriple<Integer, Integer, Integer> right = readTriple(rightDis);
                j = comparison(left, right);
                if (j == -1) {
                    writeTriple(dos, right);
                    left = readTriple(rightDis);
                } else if (j == 0) {
                    writeTriple(dos, left);
                    writeTriple(dos, right);
                    left = readTriple(leftDis);
                    right = readTriple(rightDis);
                } else {
                    writeTriple(dos, left);
                    left = readTriple(leftDis);
                }

                while (leftDis.available() > 0) {
                    writeTriple(dos, left);
                    left = readTriple(leftDis);
                }

                while (rightDis.available() > 0) {
                    writeTriple(dos, right);
                    left = readTriple(rightDis);
                }
            }
            
            dos.close();
            leftDis.close();
            rightDis.close();
            
        }
    }

    private int comparison(MutableTriple<Integer, Integer, Integer> a, MutableTriple<Integer, Integer, Integer> b) {
		if (a.getLeft() > b.getLeft()) {
			return -1;
		} else if (a.getLeft() < b.getLeft()) {
			return 1;
		}

		// a.getLeft() == b.getLeft()
		if (a.getMiddle() > b.getMiddle()) {
			return -1;
		} else if (a.getMiddle() < b.getMiddle()) {
			return 1;
		}

		// a.getMiddle() == b.getMiddle()
		if (a.getRight() > b.getRight()) {
			return -1;
		} else if (a.getRight() < b.getRight()) {
			return 1;
		}

		// a == b
		return 0;
	}

    private MutableTriple<Integer, Integer, Integer> readTriple(DataInputStream dis) throws IOException {
        MutableTriple<Integer, Integer, Integer> triple = new MutableTriple<Integer, Integer, Integer>();
        
        triple.setLeft(Integer.valueOf(dis.readInt()));
        triple.setMiddle(Integer.valueOf(dis.readInt()));
        triple.setRight(Integer.valueOf(dis.readInt()));

        return triple;
    }

    private void writeTriple(DataOutputStream dos, MutableTriple<Integer, Integer, Integer> triple) throws IOException {
        dos.writeInt(triple.getLeft());
        dos.writeInt(triple.getMiddle());
        dos.writeInt(triple.getRight());
    }

    private void mergeSort(ArrayList<MutableTriple<Integer, Integer, Integer>> arr, int low, int high) {
        // System.out.printf("mergeSort(%d, %d): start ===\n", low, high);
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(arr, low, middle);
            mergeSort(arr, middle + 1, high);
            merge(arr, low, middle, high);
        }
        // System.out.println("mergeSort: end ===");
    }

    private void merge(ArrayList<MutableTriple<Integer, Integer, Integer>> arr, int low, int middle, int high) {
        // System.out.printf("merge(%d, %d, %d): start ===\n", low, middle, high);
        int ptr1 = 0, ptr2 = 0, ptr3 = low;
        ArrayList<MutableTriple<Integer, Integer, Integer>> left = new ArrayList<MutableTriple<Integer, Integer, Integer>>();
        ArrayList<MutableTriple<Integer, Integer, Integer>> right = new ArrayList<MutableTriple<Integer, Integer, Integer>>();

        for (int i = low; i <= middle; i++) {
            left.add(arr.get(i));
        }
        for (int i = middle + 1; i <= high; i++) {
            right.add(arr.get(i));
        }
        // System.out.printf("ptr1 = %d, ptr2 = %d, ptr3 = %d\n", ptr1, ptr2, ptr3);

        while (ptr1 < (middle - low + 1) && ptr2 < (high - middle)) {
            switch (comparison(left.get(ptr1), right.get(ptr2))) {
            case -1:
                arr.set(ptr3++, right.get(ptr2++));
                break;
            case 1:
                arr.set(ptr3++, left.get(ptr1++));
                break;
            case 0:
                arr.set(ptr3++, left.get(ptr1++));
                arr.set(ptr3++, right.get(ptr2++));
                break;
            }
        }

        // System.out.printf("ptr1 = %d, ptr2 = %d, ptr3 = %d\n", ptr1, ptr2, ptr3);
        // System.out.printf("high-middle = %d\n", high-middle);

        while (ptr1 < (middle - low + 1)) {
            // System.out.printf("Left remain: (ptr = %d, end = %d)\n", ptr1, middle - low + 1);
            arr.set(ptr3++, left.get(ptr1++));
        }

        while (ptr2 < (high - middle)) {
            // System.out.printf("Right remain: (ptr = %d, end = %d)\n", ptr2, high - middle);
            arr.set(ptr3++, right.get(ptr2++));
        }

        // System.out.println("merge: end ===");
    }
}