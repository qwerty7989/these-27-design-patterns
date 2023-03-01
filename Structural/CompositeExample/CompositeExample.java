package CompositeExample;
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
    char character;
    ArrayList<TextComposite> group;
    String color;

    public TextComposite() {
        group = new ArrayList<>();
        color = "BLACK";
    }

    public void setCharacter(char aChar) {
        character = aChar;
    }

    public char getCharacter() {
        return character;
    }

    public void add(TextComposite child) {
        group.add(child);
    }

    public TextComposite getChild(int index) {
        return group.get(index);
    }

    public void setColor(String newColor) {
        color = newColor;
        for (TextComposite child : group) {
            child.setColor(color);
        }
    }

    public void print(int depth) {
        if (Character.valueOf(character) != 0)
            System.out.println("[" + depth + "]" + " : " + character + " is " + color);

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

        TextComposite groupOne = new TextComposite();
        groupOne.group.add(charFour);
        groupOne.group.add(charFive);
        lineOne.group.add(groupOne);

        lineOne.group.add(charOne);
        lineOne.group.add(charTwo);
        lineOne.group.add(charThree);
        lineOne.group.add(groupOne);

        aPage.group.add(lineOne);
        aPage.print(0);
        System.out.println();

        charOne.setColor("RED");
        aPage.print(0);
        System.out.println();

        groupOne.setColor("BLUE");
        aPage.print(0);
        System.out.println();

        aPage.setColor("GREEN");
        aPage.print(0);
        System.out.println();
    }
}