package models;

public class LoyaltyProgram {
    String loyaltyLevel;
    float totalPointsValueUnlockedAt;
    float boosterValue;

    public LoyaltyProgram() { }

    public String getLoyaltyLevel() {
        return this.loyaltyLevel;
    }

    public void setLoyaltyLevel(String loyaltyLevel) {
        this.loyaltyLevel = loyaltyLevel;
    }

    public float getTotalPointsValueUnlockedAt() {
        return this.totalPointsValueUnlockedAt;
    }

    public void setTotalPointsValueUnlockedAt(float totalPointsValueUnlockedAt) {
        this.totalPointsValueUnlockedAt = totalPointsValueUnlockedAt;
    }

    public float getBoosterValue() {
        return this.boosterValue;
    }

    public void setBoosterValue(float boosterValue) {
        this.boosterValue = boosterValue;
    }

    @Override
    public String toString() {
        return "{" +
            " loyaltyLevel='" + getLoyaltyLevel() + "'" +
            ", totalPointsValueUnlockedAt='" + getTotalPointsValueUnlockedAt() + "'" +
            ", boosterValue='" + getBoosterValue() + "'" +
            "}";
    }

}
