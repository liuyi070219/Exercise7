package Exercise7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // 公共方法：管理员操作菜单
    public void userManagementOptions() {
        while (true) {
            // 打印菜单
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Enter your choice: ");

            // 处理输入（防止非数字输入）
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again (enter a number 1-5)");
                continue;
            }

            // 分支调用对应方法
            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    System.out.println("Exiting Admin Panel... Thank you!");
                    scanner.close();
                    System.exit(0); // 退出程序
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    // 私有方法：添加新用户
    private void addNewUsers() {
        System.out.print("\nHow many users would you like to add? ");
        int numUsers;
        // 处理非数字输入
        try {
            numUsers = Integer.parseInt(scanner.nextLine());
            if (numUsers <= 0) {
                System.out.println("Please enter a positive number!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return;
        }

        // 循环添加指定数量的用户
        for (int i = 0; i < numUsers; i++) {
            System.out.println("\n--- Enter details for User " + (i + 1) + " ---");
            // 1. 输入基础信息
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Email Address: ");
            String email = scanner.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            System.out.print("Card Number: ");
            String cardNum = scanner.nextLine();
            System.out.print("Card Expiry Date (MM/YY): ");
            String cardExp = scanner.nextLine();
            System.out.print("Card Provider: ");
            String cardProv = scanner.nextLine();
            System.out.print("CVV: ");
            String cvv = scanner.nextLine();
            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            // 2. 输入最近3次行程，初始化数组
            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\n--- Enter Trip " + (j + 1) + " Details ---");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();
                System.out.print("Source: ");
                String source = scanner.nextLine();
                System.out.print("Destination: ");
                String dest = scanner.nextLine();
                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();
                System.out.print("Feedback (press ENTER for NULL): ");
                String feedback = scanner.nextLine();
                // 若反馈为空，设为NULL
                if (feedback.isEmpty()) feedback = "NULL";

                // 用StringBuilder拼接行程信息
                StringBuilder tripSB = new StringBuilder();
                tripSB.append("Date: ").append(tripDate)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(dest)
                        .append(", Fare (€): ").append(fare)
                        .append(", Feedback: ").append(feedback);
                // 转字符串存入数组
                lastThreeTrips[j] = tripSB.toString();
            }

            // 3. 创建RegisteredUsers对象并添加到集合
            RegisteredUsers newUser = new RegisteredUsers(fullName, email, dob, cardNum,
                    cardExp, cardProv, cvv, userType, lastThreeTrips);
            registeredUsersList.add(newUser);
            System.out.println("User " + (i + 1) + " added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to display");
            return;
        }
        System.out.println("\n--- All Registered Users ---");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user); // 调用toString()方法
        }
    }

    // 私有方法：删除注册用户（按邮箱）
    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to remove");
            return;
        }
        // 输入待删除邮箱
        System.out.print("\nEnter email address of the user to remove: ");
        String targetEmail = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equalsIgnoreCase(targetEmail)) {
                iterator.remove();
                found = true;
                System.out.println("User with email " + targetEmail + " removed successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    // 私有方法：更新注册用户（按邮箱）
    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to update"); // 修正题目笔误（原提示remove）
            return;
        }
        // 输入待更新邮箱
        System.out.print("\nEnter email address of the user to update: ");
        String targetEmail = scanner.nextLine();
        RegisteredUsers targetUser = null;

        // 查找匹配的用户
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equalsIgnoreCase(targetEmail)) {
                targetUser = user;
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }

        // 开始更新信息：字符串按回车保持原值，数字（卡号/CVV）输0保持原值
        System.out.println("\n--- Update User Details (press ENTER for no change, enter 0 for number fields) ---");
        // 1. 字符串类型属性
        System.out.print("New Full Name: (Press ENTER for no change) ");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()) targetUser.setFullName(newFullName);

        System.out.print("New Date of Birth (YYYY-MM-DD): (Press ENTER for no change) ");
        String newDob = scanner.nextLine();
        if (!newDob.isEmpty()) targetUser.setDateOfBirth(newDob);

        System.out.print("New Card Expiry Date (MM/YY): (Press ENTER for no change) ");
        String newCardExp = scanner.nextLine();
        if (!newCardExp.isEmpty()) targetUser.setCardExpiryDate(newCardExp);

        System.out.print("New Card Provider: (Press ENTER for no change) ");
        String newCardProv = scanner.nextLine();
        if (!newCardProv.isEmpty()) targetUser.setCardProvider(newCardProv);

        System.out.print("New User Type: (Press ENTER for no change) ");
        String newUserType = scanner.nextLine();
        if (!newUserType.isEmpty()) targetUser.setUserType(newUserType);

        // 2. 数字/卡号类属性（输0保持原值）
        System.out.print("New Card Number: (enter 0 for no change) ");
        String newCardNum = scanner.nextLine();
        if (!newCardNum.equals("0")) targetUser.setCardNumber(newCardNum);

        System.out.print("New CVV: (enter 0 for no change) ");
        String newCvv = scanner.nextLine();
        if (!newCvv.equals("0")) targetUser.setCvv(newCvv);

        System.out.println("User details updated successfully!");
    }
}



