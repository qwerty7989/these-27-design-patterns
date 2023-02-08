package DecoratorExample;

class Shape {

}

class Circle implements Shape {
    private radius;

    @Override String info() {
        return "A circle of radius " + radius;
    }
}



class DecoratorExample {
    public static void main(String[] args) {
        Circle cirle = new Circle();
        
    }
    
}
