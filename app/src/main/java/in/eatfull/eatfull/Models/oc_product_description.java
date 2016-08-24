package in.eatfull.eatfull.Models;

/**
 * Created by steve on 21/6/16.
 */
public class oc_product_description {
    private int product_id;
    private String name;
    private String description;

    public oc_product_description(int product_id, String name, String description) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
