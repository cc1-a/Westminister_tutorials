package uk.ac.westminster.products_api;

public class PhysicalProduct extends Product {
    private double weightKg;
    private double shippingCost;

    public PhysicalProduct() {
        super();
    }

    public PhysicalProduct(int id, String name, double price, double weightKg, double shippingCost) {
        super((long) id, name, price);
        this.weightKg = weightKg;
        this.shippingCost = shippingCost;
    }

    public PhysicalProduct(Long id, String name, double price, double weightKg, double shippingCost) {
        super(id, name, price);
        this.weightKg = weightKg;
        this.shippingCost = shippingCost;
    }

    public PhysicalProduct(String name, double price, double weightKg, double shippingCost) {
        super(name, price);
        this.weightKg = weightKg;
        this.shippingCost = shippingCost;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
}
