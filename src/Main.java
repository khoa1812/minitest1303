import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Material c1 = new CrispyFlour("1", "L", LocalDate.parse("2011-01-01"),23,34);
        Material c2 = new CrispyFlour("2", "L", LocalDate.of(2010,10,18),24,34);
        Material c3 = new CrispyFlour("3", "L", LocalDate.now(),25,34);
        Material c4 = new Meat("4","M",LocalDate.of(2010,11,20),200,70);
        Material c5 = new Meat("5","M",LocalDate.of(2010,11,20),400,30);
        Material c6 = new Meat("6","M",LocalDate.of(2010,11,20),500,20);

        Scanner scanner = new Scanner(System.in);

        MaterialManager manager = new MaterialManager(100);

        manager.addMaterial(c1);
        manager.addMaterial(c2);
        manager.addMaterial(c3);
        manager.addMaterial(c4);
        manager.addMaterial(c5);
        manager.addMaterial(c6);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm vật liệu");
            System.out.println("2. Sửa vật liệu");
            System.out.println("3. Xóa vật liệu");
            System.out.println("4. Tính tổng tiền của 10 vật liệu");
            System.out.println("5. Sắp xếp vật liệu theo giá");
            System.out.println("6. Tính chênh lệch giữa chiết khấu và không chiết khấu");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMaterial(manager, scanner);
                    break;
                case 2:
                    editMaterial(manager, scanner);
                    break;
                case 3:
                    removeMaterial(manager, scanner);
                    break;
                case 4:
                    double totalCost = manager.calculateTotalCostOfMaterials();
                    System.out.println("Tổng tiền của 10 vật liệu: " + totalCost);
                    break;
                case 5:
                    manager.sortMaterialsByCost();
                    System.out.println("Vật liệu sau khi sắp xếp:");
                    Material[] arr = manager.getMaterials();
                    for (int i = 0; i < manager.getSize(); i++) {
                        System.out.println(arr[i]);
                    }
                    break;
                case 6:
                    double discountDifference = manager.calculateDiscountDifference();
                    System.out.println("Chênh lệch giữa chiết khấu và không chiết khấu tại ngày hôm nay: " + discountDifference);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void addMaterial(MaterialManager manager, Scanner scanner) {
        System.out.print("Nhập loại vật liệu (1. Meat, 2. CrispyFlour): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();

        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập ngày sản xuất (yyyy-MM-dd): ");
        LocalDate manufacturingDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Nhập giá: ");
        int cost = scanner.nextInt();

        if (type == 1) {
            System.out.print("Nhập trọng lượng: ");
            double weight = scanner.nextDouble();
            manager.addMaterial(new Meat(id, name, manufacturingDate, cost, weight));
        } else if (type == 2) {
            System.out.print("Nhập số lượng: ");
            int quantity = scanner.nextInt();
            manager.addMaterial(new CrispyFlour(id, name, manufacturingDate, cost, quantity));
        } else {
            System.out.println("Loại vật liệu không hợp lệ.");
        }
    }

    private static void editMaterial(MaterialManager manager, Scanner scanner) {
        System.out.print("Nhập ID của vật liệu cần sửa: ");
        String id = scanner.nextLine();

        System.out.print("Nhập loại vật liệu mới (1. Meat, 2. CrispyFlour): ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ID mới: ");
        String newId = scanner.nextLine();

        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();

        System.out.print("Nhập ngày sản xuất mới (yyyy-MM-dd): ");
        LocalDate manufacturingDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Nhập giá mới: ");
        int cost = scanner.nextInt();

        if (type == 1) {
            System.out.print("Nhập trọng lượng mới: ");
            double weight = scanner.nextDouble();
            manager.editMaterial(id, new Meat(newId, name, manufacturingDate, cost, weight));
        } else if (type == 2) {
            System.out.print("Nhập số lượng mới: ");
            int quantity = scanner.nextInt();
            manager.editMaterial(id, new CrispyFlour(newId, name, manufacturingDate, cost, quantity));
        } else {
            System.out.println("Loại vật liệu không hợp lệ.");
        }
    }

    private static void removeMaterial(MaterialManager manager, Scanner scanner) {
        System.out.print("Nhập ID của vật liệu cần xóa: ");
        String id = scanner.nextLine();
        manager.removeMaterial(id);
        System.out.println("Đã xóa vật liệu có ID là " + id);

    }

}