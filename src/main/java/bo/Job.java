package bo;

import java.util.Objects;

public class Job extends PriceEntity {

    private Integer orderID;
    private String grade;
    private String weight;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Job{" +
                "orderID=" + orderID +
                ", grade='" + grade + '\'' +
                ", weight='" + weight + '\'' + " " +
                super.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Job job = (Job) o;
        if (o instanceof Job) {
            return super.equals(o) && Objects.equals(orderID, job.orderID) &&
                    Objects.equals(grade, job.grade) &&
                    Objects.equals(weight, job.weight);
        }
        return false;
    }
}
