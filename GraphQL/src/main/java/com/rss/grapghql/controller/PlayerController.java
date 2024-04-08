package com.rss.grapghql.controller;

import com.rss.grapghql.model.Player;
import com.rss.grapghql.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Player> findAll(){
        return service.findAll();
    }

    @QueryMapping
    public Optional <Player> findOne(@Argument Integer id){
        return service.findOne(id);
    }
}
