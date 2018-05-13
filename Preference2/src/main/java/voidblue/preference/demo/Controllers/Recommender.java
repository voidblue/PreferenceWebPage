package voidblue.preference.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import voidblue.preference.demo.Models.PollData;
import voidblue.preference.demo.Models.SightSeeingSpot;
import voidblue.preference.demo.Service.RecommendService;

import java.util.ArrayList;

@Controller
public class Recommender {
    @Autowired
    RecommendService recommendService;




    @PostMapping("/recommender")
    public String recommend(Model model, @ModelAttribute PollData pollData){
        ArrayList<SightSeeingSpot> sightSeeingSpots = recommendService.getSightSeeingSpots(pollData);

        model.addAttribute("sightSeeingSpots",sightSeeingSpots);

        return "recommender";
    }








}