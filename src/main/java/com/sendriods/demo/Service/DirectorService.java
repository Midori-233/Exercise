package com.sendriods.demo.Service;

import com.sendriods.demo.Dao.DirectorRepository;
import com.sendriods.demo.Dao.DivisionRepository;
import com.sendriods.demo.Domain.Director;
import com.sendriods.demo.Domain.Division;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    final private DirectorRepository directorRepository;
    final private DivisionRepository divisionRepository;

    public DirectorService(DirectorRepository directorRepository, DivisionRepository divisionRepository) {
        this.directorRepository = directorRepository;
        this.divisionRepository = divisionRepository;
    }

    public Director addDirector(Director director) {
        return directorRepository.save(director);
    }

    public Director delete(Director director) {
        directorRepository.delete(director);
        return director;
    }

    public Director update(Director director) {
        return directorRepository.save(director);
    }

    public Optional<Director> getDirectorById(Long id) {
        return directorRepository.getDirectorById(id);
    }

    public Optional<Director> getDirectorByName(String name) {
        return directorRepository.getDirectorByName(name);
    }

    public Director setDivision(Division division, Director director) {
        director.setDivision(division);
        return directorRepository.save(director);
    }

    public List<Director> getAllDirector() {
        return directorRepository.findAll();
    }
}
