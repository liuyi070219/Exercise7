package Exercise7;
import java.util.Arrays;
    public class RegisteredUsers {
        // 基础属性（与UserRegistration一致）
        private String fullName;
        private String emailAddress;
        private String dateOfBirth;
        private String cardNumber;
        private String cardExpiryDate;
        private String cardProvider;
        private String cvv;
        private String userType;
        // 最近三次行程数组
        private String[] lastThreeTrips;

        // 构造器
        public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                               String cardNumber, String cardExpiryDate, String cardProvider,
                               String cvv, String userType, String[] lastThreeTrips) {
            this.fullName = fullName;
            this.emailAddress = emailAddress;
            this.dateOfBirth = dateOfBirth;
            this.cardNumber = cardNumber;
            this.cardExpiryDate = cardExpiryDate;
            this.cardProvider = cardProvider;
            this.cvv = cvv;
            this.userType = userType;
            this.lastThreeTrips = lastThreeTrips;
        }

        // Getter和Setter
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getEmailAddress() { return emailAddress; }
        public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

        public String getDateOfBirth() { return dateOfBirth; }
        public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

        public String getCardNumber() { return cardNumber; }
        public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

        public String getCardExpiryDate() { return cardExpiryDate; }
        public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }

        public String getCardProvider() { return cardProvider; }
        public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }

        public String getCvv() { return cvv; }
        public void setCvv(String cvv) { this.cvv = cvv; }

        public String getUserType() { return userType; }
        public void setUserType(String userType) { this.userType = userType; }

        public String[] getLastThreeTrips() { return lastThreeTrips; }
        public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }

        public String toString() {
            return "RegisteredUsers{" +
                    "fullName='" + fullName + '\'' +
                    ", emailAddress='" + emailAddress + '\'' +
                    ", dateOfBirth='" + dateOfBirth + '\'' +
                    ", cardNumber='" + cardNumber + '\'' +
                    ", cardExpiryDate='" + cardExpiryDate + '\'' +
                    ", cardProvider='" + cardProvider + '\'' +
                    ", cvv='" + cvv + '\'' +
                    ", userType='" + userType + '\'' +
                    ", lastThreeTrips=" + Arrays.toString(lastThreeTrips) +
                    '}';
        }
    }

