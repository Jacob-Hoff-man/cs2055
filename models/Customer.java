package models;

public class Customer {
    int customerId;
    String firstName;
    String lastName;
    String midInitial;
    String birthMonth;
    String birthDay;
    String phoneNumber;
    String phoneType;
    String loyaltyLevel;
    float currentPoints;
    float totalPoints;

    public Customer() {}

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidInitial() {
        return this.midInitial;
    }

    public void setMidInitial(String midInitial) {
        this.midInitial = midInitial;
    }

    public String getBirthMonth() {
        return this.birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getLoyaltyLevel() {
        return this.loyaltyLevel;
    }

    public void setLoyaltyLevel(String loyaltyLevel) {
        this.loyaltyLevel = loyaltyLevel;
    }

    public float getCurrentPoints() {
        return this.currentPoints;
    }

    public void setCurrentPoints(float currentPoints) {
        this.currentPoints = currentPoints;
    }

    public float getTotalPoints() {
        return this.totalPoints;
    }

    public void setTotalPoints(float totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "{" +
            " customerId='" + getCustomerId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", midInitial='" + getMidInitial() + "'" +
            ", birthMonth='" + getBirthMonth() + "'" +
            ", birthDay='" + getBirthDay() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", phoneType='" + getPhoneType() + "'" +
            ", loyaltyLevel='" + getLoyaltyLevel() + "'" +
            ", currentPoints='" + getCurrentPoints() + "'" +
            ", totalPoints='" + getTotalPoints() + "'" +
            "}\n";
    }
}
