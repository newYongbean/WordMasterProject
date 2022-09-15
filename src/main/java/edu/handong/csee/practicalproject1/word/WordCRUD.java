package edu.handong.csee.practicalproject1.word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s; // 사용자에게 입력 받기 위함
    final String fname = "Dictionary.txt";

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

    public void addItem() {
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

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idList =  new ArrayList<>();
        int j = 0;

        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idList.add(i);
            j++;
        }
        System.out.println("--------------------------------");

        return idList;
    }

    public void updateItem() {
        System.out.print("=> 수정할단어검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.print("=> 수정할번호선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idList.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어수정이성공적으로되었습니다!!\n");
    }

    public void deleteItem() {
        System.out.print("=> 삭제할단어검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.print("=> 삭제할번호선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로삭제하실래요?(Y/n) ");
        String answer = s.next();
        if(answer.equalsIgnoreCase("y")) {
            list.remove((int)idList.get(id-1)); // idList는 Integer type이기 때문에 typecast를 해줘야한다
            System.out.println("단어 삭제가 성공적으로 되었습니다!!\n");
        } else
        System.out.println("취소 되었습니다.\n");
    }

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;

            while(true) {
                line = br.readLine();
                if(line == null) break;
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩 완료!!!\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
    }
}
