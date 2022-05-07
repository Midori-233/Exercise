package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Director;
import com.sendriods.demo.Service.DirectorService;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/director")
public class DirectorController {
    final private DivisionService divisionService;
    final private DirectorService directorService;

    public DirectorController(DivisionService divisionService, DirectorService directorService) {
        this.divisionService = divisionService;
        this.directorService = directorService;
    }

    @PostMapping("/addDirector")
    public Result addDirector(@RequestBody Director director) {
        return Result.success(directorService.addDirector(director));
    }

    @GetMapping("/getAllDirector")
    public Result<List> getAll() {
        return Result.success(directorService.getAllDirector(), "succeed!");
    }

    @PutMapping("/update")
    public Result updateDirector(@RequestBody Director director) {
        return Result.success(directorService.update(director), "succeed!");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody Director director) {
        return Result.success(directorService.delete(director), "succeed!");
    }

    @GetMapping("/getDirectorById")
    public Result getDirectorById(@RequestParam Optional<Long> id) {
        return id.map(
                l -> Result.success(directorService.getDirectorById(id.get()))
        ).orElse(Result.error("432", "No Valid Input"));
    }

    @GetMapping("/getDirectorByName")
    public Result gerDirectorByName(@RequestParam Optional<String> name) {
        return name.map(
                s -> Result.success(directorService.getDirectorByName(name.get()))
        ).orElse(Result.error("432", "No Valid Input"));
    }
}
