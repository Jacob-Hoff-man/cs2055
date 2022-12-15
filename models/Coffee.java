package models;

import java.util.Objects;

public class Coffee {
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
        return "\n{" +
            "coffeeId='" + getCoffeeId() + "'" +
            ",\n coffeeName='" + getCoffeeName() + "'" +
            ",\n description='" + getDescription() + "'" +
            ",\n country='" + getCountry() + "'" +
            ",\n intensity='" + getIntensity() + "'" +
            ",\n price='" + getPrice() + "'" +
            ",\n redeemPoints='" + getRedeemPoints() + "'" +
            ",\n rewardPoints='" + getRewardPoints() + "'" +
            "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coffee)) {
            return false;
        }
        Coffee coffee = (Coffee) o;
        return coffeeId == coffee.coffeeId && Objects.equals(coffeeName, coffee.coffeeName) && Objects.equals(description, coffee.description) && Objects.equals(country, coffee.country) && intensity == coffee.intensity && price == coffee.price && redeemPoints == coffee.redeemPoints && rewardPoints == coffee.rewardPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeId, coffeeName, description, country, intensity, price, redeemPoints, rewardPoints);
    }


}
