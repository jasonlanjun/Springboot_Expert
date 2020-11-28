package com.junlan.domain;

import java.util.List;

public class BillingItemsEntity {

    private List<BillEntity> billingItems;

    public List<BillEntity> getBillingItems() {
        return billingItems;
    }

    public void setBillingItems(List<BillEntity> billingItems) {
        this.billingItems = billingItems;
    }

}
