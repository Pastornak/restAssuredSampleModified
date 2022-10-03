package models;

public class QueryOptions {
    public String orderType;
    public int page;
    public boolean pagination;
    public int size;
    public String sortBy;

    // setting some of the default values
    public QueryOptions() {
        this.page = 1;
        this.pagination = true;
        this.size = 10;
    }

    public QueryOptions setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public QueryOptions setPage(int page) {
        this.page = page;
        return this;
    }

    public QueryOptions setPagination(boolean pagination) {
        this.pagination = pagination;
        return this;
    }

    public QueryOptions setSize(int size) {
        this.size = size;
        return this;
    }

    public QueryOptions setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }
}