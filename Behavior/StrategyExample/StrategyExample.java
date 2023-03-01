package StrategyExample;
import java.util.ArrayList;

class AlignmentStrategy {
    String alignment = "=";

    String adjustAlignment() {
        return alignment;
    }
}

class LeftAlignment extends AlignmentStrategy {
    String alignment = "";

    @Override
    String adjustAlignment() {
        return alignment;
    }
}

class RightAlignment extends AlignmentStrategy {
    String alignment = "#####";

    @Override
    String adjustAlignment() {
        /**
         * Do some alignment
         */
        return alignment;
    }
}

class CenterAlignment extends AlignmentStrategy {
    // ? See we can just literally add a new alignment and it'll work like a charm.
    String alignment = "***";

    @Override
    String adjustAlignment() {
        /**
         * Do some alignment
         */
        return alignment;
    }
}

class TextComposite {
    char character;
    ArrayList<TextComposite> group;
    String color;

    public AlignmentStrategy alignment;

    public TextComposite() {
        group = new ArrayList<>();
        color = "BLACK";

        this.alignment = new LeftAlignment();
    }

    public TextComposite(AlignmentStrategy alignment) {
        group = new ArrayList<>();
        color = "BLACK";

        this.alignment = alignment;
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
            System.out.println(alignment.adjustAlignment() + "[" + depth + "]" + " : " + character + " is " + color);

        for (TextComposite child : group) {
            child.print(depth + 1);
        }
    }
}

class StrategyExample {
    public static void main(String[] args) {
        TextComposite aPage = new TextComposite();
        aPage.character = 'A';

        TextComposite lineOne = new TextComposite();

        TextComposite charOne = new TextComposite();
        charOne.character = 'B';

        TextComposite charTwo = new TextComposite(new RightAlignment());
        charTwo.character = 'C';

        TextComposite charThree = new TextComposite(new RightAlignment());
        charThree.character = 'D';

        TextComposite charFour = new TextComposite(new CenterAlignment());
        charFour.character = 'E';

        TextComposite charFive = new TextComposite(new CenterAlignment());
        charFive.character = 'F';

        TextComposite groupOne = new TextComposite(new CenterAlignment());
        groupOne.group.add(charFour);
        groupOne.group.add(charFive);
        lineOne.group.add(groupOne);

        lineOne.group.add(charOne);
        lineOne.group.add(charTwo);
        lineOne.group.add(charThree);

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