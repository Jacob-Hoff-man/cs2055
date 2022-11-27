package models;

public class Coffee {
    // Coffee_Id int NOT NULL,
    // Coffee_Name varchar(50) NOT NULL,
    // Description VARCHAR(250),
    // Country varchar(60),
    // Intensity int NOT NULL CHECK (Intensity >= 1 AND Intensity <= 12),
    // Price float NOT NULL CHECK (Price >= 0),
    // Redeem_Points float NOT NULL CHECK (Redeem_Points >= 0),
    // Reward_Points float NOT NULL CHECK (Reward_Points >= 0),
    // CONSTRAINT C_PK PRIMARY KEY (Coffee_Id),
    // CONSTRAINT UQ_Cname UNIQUE (Coffee_Name)

    int coffeeId;
    String coffeeName;
    String description;
    String country;
    int intensity;
    float price;
    float redeemPoints;
    float rewardPoints;

    public Coffee() { }

    public int getCoffeeId() {
        return this.coffeeId;
    }

    public void setCoffeeId(int coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getCoffeeName() {
        return this.coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIntensity() {
        return this.intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRedeemPoints() {
        return this.redeemPoints;
    }

    public void setRedeemPoints(float redeemPoints) {
        this.redeemPoints = redeemPoints;
    }

    public float getRewardPoints() {
        return this.rewardPoints;
    }

    public void setRewardPoints(float rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    @Override
    public String toString() {
        return "{" +
            " coffeeId='" + getCoffeeId() + "'" +
            ", coffeeName='" + getCoffeeName() + "'" +
            ", description='" + getDescription() + "'" +
            ", country='" + getCountry() + "'" +
            ", intensity='" + getIntensity() + "'" +
            ", price='" + getPrice() + "'" +
            ", redeemPoints='" + getRedeemPoints() + "'" +
            ", rewardPoints='" + getRewardPoints() + "'" +
            "}";
    }


}
