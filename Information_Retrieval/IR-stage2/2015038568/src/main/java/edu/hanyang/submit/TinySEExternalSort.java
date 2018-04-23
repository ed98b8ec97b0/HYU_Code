package edu.hanyang.submit;

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
    ArrayList<MutableTriple<Integer, Integer, Integer>> arr = new ArrayList<MutableTriple<Integer, Integer, Integer>>();
    int fileCnt = 64;
    int tripleCnt = 15625;
    int arrBuf = 15625;
    String filePrefix = "./tmp/sorting";
    String filePostfix = ".data";

    // blocksize = 1024, nblocks = 160
    public void sort(String infile, String outfile, String tmpdir, int blocksize, int nblocks) throws IOException {
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

        for (n = 0; n < fileCnt; n++) {
            for (i = 0; i < tripleCnt; i++) {
                MutableTriple<Integer, Integer, Integer> tmp = readTriple(dis);
                arr.add(tmp);
                tmp = null;
            }

            // mergesort
            mergeSort(arr, 0, arr.size()-1);

            // file save
            String fileName = filePrefix + String.valueOf(0) + "_" + String.valueOf(n) + filePostfix;
            File f = new File(fileName);
            
            if (!f.exists()) {
                f.createNewFile();
            } else {
                f.delete();
                f.createNewFile();
            }

            writeArr(fileName, buffersize, arr);
            arr.clear();
        }
        dis.close();

        // 파일을 이용한 mergeSort 중간 과정
        externalSort(1, 32, buffersize, "");
        externalSort(2, 16, buffersize, "");
        externalSort(3, 8, buffersize, "");
        externalSort(4, 4, buffersize, "");
        externalSort(5, 2, buffersize, "");
        externalSort(6, 1, buffersize, outfile);

        // 중간파일 제거
        File[] fList = tmpDir.listFiles();
        for (i = 0; i < fList.length; i++) {
            if (fList[i].getName().startsWith("sorting")) {
                fList[i].delete();
            }
        }
    }

    private void externalSort(int level, int fileCnt, int buffersize, String outfile) throws IOException {
        int j;

        for (int i = 0; i < fileCnt; i ++) {
            String leftInfile = filePrefix + String.valueOf(level-1) + "_" + String.valueOf(2*i) + filePostfix;
            String rightInfile = filePrefix + String.valueOf(level-1) + "_" + String.valueOf(2*i + 1) + filePostfix;
            if (level < 6) {
                outfile = filePrefix + String.valueOf(level) + "_" + String.valueOf(i) + filePostfix;
            }
            File f = new File(outfile);

            arr.clear();
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


            MutableTriple<Integer, Integer, Integer> left = readTriple(leftDis);
            MutableTriple<Integer, Integer, Integer> right = readTriple(rightDis);

            while ((leftDis.available() > 0) && (rightDis.available() > 0)) {
                j = comparison(left, right);
                if (j == -1) {
                    arr.add(right);
                    right = null;
                    if (rightDis.available() > 0) {
                        right = readTriple(rightDis);
                    }
                } else if (j == 0) {
                    arr.add(left);
                    arr.add(right);
                    left = null;
                    right = null;
                    if (leftDis.available() > 0) {
                        left = readTriple(leftDis);
                    }
                    if (rightDis.available() > 0) {
                        right = readTriple(rightDis);
                    }
                } else {
                    arr.add(left);
                    left = null;
                    if (leftDis.available() > 0) {
                        left = readTriple(leftDis);
                    }
                }

                if (arr.size() >= arrBuf) {
                    writeArr(outfile, buffersize, arr);
                    arr.clear();
                }
            }

            while (leftDis.available() > 0) {
                left = readTriple(leftDis);
                arr.add(left);
                left = null;
                if (leftDis.available() > 0) {
                    left = readTriple(leftDis);
                }

                if (arr.size() >= arrBuf) {
                    writeArr(outfile, buffersize, arr);
                    arr.clear();
                }
            }

            while (rightDis.available() > 0) {
                right = readTriple(rightDis);
                arr.add(right);
                right = null;
                if (rightDis.available() > 0) {
                    right = readTriple(rightDis);
                }

                if (arr.size() >= arrBuf) {
                    writeArr(outfile, buffersize, arr);
                    arr.clear();
                }
            }

            if (arr.size() > 0) {
                writeArr(outfile, buffersize, arr);
                arr.clear();
            }

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

    private void writeArr(String outfile, int buffersize, ArrayList<MutableTriple<Integer, Integer, Integer>> arr) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outfile, true), buffersize));

        for (int i = 0; i < arr.size(); i++) {
            dos.writeInt((arr.get(i)).getLeft());
            dos.writeInt((arr.get(i)).getMiddle());
            dos.writeInt((arr.get(i)).getRight());
        }

        dos.close();
    }

    private void mergeSort(ArrayList<MutableTriple<Integer, Integer, Integer>> arr, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergeSort(arr, low, middle);
            mergeSort(arr, middle + 1, high);
            merge(arr, low, middle, high);
        }
    }

    private void merge(ArrayList<MutableTriple<Integer, Integer, Integer>> arr, int low, int middle, int high) {
        int ptr1 = 0, ptr2 = 0, ptr3 = low;
        ArrayList<MutableTriple<Integer, Integer, Integer>> left = new ArrayList<MutableTriple<Integer, Integer, Integer>>();
        ArrayList<MutableTriple<Integer, Integer, Integer>> right = new ArrayList<MutableTriple<Integer, Integer, Integer>>();

        for (int i = low; i <= middle; i++) {
            left.add(arr.get(i));
        }
        for (int i = middle + 1; i <= high; i++) {
            right.add(arr.get(i));
        }

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

        while (ptr1 < (middle - low + 1)) {
            arr.set(ptr3++, left.get(ptr1++));
        }

        while (ptr2 < (high - middle)) {
            arr.set(ptr3++, right.get(ptr2++));
        }
    }
}