package com.example.fintech.stock.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class IndexP implements Serializable {
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
    @Column(name="type",nullable = false)
    private String type;

    @Column(name="adjclose",nullable = false)
    private double adjclose;
    @Column(name="volume",nullable = false)
    private Long volume;

    public IndexP(){}
    public IndexP(Date  date, double open, double high, double low, double close, double adjclose, Long volume, String type){
        this.date=date;
        this.open=open;
        this.high=high;
        this.low=low;
        this.close=close;
        this.adjclose=adjclose;
        this.volume=volume;
        this.type=type;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
