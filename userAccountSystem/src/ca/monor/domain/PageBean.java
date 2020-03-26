package ca.monor.domain;

import java.util.List;

public class PageBean<E> {
    private int currentPage;     // 当前页面
    private List<E> list;        // 每页的数据
    private int totalCount;      // 总的记录数量
    private int row;             // 每页上显示的条目数量（行数）
    private int totalPages;      // 总页码数

    public PageBean() { }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", list=" + list +
                ", totalCount=" + totalCount +
                ", row=" + row +
                ", totalPages=" + totalPages +
                '}';
    }
}
