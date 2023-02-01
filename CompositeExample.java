import java.util.ArrayList;

class Text {
    char character;
    String color;


}

class Word {
    ArrayList<Text> wordText = new ArrayList<>();
    String color;
    
}

class Sentence {
    ArrayList<Word> sentenceText = new ArrayList<>();

}

class Page {
    ArrayList<Text> textList = new ArrayList<>();
    ArrayList<Word> wordList = new ArrayList<>();
    ArrayList<Sentence> sentenceList = new ArrayList<>();
    // ! What the heck are you doing !?
}

class TextComponent {

}

class TextCharacter extends TextComponent {
    public char character;
}


class TextComposite extends TextComponent {
    public char character;
    public ArrayList<TextComposite> group;
    String color;

    public TextComposite() {
        group = new ArrayList<>();
        color = "BLACK";
    }

    public void setColor(String newColor) {
        color = newColor;
        for (TextComposite child : group) {
            child.setColor(color);
        }
    }

    public void print(int depth) {
        if (Character.valueOf(character) != 0)
            System.out.println(depth + " : " + character + " is " + color);
        
        for (TextComposite child : group) {
            child.print(depth + 1);
        }
    }
}

class CompositeExample {
    public static void main(String[] args) {
        TextComposite aPage = new TextComposite();        
        aPage.character = 'A';
        
        TextComposite lineOne = new TextComposite();

        TextComposite charOne = new TextComposite();        
        charOne.character = 'B';
        
        TextComposite charTwo = new TextComposite();        
        charTwo.character = 'C';

        TextComposite charThree = new TextComposite();        
        charThree.character = 'D';

        TextComposite charFour = new TextComposite();        
        charFour.character = 'E';
        
        TextComposite charFive = new TextComposite();        
        charFive.character = 'F';
        
        TextComposite wordOne = new TextComposite();
        wordOne.group.add(charFour);
        wordOne.group.add(charFive);
        lineOne.group.add(wordOne);

        lineOne.group.add(charOne);
        lineOne.group.add(charTwo);
        lineOne.group.add(charThree);

        aPage.group.add(lineOne);
        // aPage.print(0);
        
        charOne.setColor("RED");
        // aPage.print(0);
        
        lineOne.setColor("GREEN");
        aPage.print(0);

    }
}