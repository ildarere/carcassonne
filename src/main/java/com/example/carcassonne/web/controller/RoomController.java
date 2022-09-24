package com.example.carcassonne.web.controller;

import com.example.carcassonne.domain.model.grid.GridSpot;
import com.example.carcassonne.domain.model.tile.TIleInf;
import com.example.carcassonne.domain.model.tile.Tile;
import com.example.carcassonne.domain.model.UserData;
import com.example.carcassonne.domain.model.СhatMessage;
import com.example.carcassonne.domain.service.ChatService;
import com.example.carcassonne.domain.service.RoomService;
import com.example.carcassonne.domain.service.UserService;

import com.example.carcassonne.domain.service.game.control.ControllerFacade;
import com.example.carcassonne.domain.service.game.control.MainController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.example.carcassonne.domain.model.tile.TileType.CastleCenter;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;
    @Autowired
    ChatService chatService;
    @Autowired
    ControllerFacade controllerFacade;

    @GetMapping("/room{id}")
    public String  getById(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        if(!roomService.isRoomWithIdExist(id)){
            model.addAttribute("message", "комнаты не существует");
            return ("redirect:/gameLobby");
        }else if(roomService.isRoomReady(id)){
            model.addAttribute("message", "комнаты заполнена");
            return ("redirect:/gameLobby");
        }else {
            return ("/room");
        }

    }
    @MessageMapping("/room{id}/hello")
    @SendTo("/topic/room{id}/connected")
    public List<UserData> connected(@Payload String message,@DestinationVariable("id") int id, Principal principal) throws Exception {
        List<UserData> userList = new ArrayList<>();

        UserData userData= userService.findDataByEmail(principal.getName()).get();
        userList.add(userData);
        userList.addAll(userService.findUsersByRoom(id));
        roomService.addUserInRoom(Math.toIntExact(userData.getId()), id);
        System.out.println(principal.getName());
        System.out.println(userData.toString());


        return userList;
    }
    @MessageMapping("/room{id}/userDisconnected")
    @SendTo("/topic/room{id}/userDisconnected")
    public int userDisconnect(@Payload int userId,@DestinationVariable("id") int id) throws Exception {
        //delete from db
        roomService.deleteUserFromRoom(userId);
        UserData userData= userService.findDataById((long) userId);
        System.out.println(userData.toString());
        return userId;
    }

    @MessageMapping("/room{id}/chatSendMsg")
    @SendTo("/topic/room{id}/chatGetMsg")
    public СhatMessage chatMsg(@Payload СhatMessage chatMessage, @DestinationVariable("id") int id) throws Exception {

        System.out.println(chatMessage.toString());
        return chatMessage;
    }
    @MessageMapping("/room{id}/getNewTile")
    @SendTo("/topic/room{id}/getTile")
    public Tile getTile( @Payload String tilePos, @DestinationVariable("id") int id) throws Exception {
        Tile tile = new Tile(CastleCenter);

        return tile;
    }
    @MessageMapping("/room{id}/gameStart")
    @SendTo("/topic/room{id}/placeTile")
    public String StartTile(@DestinationVariable("id") int id) throws Exception {
        GridSpot startSpot = controllerFacade.startGame();
        System.out.println(startSpot.getTile().toString());
        //roomService.setRoomReady(id);
        List<GridSpot> highlight = controllerFacade.highlightSurroundings(startSpot);
        StringBuilder sb= new StringBuilder();
        for (GridSpot spot: highlight) {
            sb.append(spot.getX()).append("%").append(spot.getY()).append("@");
        }
        return startSpot.getTile().getInf()+"//"+sb.toString();
    }
    @PostMapping("/room{id}/getNewTileForPlayer")
    @ResponseBody
    public String getTile(@PathVariable int id ){
        Tile tile = controllerFacade.requestPlacingState();
        return tile.getType().toString()+"%"+tile.getRotationLimit();
    }
    @MessageMapping("/room{id}/isPlaceTileOnSpot")
    @SendTo("/topic/room{id}/placeTile")
    public String isPlace(@DestinationVariable("id") int id, @Payload TIleInf tile) throws Exception {
        //System.out.println(tile.toString()+"SSSSSSSSSSSSSAAaaaaaaaaaaaaaaaaas");
        Tile newTile = controllerFacade.requestTilePlacement(tile);
        if(newTile == null){
            return null;
        }
        List<GridSpot> highlight = controllerFacade.highlightSurroundings(newTile.getGridSpot());
        StringBuilder sb= new StringBuilder();
        for (GridSpot spot: highlight) {
            sb.append(spot.getX()).append("%").append(spot.getY()).append("@");
        }
        return newTile.getInf()+"//"+sb.toString();
    }
//    @PostMapping( value = "/room{id}/isPlaceTileOnSpot")
//    @ResponseBody
//    public String isPlace(@PathVariable int id , @RequestBody TIleInf tile){
//        Tile newTile = controllerFacade.requestTilePlacement(tile);
//        List<GridSpot> highlight = controllerFacade.highlightSurroundings(newTile.getGridSpot());
//        StringBuilder sb= new StringBuilder();
//        for (GridSpot spot: highlight) {
//            sb.append(spot.getX()).append("%").append(spot.getY()).append("@");
//        }
//        return newTile.getInf()+"//"+sb.toString();
//    }

}
