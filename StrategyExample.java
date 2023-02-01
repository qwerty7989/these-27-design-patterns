import java.util.ArrayList;

class AlignmentStrategy {
    String alignment = "=";

    String adjustAlignment() {
        return alignment;
    }
}

class LeftAlignment extends AlignmentStrategy {
    String alignment = "***";

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



}

class TextComposite {
    public char character;
    public ArrayList<TextComposite> group;
    String color;

    public AlignmentStrategy alignment;

    public TextComposite() {
        group = new ArrayList<>();
        color = "BLACK";
        alignment = new LeftAlignment();
    }

    public void setColor(String newColor) {
        color = newColor;
        for (TextComposite child : group) {
            child.setColor(color);
        }
    }

    public void print(int depth) {
        if (Character.valueOf(character) != 0)
            System.out.println(alignment.adjustAlignment() + depth + " : " + character + " is " + color);
        
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