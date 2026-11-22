package uk.ac.westminster.products_api;

public class DigitalProduct extends Product {
    private String downloadUrl;
    private double filzeSize;

    public DigitalProduct() {
        super();
    }

    public DigitalProduct(int id, String name, double price, String downloadUrl, double filzeSize) {
        super((long) id, name, price);
        this.downloadUrl = downloadUrl;
        this.filzeSize = filzeSize;
    }

    public DigitalProduct(Long id, String name, double price, String downloadUrl, double filzeSize) {
        super(id, name, price);
        this.downloadUrl = downloadUrl;
        this.filzeSize = filzeSize;
    }

    public DigitalProduct(String name, double price, String downloadUrl, double filzeSize) {
        super(name, price);
        this.downloadUrl = downloadUrl;
        this.filzeSize = filzeSize;
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
