package com.automation.qa.util;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class SearchQuerySuccessResponse {
    @JsonProperty
    private float total_count;
    @JsonProperty
    private boolean incomplete_results;
    @JsonProperty
    ArrayList< Object > items = new ArrayList < Object > ();

// Getter Methods
public ArrayList<Object> getItems() {
    return items;
}
    public float getTotal_count() {
        return total_count;
    }

    public boolean getIncomplete_results() {
        return incomplete_results;
    }

    // Setter Methods

    public void setTotal_count(float total_count) {
        this.total_count = total_count;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public void setItems(ArrayList<Object> items) {
        this.items = items;
    }


}
