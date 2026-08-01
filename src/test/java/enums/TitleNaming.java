package enums;

public enum TitleNaming {
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT("Checkout: Your Information"),
    CHECKOUT2("Checkout: Overview"),
    CHECKOUT3("Checkout: Complete!");

    private final String displayName;

    TitleNaming(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
