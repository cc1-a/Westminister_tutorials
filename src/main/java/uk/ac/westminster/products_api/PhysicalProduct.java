package uk.ac.westminster.products_api;

public class PhysicalProduct extends Product implements Discountable {
    private double weightKg;
    private double shippingCost;
    private double discount;

    public PhysicalProduct() {
        super();
    }

    public PhysicalProduct(int id, String name, double price, double weightKg, double shippingCost) {
        super((long) id, name, price);
        this.weightKg = weightKg;
        this.shippingCost = shippingCost;
        this.discount = price;
    }

    public PhysicalProduct(Long id, String name, double price, double weightKg, double shippingCost) {
        super(id, name, price);
        this.weightKg = weightKg;
        this.shippingCost = shippingCost;
        this.discount = price;
    }

    public PhysicalProduct(String name, double price, double weightKg, double shippingCost) {
        super(name, price);
        this.weightKg = weightKg;
        this.shippingCost = shippingCost;
        this.discount = price;
    }

    @Override
    public void applyDiscount(double rate) {
        if (rate > 1.0) {
            rate = rate / 100.0;
        }
        double capped = Math.min(rate, 0.20);
        this.discount = getPrice() * (1.0 - capped);
    }

    @Override
    public double getDiscountedPrice() {
        return this.discount;
    }

    @Override
    public String getSummary() {
        return getName() + " | Shipping: £" + this.shippingCost + " | Weight: " + weightKg + "kg";
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
