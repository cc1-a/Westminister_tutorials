package uk.ac.westminster.products_api;

public class DigitalProduct extends Product implements Discountable {
    private String downloadUrl;
    private double filzeSize;
    private double discountedPrice;

    public DigitalProduct() {
        super();
    }

    public DigitalProduct(int id, String name, double price, String downloadUrl, double filzeSize) {
        super((long) id, name, price);
        this.downloadUrl = downloadUrl;
        this.filzeSize = filzeSize;
        this.discountedPrice = price;
    }

    public DigitalProduct(Long id, String name, double price, String downloadUrl, double filzeSize) {
        super(id, name, price);
        this.downloadUrl = downloadUrl;
        this.filzeSize = filzeSize;
        this.discountedPrice = price;
    }

    public DigitalProduct(String name, double price, String downloadUrl, double filzeSize) {
        super(name, price);
        this.downloadUrl = downloadUrl;
        this.filzeSize = filzeSize;
        this.discountedPrice = price;
    }

    @Override
    public void applyDiscount(double rate) {
        if (rate > 1.0) {
            rate = rate / 100.0;
        }
        this.discountedPrice = getPrice() * (1.0 - rate);
    }

    @Override
    public double getDiscountedPrice() {
        return this.discountedPrice;
    }

    @Override
    public String getSummary() {
        return getName() + " | Download: " + this.downloadUrl + " | Size: " + filzeSize + "MB";
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public double getFilzeSize() {
        return filzeSize;
    }

    public void setFilzeSize(double filzeSize) {
        this.filzeSize = filzeSize;
    }

    public double getFileSizeMb() {
        return filzeSize;
    }

    public void setFileSizeMb(double fileSizeMb) {
        this.filzeSize = fileSizeMb;
    }
}
