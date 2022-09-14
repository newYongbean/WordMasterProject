package edu.handong.csee.practicalproject1.word;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s; // 사용자에게 입력 받기 위함

    /*
    => 난이도(1,2,3) & 새 단어 입력 : 1 driveway
    뜻 입력 : 차고 진입로
    새 단어가 단어장에 추가되었습니다 !!!
    */

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine(); // next()만 했을 경우 enter가 남아 있어서 meaning은 skip하게 된다.

        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine(); // 공백 포함을 위함

        return new Word(0, level, word, meaning);
    }

    public void addWord() {
        Word one = (Word)add(); // return type이 Object이기 때문에 typecast 필요
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    /*
    * --------------------------------
1 * driveway 차고 진입로
2 ** graceful 우아한
--------------------------------
*
    * */
    public void listAll() {
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }
}
