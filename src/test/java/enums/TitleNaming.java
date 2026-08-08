package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TitleNaming {
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT("Checkout: Your Information"),
    CHECKOUT2("Checkout: Overview"),
    CHECKOUT3("Checkout: Complete!");

    private final String displayName;
}
