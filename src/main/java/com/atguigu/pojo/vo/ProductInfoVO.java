package com.atguigu.pojo.vo;

public class ProductInfoVO {
    //商品名称条件的封装
    private String pname;
    //商品类型条件的封装
    private int typeid;

    //最低价格的封装
    private Integer lprice;

    //最高价格的封装
    private Integer hprice;

    public ProductInfoVO() {
    }

    public ProductInfoVO(String pname, int typeid, Integer lprice, Integer hprice) {
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

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public Integer getLprice() {
        return lprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
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
