package com.sendriods.demo.Service;

import com.sendriods.demo.Dao.DirectorRepository;
import com.sendriods.demo.Dao.DivisionRepository;
import com.sendriods.demo.Domain.Director;

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

    public Director getDirectorBy
}
