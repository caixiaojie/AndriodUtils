package com.yjhs.cbsd_commonlibrary.utils;

/**
 * author: Administrator
 * date: 2018/5/14 0014
 * desc:
 */

public class PageQuery {
    public  int pageNumber=1;
    public int pageSize=20;


    public boolean isFirstPage(){
        return pageNumber==1;
    }

    public void resetPage(){
        pageNumber=1;
    }

    public void nextPage(){
        pageNumber++;
    }


    public int getPage() {
        return pageNumber;
    }

    public void setPage(int page) {
        this.pageNumber = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public String toString() {
        return String.format("page=%d&pagesize=%d",pageNumber,pageSize);
    }
}
