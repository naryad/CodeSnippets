package yn.designpatterns;

public class BuilderPattern {

}
class Pizza { // director
	 
    private final String dough;
    private final String sauce;
    private final String topping;

    public String getDough() {
            return dough;
    }

    public String getSauce() {
            return sauce;
    }


    public String getTopping() {
            return topping;
    }

    Pizza(PizzaBuilder builder) {
            dough = builder.getDough();
            sauce = builder.getSauce();
            topping = builder.getTopping();
    }

    @Override
    public String toString() {
            return "Dough:" + dough + " Topping:" + topping + " Sauce:" + sauce;
    }
}

class PizzaBuilder {
    String dough;
    String sauce;
    String topping;

    public String getDough() {
            return dough;
    }

    public String getSauce() {
            return sauce;
    }

    public String getTopping() {
            return topping;
    }

    public PizzaBuilder setDough(String dough) {
            this.dough = dough;
            return this;
    }

    public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
    }

    public PizzaBuilder setTopping(String topping) {
            this.topping = topping;
            return this;
    }

    public Pizza build() {
            return new Pizza(this);
    }
}

class PizzaBuilderExample {
    public static void main(String[] args) {
            PizzaBuilder hawaiianPizzaBuilder = new PizzaBuilder()
                            .setDough("cross").setTopping("ham+pineapple").setSauce("mild");
            Pizza hawaiianPizza = hawaiianPizzaBuilder.build();
            System.out.println("Hawaiian Pizza: "+hawaiianPizza);
    }
}