package polymorph_task;

public class ExpressDelivery extends Delivery {
    private static final double BASE_COST       = 10000;
    private static final double COST_PER_KG     = 3000;
    private static final double COST_PER_KM     = 800;

    public ExpressDelivery(double weight, double distance) {
        super(weight, distance, "Express");
    }

    @Override
    public String getEstimation() {
        return "1 - 2 day(s)";
    }

    @Override
    public double getCost() {
        return BASE_COST + (COST_PER_KG * getWeight()) + (COST_PER_KM * getDistance());
    }
}
