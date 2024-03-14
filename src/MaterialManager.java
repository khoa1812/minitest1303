import java.time.LocalDate;

public class MaterialManager {
    private Material[] materials;
    private int capacity;
    private int size;

    public MaterialManager() {
    }

    public MaterialManager(Material[] materials, int capacity, int size) {
        this.materials = materials;
        this.capacity = capacity;
        this.size = size;
    }

    public Material[] getMaterials() {
        return materials;
    }

    public void setMaterials(Material[] materials) {
        this.materials = materials;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MaterialManager(int capacity) {
        this.capacity = capacity;
        materials = new Material[capacity];
        size = 0;
    }

    public void addMaterial(Material material) {
        if (size < capacity) {
            materials[size++] = material;
        } else {
            System.out.println("Danh sách vật liệu đã đầy, không thể thêm mới.");
        }
    }

    public void editMaterial(String id, Material newMaterial) {
        for (int i = 0; i < size; i++) {
            if (materials[i].getId().equals(id)) {
                materials[i] = newMaterial;
                return;
            }
        }
        System.out.println("Không tìm thấy vật liệu có ID là " + id);
    }

    public void removeMaterial(String id) {
        for (int i = 0; i < size; i++) {
            if (materials[i].getId().equals(id)) {
                for (int j = i; j < size - 1; j++) {
                    materials[j] = materials[j + 1];
                }
                size--;
                return;
            }
        }
        System.out.println("Không tìm thấy vật liệu có ID là " + id);
    }

    public double calculateTotalCostOfMaterials() {
        double totalCost = 0;
        int count = Math.min(size, 10);
        for (int i = 0; i < count; i++) {
            Material material = materials[i];
            totalCost += material instanceof Discount ? ((Discount) material).getRealMoney() : material.getAmount();
        }
        return totalCost;
    }

    public void sortMaterialsByCost() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (materials[j].getCost() > materials[j + 1].getCost()) {
                    Material temp = materials[j];
                    materials[j] = materials[j + 1];
                    materials[j + 1] = temp;
                }
            }
        }
    }

    public double calculateDiscountDifference() {
        double totalDiscount = 0;
        for (int i = 0; i < size; i++) {
            if (materials[i] instanceof Discount) {
                double discountAmount = materials[i].getAmount() - ((Discount) materials[i]).getRealMoney();
                totalDiscount += discountAmount;
            }
        }
        return totalDiscount;
    }
}
