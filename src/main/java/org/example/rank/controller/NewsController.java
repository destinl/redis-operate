package org.example.rank.controller;

import org.example.rank.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1516:45
 */
@Controller
@RequestMapping("/home")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/")
//    @Cacheable(value = "homePage", condition = "#root.caches[0].name == 'homePage'")
    @Cacheable(value = "homePageCache", key = "#root.method.name") // 缓存名称为 homePageCache
    public String homePage(Model model) {
        // 尝试从缓存中获取页面
        model.addAttribute("newsList", newsService.getNewsList());
        return "home";
    }
}
