/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_lab1;

import java.util.Comparator;
import java.util.Arrays;

/**
 *
 * @author MAMAxropela-PC
 */
public class Oop_lab1 
{
    public static void asd(String a)
    {
        Comparator<String> comp = new Comp();
        String words[] = a.split(" ");
        Arrays.sort(words, comp);
        for(String temp:words)
           System.out.print(temp + " ");
    }

    public static void main(String[] args) {
        // TODO code application logic here
        String line = "відсортувати слова заданого тексту за зростанням кількості голосних літер";
        asd(line);
    }
}

class Comp implements Comparator<String>
{
    public int compare(String a, String b)
    {
        String glass = "аоуеиі";
        int count_a = 0, count_b = 0;
        for(int i = 0; i < a.length(); i++)
            if(glass.indexOf(a.charAt(i)) != -1)
                count_a++;
        for(int i = 0; i < b.length(); i++)
            if(glass.indexOf(b.charAt(i)) != -1)
                count_b++;
        return count_a > count_b ? 1:-1;
    }
}