package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotNull(message = "Bid quantity is mandatory")
    private Double bidQuantity;

    @NotNull(message = "Ask quantity is mandatory")
    private Double askQuantity;

    @NotNull(message = "Bid is mandatory")
    private Double bid;

    @NotNull(message = "Ask is mandatory")
    private Double ask;

    @NotBlank(message = "Benchmark is mandatory")
    private String benchmark;

    @NotBlank(message = "Bid list date is mandatory")
    private String ruleNameDate;

    @NotBlank(message = "Commentary is mandatory")
    private String commentary;

    @NotBlank(message = "Security is mandatory")
    private String security;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotBlank(message = "Trader is mandatory")
    private String trader;

    @NotBlank(message = "Book is mandatory")
    private String book;

    @NotBlank(message = "Creation name is mandatory")
    private String creationName;

    @NotBlank(message = "Creation date is mandatory")
    private String creationDate;

    @NotBlank(message = "Revision name is mandatory")
    private String revisionName;

    @NotBlank(message = "Revision date is mandatory")
    private String revisionDate;

    @NotBlank(message = "Deal name is mandatory")
    private String dealName;

    @NotBlank(message = "Deal type is mandatory")
    private String dealType;

    @NotBlank(message = "Source list id is mandatory")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    private String side;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getruleNameDate() {
        return ruleNameDate;
    }

    public void setruleNameDate(String ruleNameDate) {
        this.ruleNameDate = ruleNameDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
