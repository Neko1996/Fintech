package com.example.fintech.stock.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Column(name="date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="open",nullable = false)
    private double open;
    @Column(name="high",nullable = false)
    private double high;
    @Column(name="low",nullable = false)
    private double low;
    @Column(name="close",nullable = false)
    private double close;
    @Column(name="cname",nullable = false)
    private String cname;

    @Column(name="adjclose",nullable = false)
    private double adjclose;
    @Column(name="volume",nullable = false)
    private Long volume;

    public Stock(){}
    public Stock(Date  date, double open, double high,double low,double close,double adjclose,Long volume,String cname){
        this.date=date;
        this.open=open;
        this.high=high;
        this.low=low;
        this.close=close;
        this.adjclose=adjclose;
        this.volume=volume;
        this.cname=cname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date  getDate() {
        return date;
    }

    public void setDate(Date  date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getAdjclose() {
        return adjclose;
    }

    public void setAdjclose(double adjclose) {
        this.adjclose = adjclose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

}
