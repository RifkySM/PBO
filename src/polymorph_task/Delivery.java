package polymorph_task;

public abstract class Delivery {
    private double weight       = 0.0;
    private double distance     = 0.0;
    private String serviceType;

    public Delivery(double weight, double distance, String serviceType) {
        setDistance(distance);
        setWeight(weight);
        setServiceType(serviceType);
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("weight cannot be negative");
        }
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        if(distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        this.distance = distance;
    }

    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        if (serviceType == null || serviceType.isEmpty()) {
            throw new IllegalArgumentException("serviceType cannot be null");
        }
        this.serviceType = serviceType;
    }


    public void showDetails() {
        System.out.println("\n========================================");
        System.out.println("        Delivery Detail");
        System.out.println("========================================");
        System.out.println("Service Type        : " + getServiceType());
        System.out.println("Package Weight      : " + getWeight() + " kg");
        System.out.println("Delivery Distance   : " + getDistance()+ " km");
        System.out.println("----------------------------------------");
        System.out.println("Cost                : Rp " + String.format("%,.0f", getCost()));
        System.out.println("Arrival Estimation  : " + getEstimation());
        System.out.println("========================================");
    }

    public abstract String getEstimation();
    public abstract double getCost();
}

