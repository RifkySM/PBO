package polymorph_task;

public class SameDayDelivery extends Delivery {

    private static final double BASE_COST       = 20000;
    private static final double COST_PER_KG     = 4000;
    private static final double COST_PER_KM     = 1200;

    public SameDayDelivery(double weight, double distance) {
        super(weight, distance, "Same Day");
    }

    @Override
    public String getEstimation() {
        return "6 - 10 hour(s)";
    }

    @Override
    public double getCost() {
        return BASE_COST + (COST_PER_KG * getWeight()) + (COST_PER_KM * getDistance());
    }
}
