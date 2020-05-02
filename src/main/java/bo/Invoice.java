package bo;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private String company;
    private String invoiceAddress;
    private List<Job> jobs = new ArrayList<>();

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void addJob(Job job) {
        this.jobs.add(job);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "company='" + company + '\'' +
                ", invoiceAddress='" + invoiceAddress + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
