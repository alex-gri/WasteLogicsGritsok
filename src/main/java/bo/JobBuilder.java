package bo;

public class JobBuilder {

    private Job job = new Job();

    public JobBuilder setOrderID(Integer orderID) {
        job.setOrderID(orderID);
        return this;
    }

    public JobBuilder setGrade(String grade) {
        job.setGrade(grade);
        return this;
    }

    public JobBuilder setWeight(String weight) {
        job.setWeight(weight);
        return this;
    }

    public JobBuilder setFlatChargeLineTotal(String price) {
        job.setFlatChargeLineTotal(price);
        return this;
    }

    public JobBuilder setPerTonneLineTotal(String price) {
        job.setPerTonneLineTotal(price);
        return this;
    }

    public JobBuilder setItemLineTotal(String price) {
        job.setItemLineTotal(price);
        return this;
    }

    public Job build() {
        return job;
    }
}
