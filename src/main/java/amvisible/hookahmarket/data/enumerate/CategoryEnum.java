package amvisible.hookahmarket.data.enumerate;

public enum CategoryEnum {
    HOOKAH("Наргилета"),
    BOWL("Чашки"),
    HOSE("Маркучи"),
    CHARCOAL("Въглени"),
    CHARCOAL_HEATER("Котлони");

    private String name;

    CategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
