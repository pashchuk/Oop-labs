/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_lab2;

import java.util.Comparator;

/**
 *
 * @author MAMAxropela-PC
 */
public class Oop_lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String line = "відсортувати слова заданого тексту за зростанням кількості голосних літер";
        Text text = new Text(line);
        System.out.print(text.toString());
    }
}

class Letter 
{
    private char letter;
    public Letter(char letter) {
        this.letter = letter;
    }
    
    @Override
    public String toString() {
        return Character.toString(letter);
    }
}

class Punctuation implements IWord
{
    private char symbol;
    public static String mustvalue = "!?,.:;\"";
    
    @Override
    public String toString() {
        return Character.toString(symbol); 
    }
    
    public Punctuation(char symbol)
    {
        if (mustvalue.indexOf(symbol) != -1)
            this.symbol = symbol;
        else
            throw new IllegalArgumentException("Argument is not Punctuation symbol");
    }     
}



class Word implements IWord, Comparable
{
    private int count;
    private Letter[] letter;
    public Word(String word)
    {
        count = word.length();
        letter = new Letter[count];
        for(int i = 0; i < count; i++)
            letter[i] = new Letter(word.charAt(i));
    }
    @Override
    public String toString() 
    {
        StringBuilder str = new StringBuilder();
        for(Letter a:letter)
            str.append(a.toString());
        return str.toString();
    }

    @Override
    public int compareTo(Object o) {
        Word word = (Word)o;
        return this.toString().compareTo(word.toString());
    }
    

}


class Sentence
{
    private int Count;
    private IWord[] sentence;
    public Sentence(String sentence)
    {
        this.sentence = new IWord[sentence.split(" ").length*2];
        StringBuilder str = new StringBuilder();
        int k = 0;
        for(int i = 0; i < sentence.length(); i++)
        {
            str.append(sentence.charAt(i));
            if((Punctuation.mustvalue + " ").contains(Character.toString(sentence.charAt(i+1))))
            {                  
                this.sentence[k++] = new Word(str.toString());
                str.delete(0, str.length());
                if (sentence.charAt(i+1) == ' ')
                    continue;
                this.sentence[k++] = new Punctuation(sentence.charAt(++i));
            }
        }
        this.Count = k;
    }

    @Override
    public String toString() 
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < Count; i++)
            str.append(sentence[i].toString());
        return str.toString();
    }
    
    public void Sort()
    {
        sentence.
    }
}


class Text
{
    private Sentence[] text;
    private int SentenceCount;
    public Text(String text)
    {
        String[] temp = text.split("\\u002E");
        SentenceCount = temp.length;
        this.text = new Sentence[SentenceCount];
        for(int i = 0; i < SentenceCount; i++)
            this.text[i] = new Sentence(temp[i] + ".");
    }

    @Override
    public String toString() {
       StringBuilder str = new StringBuilder();
       for(Sentence s:text)
           str.append(s.toString());
       return str.toString();
    }
    
    public void Sort()
    {
        for(int i = 0; i < SentenceCount; i++)
            text[i].Sort();
    }
}

interface IWord
{}

class Comp implements Comparator<Word>
{
    public int compare(Word a1, Word b1)
    {
        String a = a1.toString(), b = b1.toString();
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