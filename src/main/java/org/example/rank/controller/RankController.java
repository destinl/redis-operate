package org.example.rank.controller;

import org.example.rank.service.RankService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;

/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/6/1111:55
 */

@RestController
@RequestMapping("/rank")
public class RankController {

    @Resource
    private RankService rankService;

    @PostMapping("/update/{userId}/{score}")
    public void updateScore(@PathVariable String userId, @PathVariable double score){
        rankService.updateScore(userId, score);
    }

    @GetMapping("/update/{start}/{stop}")
    public Set<String> getRank(@PathVariable int start, @PathVariable int stop){
        return rankService.getLeaders(start,stop);
    }
}
