package fr.adriencasier.dgm.importer.testdata.rest.client;

import java.util.Date;

public class Donation {
    private String donationId;
    private String charityId;
    private Date createdAt;
    private String currency;
    private float amount;
    private String name;
    private String message;
    private String memberId;

    public String getDonationId() {
        return donationId;
    }

    public String getCharityId() {
        return charityId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getMemberId() {
        return memberId;
    }
}
