package com.atguigu.pojo.vo;

public class ProductInfoVO {
    //商品名称条件的封装
    private String pname;
    //商品类型条件的封装
    private String typeid;

    //最低价格的封装
    private String lprice;

    //最高价格的封装
    private String hprice;

    public ProductInfoVO() {
    }

    public ProductInfoVO(String pname, String typeid, String lprice, String hprice) {
        this.pname = pname;
        this.typeid = typeid;
        this.lprice = lprice;
        this.hprice = hprice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getLprice() {
        return lprice;
    }

    public void setLprice(String lprice) {
        this.lprice = lprice;
    }

    public String getHprice() {
        return hprice;
    }

    public void setHprice(String hprice) {
        this.hprice = hprice;
    }

    @Override
    public String toString() {
        return "ProductInfoVO{" +
                "pname='" + pname + '\'' +
                ", typeid='" + typeid + '\'' +
                ", lprice='" + lprice + '\'' +
                ", hprice='" + hprice + '\'' +
                '}';
    }
}
