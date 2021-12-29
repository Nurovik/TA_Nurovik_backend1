package com.blfc.backend.Contrlollers;
import com.blfc.backend.Models.Team;
import com.blfc.backend.Service.TeamService;
import com.blfc.backend.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/blfc")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    public Map<String, Object> getallpemain(

    ){


        Map<String, Object> response = new HashMap<String, Object>();
        List<Team> teamlist = teamService.getall();
        if(teamlist.size()>0){
            response.put(Constants.LIST, teamlist);
            response.put(Constants.STATUS, Constants.STATUS_DATAFOUND);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);

        }else{

            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
        }

        return response;

    }

    @GetMapping("/teamname")
    public Map<String,Object> GetByid(

            @RequestParam("namateam")  String namateam

    ){
        Map<String, Object> response = new HashMap<>();
        Team mteam = new Team();

        mteam = teamService.Getbyid(namateam);


        if(mteam != null){

            response.put(Constants.DATA_KEY, mteam);
            response.put(Constants.STATUS, Constants.SUCCESS);
            response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);


        }else{
            response.put(Constants.STATUS,Constants.STATUS_DATANOTFOUND);
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);

        }



        return response;
    }
}
