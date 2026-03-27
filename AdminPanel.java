package Exercise7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again (enter a number 1-5)");
                continue;
            }

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
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("\nHow many users would you like to add? ");
        int numUsers;
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

        for (int i = 0; i < numUsers; i++) {
            System.out.println("\n--- Enter details for User " + (i + 1) + " ---");
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
                if (feedback.isEmpty()) feedback = "NULL";

                StringBuilder tripSB = new StringBuilder();
                tripSB.append("Date: ").append(tripDate)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(dest)
                        .append(", Fare (€): ").append(fare)
                        .append(", Feedback: ").append(feedback);
                lastThreeTrips[j] = tripSB.toString();
            }

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
            System.out.println(user); 
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to remove");
            return;
        }
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

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("\nNo registered users to update"); 
            return;
        }
        System.out.print("\nEnter email address of the user to update: ");
        String targetEmail = scanner.nextLine();
        RegisteredUsers targetUser = null;

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

        System.out.println("\n--- Update User Details (press ENTER for no change, enter 0 for number fields) ---");
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

        System.out.print("New Card Number: (enter 0 for no change) ");
        String newCardNum = scanner.nextLine();
        if (!newCardNum.equals("0")) targetUser.setCardNumber(newCardNum);

        System.out.print("New CVV: (enter 0 for no change) ");
        String newCvv = scanner.nextLine();
        if (!newCvv.equals("0")) targetUser.setCvv(newCvv);

        System.out.println("User details updated successfully!");
    }
}



