package com.example.testnizadatak.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "token")
public class Token
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false)
    private long id;

    @Column(name="MARK", nullable = false)
    private String mark;

    @Column(name="VALUE", nullable = false)
    private float value;

    @Column(name="BEGINDATE", nullable = false)
    private Date beginDate;

    @Column(name="ENDDATE", nullable = false)
    private Date endDate;

    @Column(name="ACTIVATED", nullable = false)
    private boolean activated;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount owner;

    public Token(String mark, float value, Date beginDate, Date endDate, boolean activated, UserAccount owner)
    {
        this.mark = mark;
        this.value = value;
        this.activated = activated;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.owner = owner;
    }

    public Token()
    {
    }

    public long getId()
    {
        return id;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }

    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public boolean isActivated()
    {
        return activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public UserAccount getOwner()
    {
        return owner;
    }

    public void setOwner(UserAccount owner)
    {
        this.owner = owner;
    }

    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return "Token{" + "id = " + id + ", mark = '" + mark + ", value = " + value +  ", beginDate = " + beginDate +
                ", endDate = " + endDate +  ", activated = " + activated  +  ", owner = " + owner + '}';
    }
}
