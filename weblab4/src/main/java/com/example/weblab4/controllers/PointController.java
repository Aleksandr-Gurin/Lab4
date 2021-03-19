package com.example.weblab4.controllers;


import com.example.weblab4.model.Point;
import com.example.weblab4.model.User;
import com.example.weblab4.requests.ListDTO;
import com.example.weblab4.requests.PointDTO;
import com.example.weblab4.services.PointService;
import com.example.weblab4.services.UserService;
import com.example.weblab4.utils.sec.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/point")
public class PointController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final PointService pointService;

    @Autowired
    public PointController(UserService userService, JWTUtil jwtUtil, PointService pointService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.pointService = pointService;
    }


    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> addPoint(@Valid @RequestBody ListDTO data, HttpServletRequest req){
        User user = userService.findByLogin(jwtUtil.getUsername(jwtUtil.resolveToken(req)));
        List<Point> point = data.getPoints().stream()
                .map(this::shittyMethod)
                .collect(Collectors.toList());
        point.forEach(p ->
        {
            p.setLogin(user.getLogin());
            pointService.addPoint(p);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Point>> getPoints(HttpServletRequest req){
        User user = userService.findByLogin(jwtUtil.getUsername(jwtUtil.resolveToken(req)));
        return new ResponseEntity<>(pointService.getAllPointByLogin(user.getLogin()), HttpStatus.OK);
    }


    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> deletePoints(HttpServletRequest req){
        User user = userService.findByLogin(jwtUtil.getUsername(jwtUtil.resolveToken(req)));
        pointService.deleteAllByLogin(user.getLogin());
        return new ResponseEntity<>("Все ваши точки удалены", HttpStatus.OK);
    }


    private Point shittyMethod(PointDTO p){
        @NotNull @Min(value = -4) @Max(value = 4)double x = Double.parseDouble(p.getX());
        @NotNull @Min(value = -3) @Max(value = 3)double y = Double.parseDouble(p.getY());
        @NotNull @Min(value = -2) @Max(value = 2)double r = Double.parseDouble(p.getR());
        return new Point(x, y, r);
    }



}
