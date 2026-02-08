import java.util.ArrayList;
import java.util.List;

// 1) create class
public class Product {
    // for Product constructor
    private String productName;
    private long price;
    private boolean inStock;
    private List<String> tags;

    // 2) overloaded constructors
    public Product() {
        this.productName = "Unknown";
        this.price = 0;
        this.inStock = false;
        this.tags = new ArrayList<>();
    }

    public Product(String productName) {
        this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName;
        this.price = 0;
        this.inStock = false;
        this.tags = new ArrayList<>();
    }

    public Product(String productName, long price) {
        this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName;
        this.price = Math.max(price, 0);
        this.inStock = false;
        this.tags = new ArrayList<>();
    }

    public Product(String productName, long price, List<String> tags) {
        this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName;
        this.price = Math.max(price, 0);
        this.inStock = false;
        this.tags = (tags == null) ? new ArrayList<>() : new ArrayList<>(tags);
    }

    public Product(String productName, long price, boolean inStock) {
        this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName;
        this.price = Math.max(price, 0);
        this.inStock = inStock;
        this.tags = new ArrayList<>();
    }

        public Product(String productName, long price, boolean inStock, List<String> tags) {
        this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName;
        this.price = Math.max(price, 0);
        this.inStock = inStock;
        this.tags = (tags == null) ? new ArrayList<>() : new ArrayList<>(tags);
    }

    public Product(String productName, long price, List<String> tags, boolean inStock) {
        this.productName = (productName == null || productName.trim().isEmpty()) ? "Unknown" : productName;
        this.price = Math.max(price, 0);
        this.inStock = inStock;
        this.tags = (tags == null) ? new ArrayList<>() : new ArrayList<>(tags);
    }

    // 3) copy constructor
    public Product(Product other) {
        this.productName = other.productName;
        this.price = other.price;
        this.inStock = other.inStock;
        this.tags = new ArrayList<>(other.tags != null ? other.tags : new ArrayList<>());
    }

    // 4) encapsulate tags
    public List<String> getTags(){
        return new ArrayList<>(tags);
    }

    public void setTags(List<String> tags){
        this.tags = (tags == null) ? new ArrayList<>() : new ArrayList<>(tags);
    }

    // addtag construcor
    public void addTag(String tag){
        if (tag != null && !tag.trim().isEmpty()) {
            this.tags.add(tag);
        }
    }

    // 5)
    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", tags=" + tags +
                '}';
    }
}