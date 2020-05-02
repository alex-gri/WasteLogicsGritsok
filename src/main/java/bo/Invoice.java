package bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static Invoice findInvoiceByOrderID(List<Invoice> invoiceList, Integer orderID) {
        return invoiceList.stream()
                .filter(invoice -> invoice.getJobs().stream()
                        .anyMatch(job -> job.getOrderID().equals(orderID)))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "company='" + company + '\'' +
                ", invoiceAddress='" + invoiceAddress + '\'' +
                ", jobs=" + jobs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(company, invoice.company) &&
                Objects.equals(invoiceAddress, invoice.invoiceAddress) &&
                jobs.stream().anyMatch(job1 -> invoice.getJobs().stream().anyMatch(job2 -> job1.equals(job2)));
    }
}
