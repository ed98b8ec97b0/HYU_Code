# 정보검색론 두번째 과제

## 개요

int형 3,000,000개 담겨있는 파일을 불러들여 이를 MutableTriple 클래스를 이용하여 (123, 456, 789)의 형태로 구현한 뒤, mergeSort를 이용하여 정렬하는 문제.

## 세부 사항

1. int형의 data 3,000,000개 == MutableTriple 1,000,000개.
2. 메모리 제한때문에 MutableTriple을 31,618개까지 만들 수 있음.
3. 속도가 빠를 수록 유리.

## 구현 시 반드시 생각해야하는 점

1. 메모리의 제한에 의해 external의 범위가 디스크가 되어버림.
2. 읽고 쓰는 과정을 최대한 줄일 필요성이 있음.
3. 100,000개의 파일을 읽고 쓰는데 드는 최소 비용을 생각해서 데이터를 나눠야함.

## 구현 사항

1. ``for``문을 사용해서 20,000개씩 읽어서 정렬하여 ``sorting_1_i``로 저장한다.
    - 모자랄 경우, ``dis.available() > 0``을 이용하여 한도까지만 읽음.
    - i는 1부터 가능한 한도까지
    - 예상 파일 수: 50개.
2. 낮은 순번대로 하나씩 읽으면서 ``sorting_d_i``에 저장한다.
    - 예상 파일 수: 25개.
3. 2번 과정을 파일이 2개만 남을 때까지 반복한다.
    - 예상 반복 수: 4번.
4. 파일이 2개만 남으면 파일을 합치면서 `./tmp/sorted.data`에 저장한다.