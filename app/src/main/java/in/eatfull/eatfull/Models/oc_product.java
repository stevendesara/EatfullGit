package in.eatfull.eatfull.Models;

/**
 * Created by steve on 17/6/16.
 */
public class oc_product {
    private String name;

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
    private int product_id;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    private String model;
    private String location;
    private int quantity;
    private int stock_status_id;
    private String image;
    private int manufacturer_id;
    private int shipping;
    private double price;
    private double weight;
    private int sort_order;
    private int viewed;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStock_status_id() {
        return stock_status_id;
    }

    public void setStock_status_id(int stock_status_id) {
        this.stock_status_id = stock_status_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public void setName(String name) {
        this.name = name;
    }
}
