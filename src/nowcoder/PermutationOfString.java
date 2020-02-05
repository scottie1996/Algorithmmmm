package nowcoder;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PermutationOfString { //字符串的排列
    public ArrayList<String> resultArrayList = new ArrayList<>();
    LinkedList<Character> characterLinkedList = new LinkedList<>();
    int originalSize;
    int i = 1;
    public void permutation(String str) {
        characterLinkedList = convertToLinkedList(str);
        originalSize = characterLinkedList.size();
        generatePermutation(characterLinkedList);
        removeRedundant(resultArrayList);
        System.out.println(resultArrayList);
    }

    private void removeRedundant(ArrayList<String> resultArrayList) {
        Iterator iterator = resultArrayList.iterator();
        while (iterator.hasNext()){
            String tempString = (String) iterator.next();
            if (tempString.length() != originalSize){
                iterator.remove();
            }
        }
    }

    private LinkedList<Character> convertToLinkedList(String str) {
        LinkedList<Character> tempLinkedList = new LinkedList<>();
        char[] charArray = str.toCharArray();
        for (char c  : charArray){
            tempLinkedList.add(c);
        }
        return tempLinkedList;
    }

    private void generatePermutation(LinkedList<Character> characterLinkedList){

        if (characterLinkedList.size() == 1){

            resultArrayList.add(String.valueOf(characterLinkedList.getFirst()));
        }
        else {
            LinkedList<Character> copyLinkedList = deepClone(characterLinkedList);
            characterLinkedList.removeFirst();
            generatePermutation(characterLinkedList);
            insert(copyLinkedList);
        }
    }

    private LinkedList<Character> deepClone(LinkedList<Character> characterLinkedList) {
        LinkedList<Character> tempList = new LinkedList<>();
        Iterator iterator = characterLinkedList.iterator();
        while (iterator.hasNext()){
            tempList.add((Character) iterator.next());
        }
        return tempList;
    }

    private void insert(LinkedList<Character> characterLinkedList) {
        Character operateChar = characterLinkedList.getFirst();
        ArrayList<String> targetArrayList = getTarget(i++);
        doInsert(operateChar,targetArrayList);
    }

    private void doInsert(Character operateChar, ArrayList<String> targetArrayList) {
        for ( String s : targetArrayList){
            for (int i = 0 ; i <= s.length() ; i++ ){
                LinkedList<Character> tempList  = convertToLinkedList(s);
                tempList.add(i , operateChar);
                resultArrayList.add(convertToString(tempList));
            }
        }
    }

    private String convertToString(LinkedList<Character> tempList) {
        String tempString = "";
        for (Character c : tempList){
            tempString = tempString + c;
        }
        return tempString;
    }

    private ArrayList<String> getTarget(int currentSize) {
        int lengthToFind = currentSize;
        ArrayList<String> targetArrayList = new ArrayList<>();
        for (String s : resultArrayList){
            if (s.length() == lengthToFind ){
                targetArrayList.add(s);
            }
        }
        return targetArrayList;
    }
}
