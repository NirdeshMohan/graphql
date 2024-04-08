package com.rss.grapghql.service;

import com.rss.grapghql.model.Player;
import com.rss.grapghql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private final List<Player> players = new ArrayList<>();
    AtomicInteger ai = new AtomicInteger(0);
    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id){
        return players.stream().filter(player-> Objects.equals(player.id(), id)).findFirst();
    }

    public Player create(String name, Team team){
        Player player = new Player(ai.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id){
        Player player = players.stream().filter(p -> p.id() == id).findFirst().orElseThrow(IllegalArgumentException::new);
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team){
        Player updatedPayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(player-> Objects.equals(player.id(), id)).findFirst();
        if(optional.isPresent()){
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index, player);
        }else{
            throw new IllegalArgumentException("Invalid Palyer");
        }

        return updatedPayer;
    }

    @PostConstruct
    private void init(){
        players.add(new Player(ai.incrementAndGet(),"Dhoni",Team.CSK));
        players.add(new Player(ai.incrementAndGet(),"Kohli",Team.RCB));
        players.add(new Player(ai.incrementAndGet(),"Pant",Team.DC));
        players.add(new Player(ai.incrementAndGet(),"Gill",Team.GT));
        players.add(new Player(ai.incrementAndGet(),"Rohith",Team.MI));
        players.add(new Player(ai.incrementAndGet(),"SKY",Team.MI));
    }
}
