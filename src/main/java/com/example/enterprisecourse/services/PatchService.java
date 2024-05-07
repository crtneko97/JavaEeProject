package com.example.enterprisecourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enterprisecourse.models.patchnotes.PatchNotesRepository;

@Service
public class PatchService {

	@Autowired private PatchNotesRepository patchNotesRepository;
	
	@Autowired
	public PatchService(PatchNotesRepository patchNotesRepository) {
		this.patchNotesRepository = patchNotesRepository;
	}
	
}
