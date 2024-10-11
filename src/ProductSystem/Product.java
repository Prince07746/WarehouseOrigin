package ProductSystem;
import java.util.UUID;

public class Product {

    private String id;
    private String productType;
    private String name;
    private String model;
    private int sellingPrice;
    private int expiringYear;
    private int purchasingPrice;
    private String manufacturer;
    private int quantity;
    private String description;

    public Product(String productType, String name, String model, int sellingPrice, int expiringYear,
                   int purchasingPrice, String manufacturer, int quantity, String description) {
        this.id = UUID.randomUUID().toString();
        this.productType = productType;
        this.name = name;
        this.model = model;
        this.sellingPrice = sellingPrice;
        this.expiringYear = expiringYear;
        this.purchasingPrice = purchasingPrice;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public int getPurchasingPrice() {
        return purchasingPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public void updateField(String field, String value) {
        switch (field.toLowerCase()) {
            case "name":
                this.name = value;
                break;
            case "sellingprice":
                this.sellingPrice = Integer.parseInt(value);
                break;
            case "purchasingprice":
                this.purchasingPrice = Integer.parseInt(value);
                break;
            case "model":
                this.model = value;
                break;
            case "producttype":
                this.productType = value;
                break;
            case "expiringyear":
                this.expiringYear = Integer.parseInt(value);
                break;
            case "manufacturer":
                this.manufacturer = value;
                break;
            default:
                System.out.println("Invalid field specified.");
        }
    }



    @Override
    public String toString() {
        return "ID: " + id + ", Type: " + productType + ", Name: " + name + ", Model: " + model + ", Selling Price: " + sellingPrice +
                ", Expiring Year: " + expiringYear + ", Purchasing Price: " + purchasingPrice + ", Manufacturer: " + manufacturer;
    }
}