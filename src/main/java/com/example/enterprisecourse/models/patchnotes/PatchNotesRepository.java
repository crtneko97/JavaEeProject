package com.example.enterprisecourse.models.patchnotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatchNotesRepository extends JpaRepository<PatchNotesEntity, Long>{

}
