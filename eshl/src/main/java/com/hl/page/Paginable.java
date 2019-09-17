package com.hl.page;

public interface Paginable {
    /**
     * 总记录数
     */
    int getTotalCount();

    /**
     * 总页数
     */
    int getTotalPage();

    /**
     * 每页记录数
     */
    int getPageSize();

    /**
     * 当前页号
     */
    int getPageNo();

    /**
     * 是否第一页
     */
    boolean isFirstPage();

    /**
     * 是否最后一页
     */
    boolean isLastPage();

    /**
     * 返回下页的页号
     */
    int getNextPage();

    /**
     * 返回上页的页号
     */
    int getPrePage();
}
