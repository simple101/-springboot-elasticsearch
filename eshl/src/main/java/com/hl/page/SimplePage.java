package com.hl.page;

import lombok.Data;

@Data
public class SimplePage implements Paginable {
    private static final int DEF_COUNT = 20;
    private int totalCount = 0;
    public int pageSize = 20;
    public int pageNo = 1;
    private int filterNo;

    public SimplePage() {
    }

    public SimplePage(int pageNo, int pageSize, int totalCount) {
        if (totalCount <= 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
        if (pageNo > 0) {
            this.pageNo = pageNo;
        }
        if ((this.pageNo - 1) * this.pageSize >= totalCount) {
            this.pageNo = totalCount / pageSize;
            if (this.pageNo == 0) {
                this.pageNo = 1;
            }
        }
    }

    /**
     * 调整分页参数，使合理化
     */
    public void adjustPage() {
        if (totalCount <= 0) {
            totalCount = 0;
        }
        if (pageSize <= 0) {
            pageSize = DEF_COUNT;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if ((pageNo - 1) * pageSize >= totalCount) {
            pageNo = totalCount / pageSize;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0) {
            totalPage++;
        }
        return totalPage;
    }

    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }
}
