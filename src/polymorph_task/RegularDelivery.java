package polymorph_task;

public class RegularDelivery extends Delivery {
    private static final double BASE_COST       = 5000;
    private static final double COST_PER_KG     = 2000;
    private static final double COST_PER_KM     = 500;

    public RegularDelivery(double weight, double distance) {
        super(weight, distance, "Regular");
    }

    @Override
    public String getEstimation() {
        return "3 - 5 day(s)";
    }

    @Override
    public double getCost() {
        return BASE_COST + (COST_PER_KG * getWeight()) + (COST_PER_KM * getDistance());
    }

}
