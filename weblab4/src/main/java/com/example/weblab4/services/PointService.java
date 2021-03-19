package com.example.weblab4.services;


import com.example.weblab4.model.Point;
import com.example.weblab4.repositories.PointRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }
    @Transactional
    public List<Point> getAllPointByLogin(String login){
        return pointRepository.getAllByLogin(login);
    }

    @Transactional
    public void addPointByLogin(Point point, String login){
        point.setLogin(login);
        pointRepository.save(point);
    }

    @Transactional
    public void addPoint(Point point){
        pointRepository.save(point);
    }

    @Transactional
    public void deleteAllByLogin(String login){
        pointRepository.deleteAllByLogin(login);
    }
}

