package AbstractFactoryExample;

interface HotDrink {
    void consume();
}

class Tea implements HotDrink {
    @Override
    public void consume() {
        System.out.println("Drinking Tea");
    }
}

class MilkTea extends Tea {
    public int milkAmount;

    @Override
    public void consume() {
        System.out.println("Drinking Milk Tea");
    }
}

class Coffee implements HotDrink {
    @Override
    public void consume() {
        System.out.print("Drinking Coffee");
    }
}

class MilkCoffee extends Coffee {
    public int milkAmount;

    @Override
    public void consume() {
        System.out.println("Drinking Milk Coffee");
    }
}

interface HotDrinkFactory {
    Coffee prepareCoffee();
    Tea prepareTea();
}

class BlackDrinkFactory implements HotDrinkFactory {
    @Override
    public Coffee prepareCoffee(){
        return new Coffee();
    }

    @Override
    public Tea prepareTea() {
        return new Tea();
    }
}

class MilkDrinkFactory implements HotDrinkFactory {
    @Override
    public Coffee prepareCoffee() {
        MilkCoffee coffee = new MilkCoffee();
        coffee.milkAmount = 100;
        return coffee;
    }

    @Override
    public Tea prepareTea() {
        MilkTea tea = new MilkTea();
        tea.milkAmount = 200;
        return tea;
    }
}

class AbstractFactoryExample {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.consume();

        HotDrinkFactory blackFactory = new BlackDrinkFactory();
        Tea teaFromFac = blackFactory.prepareTea();
        teaFromFac.consume();
    }
}

