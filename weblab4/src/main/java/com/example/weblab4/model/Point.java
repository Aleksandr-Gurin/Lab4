package com.example.weblab4.model;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.weblab4.model.Point.*;


@Entity @Data
@Table(name = "points")
//@NamedQueries({
//        @NamedQuery(name = FIND_BY_LOGIN, query = "SELECT p FROM Point p WHERE p.login = :login"),
//        @NamedQuery(name = FIND_ALL, query = "SELECT p FROM Point p"),
//        @NamedQuery(name = DELETE_ALL_BY_LOGIN, query = "DELETE FROM Point p WHERE p.login = :login")
//})
public class Point implements Serializable{


//    public static final String FIND_ALL = "Point.findAll";
//    public static final String FIND_BY_LOGIN = "Point.findByLogin";
//    public static final String DELETE_ALL_BY_LOGIN = "Point.deleteAll";

    @Id
    @Column(nullable = false, unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.time = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(new Date(System.currentTimeMillis()));
        checkResult(x, y, r);
    }

    //Text [-3 ... 5] для координаты по оси X,
    //Text [-5 ... 3] для координаты по оси Y,
    //Text [-3 ... 5] для задания радиуса области

    @Column @NotNull @Min(value = -4) @Max(value = 4) private double x;

    @Column @NotNull @Min(value = -4) @Max(value = 4) private double y;

    @Column @NotNull @Min(value = -2) @Max(value = 2) private double r;

    @Column(nullable = false)
    private boolean result;

    @Column(name = "currenttime")
    private String time;

    @Column(name = "owner")
    private String login;


    public Point() {

    }

    private void checkResult(double x, double y, double r){
        this.result = checkRadius(x, y, r);

    }

    private boolean checkRadius(double x, double y, double r){
        if (r<0){
            return (  (x >= 0 && y >= 0 && x<=-r && y<=-r/2)// 3 Четверть
                    || (x <= 0 && y <= 0 && x*x+y*y <= r*r)// 3 Четверть
                    || (x >= 0 && y <= 0 && y>=x/2+r/2)// 2 Четверть
            );
        }else
        {
            return (  (x >= 0 && y >= 0 && x<=r && y<=r/2)// 1 Четверть
                    || (x <= 0 && y <= 0 && x*x+y*y <= r*r)// 3 Четверть
                    || (x >= 0 && y <= 0 && y>=x/2-r/2)// 2 Четверть
            );
        }

    }

}
