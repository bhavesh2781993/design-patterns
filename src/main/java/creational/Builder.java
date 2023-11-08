package creational;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Provides a way to avoid creating too many constructors to assign specific fields
 * Example: Lombok, Locale
 */
public class Builder {
    public static void main(String[] args) {
        // Locale uses this pattern to create object with Builder Pattern
        final Product redProduct = new Product.Builder()
            .id("1")
            .color("red")
            .price(BigDecimal.ONE)
            .build();
        System.out.println(redProduct);

        // Java built in class that uses Builder Pattern
        final Locale enLocal = new Locale.Builder()
            .setLanguage("en")
            .build();
        System.out.println(enLocal);

        // Lombok Uses this pattern to create object with Builder Pattern
        final Product blueProduct = Product.builder()
            .id("2")
            .color("blue")
            .price(BigDecimal.ONE)
            .build();

        System.out.println(blueProduct);
    }
}

class Product {
    private String id;
    private String color;
    private BigDecimal price;

    static class Builder {
        Product product = new Product();

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public Builder color(String color) {
            product.color = color;
            return this;
        }

        public Builder price(BigDecimal price) {
            product.price = price;
            return this;
        }

        public Product build() {
            return product;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
