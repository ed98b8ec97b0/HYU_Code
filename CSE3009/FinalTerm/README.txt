HOW TO RUN
------------------------------------------------

$ antlr4 -visitor -no-listener While.g4
$ javac *.java
$ java Run test.txt

정상동작:
2.1번
2.2번

특정부분 오류:
1번의 stmt의 assign와 expr의 bop 부분을 구현하는데 type 비교에 대해 어려움을 겪고있습니다.